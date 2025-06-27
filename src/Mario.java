import java.util.Scanner;

public class Mario {

    public static void main(String[] args) {
        System.out.println("Enter a positive number:");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int result = printPattern(n);
        if (result <= 0) {
            System.out.println(result);
        }
    }

    public static int printPattern(int n) {
        if (n <= 0) {
            return n;
        }

        for (int i = 0; i < n; i++) {
            // Print spaces
            for (int j = 0; j < n - i - 1; j++) {
                System.out.print(" ");
            }
            // Print #
            for (int k = 0; k <= i; k++) {
                System.out.print("#");
            }
            System.out.println();
        }

        return 1;
    }
}
