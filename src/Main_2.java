import java.util.Scanner;

/**
 * User:  maktub
 * Date:   17/6/13 上午9:22
 */
public class Main_2 {

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
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(codeM(arr, 0));


    }

    public static int codeM(int[] arr, int dep) {

        if (arr.length == 1) {
            return dep;
        }else if (arr[0] <= arr[1]) {
            return dep;
        }

        int[] temp = new int[arr.length / 2];

        int k = 0;
        for (int i = 0; i < arr.length - 1; i += 2) {
            if (arr[i] > arr[i + 1]) {
                temp[k++] = arr[i];
            }else if (arr[i] <= arr[i + 1]) {
                temp[k++] = arr[i + 1];
            }
        }
        return codeM(temp, ++dep);
    }
}
