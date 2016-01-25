import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User:  maktub
 * Date:   16/1/14 下午4:13
 */
public class Demo {

    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String temp = format.format(date);
        System.out.println(temp);

        System.out.println(null + "");
    }
}
