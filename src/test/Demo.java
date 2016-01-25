package test;

import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User:  maktub
 * Date:   16/1/4 下午4:16
 */
public class Demo {

    public static void main(String[] args) {

        String[] total = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B"};


        String chars = "";
        char a = 90;
        System.out.println(a);
        int i = 1;
        while(true) {
            int num = (int)(Math.random()*36) + 1;
            String c = total[--num];
            if (chars.indexOf(c) > 0) continue;
            if (chars.length() == 36) break;
            chars += c;
            System.out.print(c + " " + (i % 9 == 0 ? "\n" : ""));
            i++;
        }

        String[] m1 = {"s", "k", "3", "s", "t", "n", "w", "z", "o"};
        String[] m2 = {"f", "a", "g", "c", "i", "y", "u", "8", "s"};
        String[] m3 = {"b", "s", "v", "l", "5", "7", "e", "9", "6"};
        String[] m4 = {"j", "d", "4", "x", "1", "2", "p", "r", "m"};

        /**
         * 以userId第一位为选择哪个数组
         */


        System.out.println(total.length);

        randomCharData(12);

        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());



    }

    public static void randomCharData(int length) {
        StringBuilder builder = new StringBuilder();
        Random rand = new Random();
        Random randData = new Random();
        int data = 0;

        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(3);

            switch(index) {
                case 0:
                    data = randData.nextInt(10);
                    builder.append(data);
                    break;
                case 1:
                    data = randData.nextInt(26) + 65;
                    builder.append((char)data);
                    break;
                case 2:
                    data = randData.nextInt(26) + 97;
                    builder.append((char)data);
                    break;
            }
        }
        System.out.println(builder.toString());
    }
}
