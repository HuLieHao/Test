package jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * User:  maktub
 * Date:   15/12/18 下午6:23
 */
public class Demo {

    private static String medicine_text = "/Users/maktub/medicine_text";

    private static BufferedWriter writer;

    static {
        try {
            writer = new BufferedWriter(new FileWriter(medicine_text));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        for (int i = 1; i < 3000; i++) {
            String url = "http://app2.sfda.gov.cn/datasearchp/index1.do?tableId=25&tableName=TABLE25&tableView=%B9%FA%B2%FA%D2%A9%C6%B7&Id=" + i;

            Document doc = null;
            try {
                 doc = Jsoup.connect(url).timeout(30000).get();
            }catch (Exception e) {
                System.out.println("请求失败：\t" + url);
                e.printStackTrace();
                continue;
            }

//        Elements titleElements = doc.select("td[nowrap]");
//
//        Elements keyElements = doc.select("th[nowrap=true]");
//        int keySize = keyElements.size();
//        System.out.println(keySize);
//
//        for (Element element : keyElements) {
//            String key = element.text();
//            System.out.println(key);
//        }

            Elements valueElements = doc.select("td[width=381], td[width=380]");

            if (valueElements.size() < 14) continue;

            String info = i + "#";
            for (Element element : valueElements) {
                info += element.text() + "#";
            }

            info += "\n";
//            int valueSize = valueElements.size();
//            System.out.println(valueSize);
            try {
                writer.append(info);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
