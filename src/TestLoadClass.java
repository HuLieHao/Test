import java.util.HashMap;
import java.util.Map;

/**
 * User:  maktub
 * Date:   2018/4/24 10:56
 */
public class TestLoadClass {

    public static int k = 0;
    public static TestLoadClass t1 = new TestLoadClass("t1");
    public static TestLoadClass t2 = new TestLoadClass("t2");
    public static int i = print("i");
    public static int n = 99;
    public int j = print("j");
    {
        print("构造块");
    }
    static {
        print("静态块");
    }
    public TestLoadClass(String str) {
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
        TestLoadClass t = new TestLoadClass("init");
        Map<String, Object> map = new HashMap<>();
        map.put("a", "Test");
    }


}
