package htmparser;

import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maktub on 15/11/28.
 */
public class ParseUrl extends Thread{

    //存储节点数据
    public List<NodeList> nodeLists = null;

    private Parser parser = null;

    private NodeList list = null;

    private Integer i = 0;

    private ProcessNode processNode = null;

    private String url = "";

    @Override
    public void run() {
        try {
            nodeLists = new ArrayList<NodeList>();
            for (i = 1; i <= 300000; i++) {

                url = "http://app2.sfda.gov.cn/datasearchp/index1.do?tableId=25&tableName=TABLE25&tableView=%B9%FA%B2%FA%D2%A9%C6%B7&Id=" + i;

                //生成一个解析器对象，用网页的url作为参数
                parser = new Parser(url);
//                //设置网页的编码，这是只是请求了一个gb2312编码网页
//                parser.setEncoding("gb2312");

                list = parser.parse(null);
                parser = null;
                nodeLists.add(list);

                if (i % 1000 == 0) {
                    processNode = new ProcessNode(nodeLists);
                    processNode.start();
                    processNode.join();

                    nodeLists.clear();
                    processNode = null;
                    System.gc();
                }

            }

            processNode = new ProcessNode(nodeLists);
            processNode.start();
            processNode.join();

            nodeLists.clear();
            processNode = null;
            System.gc();

            System.out.println("last:" + i);
        }catch (Exception e) {
            System.out.println("last:" + i);
            e.printStackTrace();
        }
    }

}
