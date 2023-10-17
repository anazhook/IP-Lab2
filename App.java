import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class App {

    // 4. Среди строк заданной матрицы, содержащих только нечетные элементы,
    // найти строку с максимальной суммой модулей элементов.
    public static Integer OddMaxSum(int[][] Matrix, int n, int m) {
        int sum = 0;
        Boolean flag = true; // true = elements are odd
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum += Math.abs(Matrix[i][j]); // sum
                if (Matrix[i][j] % 2 == 0)
                    flag = false;
            }
            if (flag)
                if (sum > max)
                    max = sum;
            sum = 0;
            flag = true;
        }
        return max;
    }

    // 15. Найти максимальное из чисел, встречающихся
    // в заданной матрице ровно два раза.
    public static Integer MaxTwice(int[][] Matrix, int n, int m) {
        Map<Integer, Integer> countMap = new HashMap<>(); // Подсчет количества вхождений числа в матрицу
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                countMap.put(Matrix[i][j], countMap.getOrDefault(Matrix[i][j], 0) + 1);
            }
        }

        // Поиск максимального числа, встречающегося два раза
        int max = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == 2 && entry.getKey() > max) {
                max = entry.getKey();
            }
        }
        return max;
    }

    /*
     * 26. Определить, становится ли симметричной (относительно главной диагонали)
     * заданная матрица после замены на число 0 каждого локального максимума.
     * Элемент
     * матрицы называется локальным максимумом, если он строго больше всех имеющихся
     * у него соседей. Соседями элемента ajj в матрице назовем элементы
     * aki с i-1 <= k <= i+1, j-1 <= l <= j+1,(k,l) != (i,j).
     */
    public static Boolean Symmetry(int[][] Matrix, int n) {
        for (int i = 0; i < n; i++) { // проверка на максимум и замена на 0
            for (int j = 0; j < n; j++) {
                boolean ismax = true;
                int temp = Matrix[i][j];
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        if (k >= 0 && k < n && l >= 0 && l < n && k != i && l != j) {
                            if (Matrix[k][l] >= temp) {
                                ismax = false;
                                break;
                            }
                        }
                    }
                    if (!ismax) {
                        break;
                    }
                }
                if (ismax) {
                    Matrix[i][j] = 0;
                }
            }
        }

        // проверка на симметричность
        Boolean symmetric = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (Matrix[i][j] != Matrix[j][i]) {
                    symmetric = false;
                }
            }
        }
        return symmetric;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name of the file: ");
        String filename = scanner.nextLine();
        File file = new File(filename);
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("file not open");
        }

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Max sum of odd element lines is " + OddMaxSum(matrix, n, m));

        System.out.println("Max of the numbers that are used only twice is " + MaxTwice(matrix, n, m));

        if (Symmetry(matrix, n))
            System.out.println("The matrix is symmetric after replacing local maximums with zero");
        else
            System.out.println("The matrix is not symmetric after replacing local maximums with zero");
    }
}