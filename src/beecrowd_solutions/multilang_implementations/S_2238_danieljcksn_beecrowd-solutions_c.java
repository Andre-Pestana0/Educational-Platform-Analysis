import java.util.Scanner;

public class Main {

    // Em Java, trocas diretas em arrays exigem os índices, pois não há ponteiros
    public static void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return (i + 1);
    }

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    public static void cDivisors(int c, int[] divisors) {
        int idx = 0;
        for (int i = 1; i <= Math.sqrt(c); ++i) {
            if (c % i == 0) {
                if (c / i == i) {
                    divisors[idx] = i;
                    idx++;
                } else {
                    divisors[idx] = i;
                    idx++;
                    divisors[idx] = c / i;
                    idx++;
                }
            }
        }
    }

    public static boolean verify(int n, int a, int b, int d) {
        return (n % a == 0 && n % b != 0 && d % n != 0);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (sc.hasNextInt()) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            int d = sc.nextInt();

            int[] divisors = new int[1000];

            cDivisors(c, divisors);
            
            // Mantida a mesma chamada do seu código (ordena os 100 primeiros elementos)
            quickSort(divisors, 0, 99);
            
            int ans = -1;

            for (int i = 0; i < 1000; ++i) {
                if (divisors[i] == 0) continue;
                if (verify(divisors[i], a, b, d)) {
                    ans = divisors[i];
                    break;
                }
            }

            System.out.println(ans);
        }

        sc.close();
    }
}