package solo;

/**
 * User:  maktub
 * Date:   17/6/27 下午4:37
 */
public class A {
    int a = 0;

    A() {
        System.out.print("default");
    }
    public A(int a) {
        print(this.a);
    }

    protected void print(int a) {
        System.out.print(a);
    }

    static class R {
        static  String s = "RA!";
        R A;
        R(){
            System.out.print(A.s);

        }
    }
    public static void main(String[] args) {
        System.out.print(R.s);
    }
}
