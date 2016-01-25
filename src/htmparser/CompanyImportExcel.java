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
public class CompanyImportExcel extends Thread{

    private String company_excel = "/Users/maktub/company_info.xlsx";

    private InputStream inputStream = null;

    private OutputStream outputStream = null;

    private Workbook workbook = null;

    private Sheet sheet = null;

    private Integer maxRows = 0;

    private List<Map<String, String>> company_list = null;

    private Row row = null;

    private Cell cell = null;


    public CompanyImportExcel(List<Map<String, String>> company_list) {

        this.company_list = company_list;

        try {
//            System.out.println("htmparser.CompanyImportExcel EXCEL初始化");
            File file = new File(company_excel);

            inputStream = new FileInputStream(file);
            workbook = new XSSFWorkbook(inputStream);
            sheet = workbook.getSheetAt(0);
            maxRows = sheet.getLastRowNum();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        for (Map<String, String> map : company_list) {
            row = sheet.createRow(++maxRows);

            for (int i = 0; i < ProcessNode.company_info.length; i++) {

                cell = row.createCell(i);
                cell.setCellValue(map.get(ProcessNode.company_info[i]));
            }
            after();
        }

//        while (true) {
//
////            System.out.println("ImportExcel company_list " + company_list.size());
////            System.out.println("ImportExcel company_list_index " + company_list_index);
//            if (company_list.size() > company_list_index) {
//
//
//                temp = company_list.get(company_list_index);
//                row = sheet.createRow(++maxRows);
//
//                for (int i = 0; i < htmparser.ProcessNode.company_info.length; i++) {
//
//                    cell = row.createCell(i);
//                    cell.setCellValue(temp.get(htmparser.ProcessNode.company_info[i]));
//                }
//                after();
//                company_list_index++;
//
//                stop_index = 0;
//            }else {
//                try {
//                    sleep(2000);
//                    stop_index++;
//                    if (stop_index >= 10 && !htmparser.Main.processNode.isAlive()) {
//                        System.out.println("htmparser.CompanyImportExcel 线程停止运行");
//                        stop();
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }


    //写入操作
    private void after(){
        try {
            File file = new File(company_excel);
            outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            outputStream.close();

            //https://bz.apache.org/bugzilla/show_bug.cgi?id=49940
            inputStream = new FileInputStream(company_excel);
            workbook = new XSSFWorkbook(inputStream);
            sheet = workbook.getSheetAt(0);
            maxRows = sheet.getLastRowNum();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
