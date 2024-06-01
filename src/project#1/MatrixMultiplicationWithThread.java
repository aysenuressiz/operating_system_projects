import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MatrixMultiplicationWithThread {

    public static void main(String[] args) {
        String fileName = "matrices.txt";
        Random rand = new Random();
        int r = rand.nextInt(100); 
        generateRandomMatricesToFile(fileName, r);

        int[][][] matrices = readMatricesFromFile(fileName);
        if (matrices != null && matrices.length == 2) {
            int[][] matrixA = matrices[0];
            int[][] matrixB = matrices[1];
            int[][] resultMatrix = new int[r][r];

            Thread[] threads = new Thread[r];
            //long[] threadTimes = new long[r];

            long startTime = System.currentTimeMillis();
            for (int i = 0; i < r; i++) {
                final int row = i;
                threads[row] = new Thread(() -> {
                    for (int j = 0; j < r; j++) {
                        resultMatrix[row][j] = 0;
                        for (int k = 0; k < r; k++) {
                            resultMatrix[row][j] += matrixA[row][k] * matrixB[k][j];
                        }
                    }
                });
                threads[row].start();
            }

            long endTime = System.currentTimeMillis();
            long threadTimes = endTime - startTime;

            for (int i = 0; i < r; i++) {
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Matrix A:");
            printMatrix(matrixA);

            System.out.println("Matrix B:");
            printMatrix(matrixB);

            System.out.println("Matrix C:");
            printMatrix(resultMatrix);
            
            System.out.println("toplam zaman "+threadTimes + " mıllısanıye" );

            
        } else {
            System.out.println("Error reading matrices from file.");
        }
    }

    // Rastgele iki matrisi dosyaya yazar
    public static void generateRandomMatricesToFile(String fileName, int r) {
        Random random = new Random();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            // İlk matris
            bw.write(r + "\n");
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < r; j++) {
                    bw.write(random.nextInt(10) + " ");
                }
                bw.write("\n");
            }
            // İkinci matris
            bw.write(r + "\n");
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < r; j++) {
                    bw.write(random.nextInt(10) + " ");
                }
                bw.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Dosyadan iki matrisi okur
    public static int[][][] readMatricesFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int n = Integer.parseInt(br.readLine());
            int[][] matrixA = new int[n][n];

            for (int i = 0; i < n; i++) {
                String[] values = br.readLine().split(" ");
                for (int j = 0; j < n; j++) {
                    matrixA[i][j] = Integer.parseInt(values[j]);
                }
            }

            n = Integer.parseInt(br.readLine());
            int[][] matrixB = new int[n][n];

            for (int i = 0; i < n; i++) {
                String[] values = br.readLine().split(" ");
                for (int j = 0; j < n; j++) {
                    matrixB[i][j] = Integer.parseInt(values[j]);
                }
            }

            return new int[][][]{matrixA, matrixB};
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Matrisi ekrana yazdırır
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}
