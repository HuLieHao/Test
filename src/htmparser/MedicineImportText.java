package htmparser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * User:  maktub
 * Date:   15/12/6 下午6:11
 */
public class MedicineImportText extends Thread{

    private static String medicine_text = "/Users/maktub/medicine_text";

    private static Long index = 0l;

    private List<Map<String, String>> medicine_list = null;

    private StringBuffer medicine = new StringBuffer();

    private static BufferedWriter writer;

    static {
        try {
            writer = new BufferedWriter(new FileWriter(medicine_text));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MedicineImportText(List<Map<String, String>> medicine_list) {
        this.medicine_list = medicine_list;
    }

    @Override
    public void run() {
        for (Map<String, String> map : medicine_list) {
            medicine.append(++index + "#");
            for (int i = 0; i < ProcessNode.medicine_info.length; i++) {
                medicine.append(map.get(ProcessNode.medicine_info[i]) + "#");
            }
            medicine.delete(medicine.length() - 1,medicine.length());
            medicine.append("\n");
        }
        try {
            writer.append(medicine.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
