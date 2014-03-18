/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tabtool.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Administrator
 */
public class TabService {

    private static TabService tabService;
    private JTextArea logInfo;
    private String excelPath;
    private String tabPath;

    private TabService() {
    }

    public void init(JTextArea logInfo, String excelPath, String tabPath) {
        this.logInfo = logInfo;
        this.excelPath = excelPath;
        this.tabPath = tabPath;
    }

    public static TabService getInstance() {

        if (tabService == null) {
            tabService = new TabService();
        }
        return tabService;
    }

    private Sheet getExcelSheet0() {

        Workbook workbook = null;
        try {
            FileInputStream input = new FileInputStream(excelPath);
            if (excelPath.endsWith(".xls")) {
                workbook = new HSSFWorkbook(input);
            } else if (excelPath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(input);
            } else {
                logInfo.append("暂不支持选择的execl文件类型！\r\n");
                return null;
            }
            input.close();
        } catch (FileNotFoundException ex) {
            logInfo.append(ex.getMessage() + "\r\n");
        } catch (IOException ex) {
            logInfo.append(ex.getMessage() + "\r\n");
        }
        return workbook.getSheetAt(0);
    }

    private List<String> getParseData(Sheet sheet) {

        List<String> lines = new ArrayList<String>();

        int colLen = sheet.getRow(1).getLastCellNum();
        int rowLen = sheet.getLastRowNum();
        for (int rowNum = 1; rowNum <= rowLen; rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                logInfo.append("excel第" + (rowNum + 1) + " 行Row对象为空！！\r\n");
                return lines;
            }
            String line = "";
            for (int colNum = 0; colNum < colLen; colNum++) {
                Cell cell = row.getCell(colNum);
                String cellVal = "####";
                if (cell != null) {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cellVal = cell.getStringCellValue();
                }
                if (colNum + 1 == colLen) {
                    line += cellVal;
                } else {
                    line += cellVal + "\t";
                }
            }
            lines.add(line);
        }

        return lines;
    }

    private String getTabFilePath() {

        int nameStart = excelPath.lastIndexOf('\\');
        int nameEnd = excelPath.lastIndexOf('.');
        return tabPath + "\\" + excelPath.substring(nameStart + 1, nameEnd) + ".tab";
    }

    private void saveTabFile(List<String> lines) {
        String pathTab = getTabFilePath();
        File tabFile = new File(pathTab);
        if (!tabFile.exists()) {
            try {
                tabFile.createNewFile();
            } catch (IOException ex) {
                logInfo.append(ex.getMessage() + "\r\n");
                return;
            }
        }
        try {
            PrintWriter writer = new PrintWriter(tabFile);
            for (String line : lines) {
                writer.println(line);
            }
            writer.close();
        } catch (FileNotFoundException ex) {
            logInfo.append(ex.getMessage() + "\r\n");
            return;
        }
    }

    public void startService() {

        Sheet sheet = getExcelSheet0();
        if (sheet == null) {
            return;
        }

        List<String> lines = getParseData(sheet);
        if (lines == null || lines.isEmpty()) {
            return;
        }

        if (lines.size() < 3) {
            logInfo.append("excel表的行数小于4！！\r\n");
            return;
        }

        saveTabFile(lines);

        logInfo.append("表的转换已经成功！！！\r\n");
    }
}
