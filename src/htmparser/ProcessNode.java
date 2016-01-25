package htmparser;

import org.htmlparser.Node;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by maktub on 15/11/28.
 */
public class ProcessNode extends Thread{

    public static final String[] medicine_info = {"批准文号", "产品名称", "英文名称", "商品名", "剂型", "规格", "生产单位", "生产地址", "产品类别", "原批准文号" , "批准日期", "药品本位码", "药品本位码备注"};
    public static final String[] company_info = {"编号", "分类码", "省市", "企业名称", "法定代表人", "企业负责人", "企业类型", "注册地址", "生产地址", "生产范围", "发证日期", "有效期截止日", "备注"};


    //存储药品信息
    public List<Map<String, String>> medicine_list = new ArrayList<Map<String, String>>();

    //存储公司信息
    public List<Map<String, String>> company_list = new ArrayList<Map<String, String>>();

    public List<NodeList> nodeLists = null;

    private String[] name = new String[4];

    private int index = 0;

    public ProcessNode(List<NodeList> nodeLists) {
        this.nodeLists = nodeLists;
    }

    @Override
    public void run() {

        try {
            for (NodeList nodeList : nodeLists) {
                Map<String, String> medicine_map = new HashMap<String, String>();
                Map<String, String> company_map = new HashMap<String, String>();

                processNodeList(nodeList, medicine_map, company_map);
                index = 0;
                name[0] = null;
                name[1] = null;
                name[2] = null;
                name[3] = null;

                if (medicine_map.size() > 0) {
//                    System.out.println("htmparser.ProcessNode medicine_map " + medicine_map.size());
                    medicine_list.add(medicine_map);
                }

                if (medicine_list.size() > 0 && medicine_list.size() % 200 == 0) {
                    MedicineImportText medicineImportText = new MedicineImportText(medicine_list);
                    medicineImportText.start();
                    medicineImportText.join();

                    medicine_list.clear();
                    medicineImportText = null;
                    System.gc();
                }
            }

            MedicineImportText medicineImportText = new MedicineImportText(medicine_list);
            medicineImportText.start();
            medicineImportText.join();

            medicine_list.clear();
            medicineImportText = null;
            System.gc();


        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processNodeList(NodeList nodeList, Map<String, String> medicine_map, Map<String, String> company_map) {

        //迭代开始
        SimpleNodeIterator iterator = nodeList.elements();
        while (iterator.hasMoreNodes()) {
            Node node = iterator.nextNode();
            if ("td background=\"face5/images/2010_data_butmiddle.gif\" class=\"new_fontstyle\" valign=\"top\" nowrap".equals(node.getText()) && index <= 3) {
                name[index] = node.toPlainTextString();
                index++;
            }
            //得到该节点的子节点列表
            NodeList childList = node.getChildren();
            //孩子节点为空，说明是值节点
            if (null == childList) {
                //得到值节点值
                if (name[0] != null && !"".equals(name[0]) && (name[1] == null || "".equals(name[1]))) {
                    getInfo(0, node, node.toPlainTextString().trim(), medicine_map, company_map);
                }
                if (name[1] != null && !"".equals(name[1])  && (name[2] == null || "".equals(name[2]))) {
                    getInfo(1, node, node.toPlainTextString().trim(), medicine_map, company_map);
                }

            }else {
                processNodeList(childList, medicine_map, company_map);
            }
        }
    }

    private void getInfo(int index, Node node, String keys, Map<String, String> medicine_map, Map<String, String> company_map) {

        if (index == 0) {
            for (String info : medicine_info) {
                if (info.equals(keys)) {
                    medicine_map.put(keys, node.getParent().getNextSibling().getNextSibling().toPlainTextString());
                }
            }
        }else if (index ==1) {
            for (String info : company_info) {
                if (info.equals(keys)) {
                    company_map.put(keys, node.getParent().getNextSibling().getNextSibling().toPlainTextString());
                }
            }
        }
    }
}
