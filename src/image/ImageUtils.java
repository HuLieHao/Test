package image;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * User:  maktub
 * Date:   16/3/14 上午11:19
 */
public class ImageUtils {


    public static File createTitle() throws IOException {

        File file_title = new File("/Users/maktub/Desktop/images/u314_01.jpg");

        String text_No = "No.10015169366996900679";

        String user_name = "小路, 女, 29岁";

        String doctor_name = "陈银凤";

        String dept = "妇产科";

        String date = "2018-01-26";

        Font font = new Font(null, Font.PLAIN, 23);

        BufferedImage image = ImageIO.read(file_title);

        Graphics2D g2 = (Graphics2D) image.getGraphics();
        g2.setColor(Color.LIGHT_GRAY);
        g2.setFont(font);
        g2.drawString(text_No, 37, 159);

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("黑体", Font.PLAIN, 26));
        g2.drawString(user_name, 135, 252);

        g2.drawString(doctor_name, 135, 300);

        g2.drawString(dept, 135, 350);

        g2.drawString(date, 135, 400);

        File result = new File("/Users/maktub/Desktop/images/test01.jpg");

        ImageIO.write(image, "jpg", new FileOutputStream(result));

        return result;
    }

    public static File createBody() throws IOException {

        String diagnosis = "医生的看法凑够多少字医生的看法凑够多少字医生的看法凑够多少字医生的看法凑够多少字医生的看法凑够多少字你好";

        File file = new File("/Users/maktub/Desktop/images/test02.jpg");

        int width = 792;

        //计算高度

        int height = 500;

        String text = "诊断:";

        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Font font_bold_26 = new Font("黑体", Font.BOLD, 26);
        Font font_plain_20 = new Font("黑体", Font.PLAIN, 20);

        Graphics2D g2 = (Graphics2D) bi.getGraphics();
        g2.setBackground(Color.white);
        g2.clearRect(0, 0, width, height);
        g2.setPaint(Color.black);

        g2.setFont(font_bold_26);
        g2.drawString(text, 35, 60);

        //计算的换行

//        FontRenderContext context = g2.getFontRenderContext();
//        Rectangle2D bounds = font_plain_20.getStringBounds(diagnosis, context);

        g2.setPaint(Color.LIGHT_GRAY);
        g2.setFont(font_plain_20);
        //宽度限制在700
        //字体20, 一行最多展示35个汉字
        int endLine = 0;
        for (int i = 0; i <= diagnosis.length() / 35; i++) {
            int l = 35 * (i + 1);
            String diagnosis_1 = diagnosis.substring(35 * i, l > diagnosis.length() ? diagnosis.length() : l);
            endLine = 100 + 30 * i;
            g2.drawString(diagnosis_1, 50, endLine);
        }

        g2.setPaint(Color.black);
        g2.setFont(font_bold_26);
        g2.drawString("Rp:", 35, endLine + 50);

        Map<String, String> map = new HashMap<>();
        map.put("goods_name", "清宫长春胶囊");
        map.put("specification", "0.25g*40粒");
        map.put("num", "6");
        map.put("unit", "盒");
        map.put("user_method", "口服, 4片/次, 每日三次, 用药7天");

        Map<String, String> map_2 = new HashMap<>();
        map_2.put("goods_name", "中药方剂(颗粒)X10 配方:当归12克。炙黄芪30克。山药12克。白芍12克。柴胡12克。升麻12克。柏子仁12克。酸枣仁儿30克。嗯，首乌藤12克，龙眼肉12克。炒白术10克。生地10克，熟地10克,桑葚15克，制首乌10克。沙参10克，麦冬10克。陈皮10克，砂仁10克，神曲10克。川芎10克,菟丝子12克，肉苁蓉12克。杜仲10克。枳壳10克，肉桂6克。，炙甘草6克。炒山楂10克。煅龙骨，30克，煅牡蛎，30克");
        map_2.put("specification", "");
        map_2.put("num", "");
        map_2.put("unit", "");
        map_2.put("user_method", "无 注意事项: 一天两次一次一包喝十天");

        java.util.List<Map<String, String>> list = new ArrayList<>();
        list.add(map);
        list.add(map);
        list.add(map_2);

        endLine = endLine + 100;
        for(Map<String, String> temp : list) {
            String medicine_name = temp.get("goods_name") + "  " + temp.get("specification");
            endLine += 50;
            g2.drawString(medicine_name, 40, endLine);

            String num = temp.get("num");
            String unit = temp.get("unit");
            if (!"".equals(num)) {
                endLine += 50;
                g2.drawString("数量: " + num + unit, 40, endLine);
            }

        }

        ImageIO.write(bi, "jpg", new FileOutputStream(file));

        return file;
    }

    public static File createFooter() throws IOException {

        File file_title = new File("/Users/maktub/Desktop/images/u314_03.jpg");

        String param1 = "陈银凤";

        String param2 = "赵明琦";

        String param3 = "李肖珍";

        Font font = new Font("宋体", Font.PLAIN, 27);

        BufferedImage image = ImageIO.read(file_title);

        Graphics2D g2 = (Graphics2D) image.getGraphics();
        g2.setColor(Color.RED);
        g2.setFont(font);
        g2.drawString(param1, 235, 90);

        g2.drawString(param2, 235, 155);

        g2.drawString(param3, 235, 220);


        File result = new File("/Users/maktub/Desktop/images/test03.jpg");

        ImageIO.write(image, "jpg", new FileOutputStream(result));

        return result;

    }

    public void composition(File titleFile, File footFile) {



    }

    public static void main(String[] args) throws IOException {

//        long start = System.currentTimeMillis();
//
//        File titleFile = createTitle();
//
//        File bodyFile = createBody();
//
//        File footer = createFooter();
//
//        long end = System.currentTimeMillis();
////        System.out.println(end - start);

    }


}
