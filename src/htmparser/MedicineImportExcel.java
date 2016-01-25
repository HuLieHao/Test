package htmparser;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by maktub on 15/11/28.
 */
public class MedicineImportExcel extends Thread{

    private static Integer index = 1;

    private static String medicine_excel = "/Users/maktub/medicine_info_" + index + ".xlsx";

    private String templete = "/Users/maktub/medicine_info副本.xlsx";

    private InputStream inputStream = null;

    private OutputStream outputStream = null;

    private  Workbook workbook = null;

    private Sheet sheet = null;

    private Integer maxRows = 0;

    private List<Map<String, String>> medicine_list = null;

    private Row row = null;

    private Cell cell = null;

    public MedicineImportExcel(List<Map<String, String>> medicine_list) {

        this.medicine_list = medicine_list;
        try {
//            System.out.println("htmparser.MedicineImportExcel EXCEL初始化");
            File file = new File(medicine_excel);
            Long size = file.length();
            if (size >= 122880) {
                index++;
                medicine_excel = "/Users/maktub/medicine_info_" + index + ".xlsx";
                copyFile(templete, medicine_excel);
                file = new File(medicine_excel);
            }
            inputStream = new FileInputStream(file);
            workbook = new XSSFWorkbook(inputStream);
            sheet = workbook.getSheetAt(0);
            maxRows = sheet.getLastRowNum();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        for (Map<String, String> map : medicine_list) {
            row = sheet.createRow(++maxRows);

            for (int i = 0; i < ProcessNode.medicine_info.length; i++) {

                cell = row.createCell(i);
                cell.setCellValue(map.get(ProcessNode.medicine_info[i]));

//                System.out.println(htmparser.ProcessNode.medicine_info[i] + " : " + map.get(htmparser.ProcessNode.medicine_info[i]));
            }
            after();
        }

//        while (true) {
//
////            System.out.println("ImportExcel medicine_list " + medicine_list.size());
////            System.out.println("ImportExcel medicine_list_ndex " + medicine_list_ndex);
//            if (medicine_list.size() > medicine_list_ndex) {
//
//
//                temp = medicine_list.get(medicine_list_ndex);
//                Row row = sheet.createRow(++maxRows);
//
//                for (int i = 0; i < htmparser.ProcessNode.medicine_info.length; i++) {
//
//                    cell = row.createCell(i);
//                    cell.setCellValue(temp.get(htmparser.ProcessNode.medicine_info[i]));
//
////                    System.out.println(htmparser.ProcessNode.medicine_info[i] + " : " + value);
//                }
//                after();
//                medicine_list_ndex++;
//                stop_index = 0;
//            }else {
//                try {
//                    sleep(2000);
//                    stop_index++;
//                    if (stop_index >= 10 && !htmparser.Main.processNode.isAlive()) {
//                        System.out.println("htmparser.MedicineImportExcel 线程停止运行");
//                        stop();
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }

    private static void copyFile(String file1, String file2) throws Exception {
        FileInputStream fis = new FileInputStream(file1);
        FileOutputStream fos = new FileOutputStream(file2);
        int temp;
        while ((temp = fis.read()) != -1) {
            fos.write(temp);
            fos.flush();
        }
        fis.close();
        fos.close();
    }


    //写入操作
    private void after(){
        try {

            outputStream = new FileOutputStream(medicine_excel);
            workbook.write(outputStream);
            outputStream.close();

            //https://bz.apache.org/bugzilla/show_bug.cgi?id=49940
            File file = new File(medicine_excel);
            Long size = file.length();
            if (size >= 122880) {
                index++;
                medicine_excel = "/Users/maktub/medicine_info_" + index + ".xlsx";
                copyFile(templete, medicine_excel);
                file = new File(medicine_excel);
            }
            inputStream = new FileInputStream(file);
            workbook = new XSSFWorkbook(inputStream);
            sheet = workbook.getSheetAt(0);
            maxRows = sheet.getLastRowNum();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
