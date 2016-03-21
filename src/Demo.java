
import image.ImageUtils;
import org.apache.commons.httpclient.NameValuePair;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        File file = new File("/Users/maktub/Pictures/naruto-1.png");
        System.out.println(file.getName());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getParent());


        ImageUtils imageUtils = new ImageUtils(file.getAbsolutePath());
        imageUtils.resizeFix(100, 100);


    }


}
