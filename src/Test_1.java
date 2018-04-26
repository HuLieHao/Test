/**
 * User:  maktub
 * Date:   17/8/11 10:53
 */
public class Test_1 {

    public static int k = 0;
    public static Test_1 t1 = new Test_1("t1");
    public static Test_1 t2 = new Test_1("t2");
    public static int i = print("i");
    public static int n = 99;
    public int j = print("j");

    {
        print("构造块");
    }
    static {
        print("静态块");
    }

    public Test_1(String str) {
        System.out.println((++k) + ":" + str + "   i=" + i + "    n=" + n);
        ++i;
        ++n;
    }

    public static int print(String str) {
        System.out.println((++k) + ":" + str + "   i=" + i + "    n=" + n);
        ++n;
        return ++i;
    }

    public static void main(String args[]) {
        Test_1 t = new Test_1("init");
    }
}
