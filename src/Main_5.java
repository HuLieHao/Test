import java.util.Scanner;

/**
 * User:  maktub
 * Date:   17/6/13 上午9:22
 */
public class Main_5 {

    public void scanner_1() {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }
    }

    public void scanner_2() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ans = 0, x;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                x = sc.nextInt();
                ans += x;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        int n = (int)Math.sqrt(8);
        System.out.println(n);
        Scanner sc = new Scanner(System.in);
        int min = sc.nextInt();
        int max = sc.nextInt();

       CodeM(min, max);

    }

    public static void CodeM(int min, int max) {

        int[] temp = new int[9];
        for (int i = min; i <= max; i++) {
            int n = (int)Math.sqrt(i);
            System.out.println(n);
            for (int j = 1; j <= n; j++) {
                int k = j / (int)Math.pow((j + "").length(), 10);
                System.out.print(k + "");
                if (n % j == 0) {
                    temp[--k]++;
                }
            }

            if (i != 1) {
                int k = i / (int)Math.pow((i + "").length(), 10);
                temp[--k]++;
            }
        }

        System.out.println();
        for(int m : temp) {
            System.out.println(m);
        }

    }


}
