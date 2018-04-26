package solo;

/**
 * User:  maktub
 * Date:   17/6/27 下午4:39
 */
public class Program {

    public static void main(String[] args) {

        B b = new B();

        Integer a = 1000 + (int)(Math.random() * (9999 - 1000 + 1));
        System.out.println(a.longValue());

        String s = "abc";
        for (String t : s.split(",")) {
            System.out.println(t);
        }
    }
}
