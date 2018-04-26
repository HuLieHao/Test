
import image.ImageUtils;
//import org.apache.commons.httpclient.NameValuePair;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * User:  maktub
 * Date:   16/1/14 下午4:13
 */
public class Demo {

    public static void main(String[] args) {
//        Date date = new Date();
//        date.setTime(1456474655127L);
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String temp = format.format(date);
//        System.out.println(temp);

//        System.out.println(null + "");


//        NameValuePair param = new NameValuePair("key", "value");
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(param);
//
//        Object[] obj = params.toArray();
//        System.out.println(params.toArray().length);
//        NameValuePair[] avc = new NameValuePair[4];
//        NameValuePair[] array = params.toArray(avc);
//
//        System.out.println(array.length);

//        File file = new File("/Users/maktub/eBook/How_Tomcat_Works.pdf");
//        File file = new File("/Users/maktub/Pictures/naruto-1.png");
//        System.out.println(file.getName());
//        System.out.println(file.getAbsoluteFile());
//        System.out.println(file.getAbsolutePath());
//        System.out.println(file.getParent());
//
//
//        ImageUtils imageUtils = new ImageUtils(file.getAbsolutePath());
//        imageUtils.resizeFix(100, 100);

//
//        String str = "[0xe7][0xad][0xbe][0xe5][0x90][0x8d][0xe9][0x94][0x99][0xe8][0xaf][0xaf]]";
//        System.out.println(str.replace("[", "").replace("]", ","));
//        char[] cs = {0xe7,0xad,0xbe,0xe5,0x90,0x8d,0xe9,0x94,0x99,0xe8,0xaf,0xaf};
//        byte[] ds = new byte[cs.length] ;
//        for(int i=0;i<cs.length;i++){
//            ds[i]=(byte)cs[i];
//        }
//
//        System.out.println(new String(ds));

//        String uuid = UUID.randomUUID().toString();
//        System.out.println(uuid.replace("-", ""));
//        System.out.println(uuid.replace("-", "").length());
//
//        System.out.println(System.currentTimeMillis());
//
//
//        Date date = new Date();
//        //1472551805470
//        //1472550901805720
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String  str = format.format(date);
//        System.out.println(str);
//
//        //unix时间是秒级的,而java时间是毫秒级的
//        Date date1 = new Date(Long.parseLong("1472550901805720") / 1000);
//        System.out.println(format.format(date1));


        Collection<String> list = new ArrayList<>();
        list.add("Python");
        list.add("Java");
        list.add("C++");

        Iterator<String> itr = list.iterator();
        while (itr.hasNext()) {
            String lan = itr.next();
//            list.remove(lan);
            if (lan == "C++") {
                itr.remove();
            }
        }

        for (String language : list) {
            System.out.println(language);
        }

        for(String language : list) {
            list.remove(language);
        }

//        //8n^2 <= 64nlg^n
//
//        for (double n = 0; n <= 50; n += 0.1) {
//
//            double left = 8 * Math.pow(n, 2);
//            double right = 64 * n *  Math.log10(n);
//
//            if (left <= right) {
//                System.out.println("1题:" + n);
//            }
//
////            left = 100 * Math.pow(n, 2);
////            right = Math.pow(2, n);
////            if (left <= right) {
////                System.out.println("2题:" + n);
////            }
//
//        }
//
//        int n = (int)((Math.random() + 1) * 1000);
//
//        System.out.println(n);

        Integer pay_type = 1;
        System.out.println(pay_type.byteValue());


        System.out.println(new Date().getTime());

    }


}
