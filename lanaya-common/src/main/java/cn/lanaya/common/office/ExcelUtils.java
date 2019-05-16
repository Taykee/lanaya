package cn.lanaya.common.office;

import cn.lanaya.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ExcelUtils {
    private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    public static Workbook getWorkBook(InputStream is, File file) throws IOException {
        return getWorkBook(is, file.getName());
    }

    public static Workbook getWorkBook(InputStream is, String name) throws IOException {
        Workbook wb = null;
        if (StringUtils.endsWithIgnoreCase(name, EXCEL_XLS)) {
            wb = new HSSFWorkbook(is);
        } else if (StringUtils.endsWithIgnoreCase(name, EXCEL_XLSX)) {
            wb = new XSSFWorkbook(is);
        }
        return wb;
    }

    public static void checkExcelValid(File file) {
        if (!file.exists()) {
            throw new BusinessException("file is not exists");
        }
        if (!(file.isFile() && (StringUtils.endsWithIgnoreCase(file.getName(), EXCEL_XLS) ||
                StringUtils.endsWithIgnoreCase(file.getName(), EXCEL_XLSX)))) {
            throw new BusinessException("not excel file");
        }
    }

    public static <T> List<T> parseRowData(Sheet sheet, int startRow, Map<String, String> map,
                                           CalculateRow<T> calRow) {
        return parseRowData(sheet, startRow, map, calRow, null);
    }

    public static <T> List<T> parseRowData(Sheet sheet, int startRow, Map<String, String> map,
                                           CalculateRow<T> calRow, Set<String> emptyCols) {
        List<T> ts = new LinkedList<>();
        int type = -1;
        boolean flag = false;
        for (Row row : sheet) {
            int rowNum = row.getRowNum() + 1;
            if (rowNum < startRow) {
                continue;
            }
            type = calRow.getRowType(row, type);
            Map<String, String> beanMap = new HashMap<>();
            String cellValue = StringUtils.EMPTY;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                int colNum = getColumnNum(key);
                Cell cell = row.getCell(colNum);
                cellValue = StringCellValue(cell);
                if (StringUtils.isBlank(cellValue)) {
                    if (emptyCols != null && emptyCols.contains(key)) {
                        logger.info("{} 第[{}]行[{}]列无数据", sheet.getSheetName(), rowNum, key);
                        continue;
                    } else {
                        logger.info("{} 第[{}]行[{}]列无数据或数据无效", sheet.getSheetName(), rowNum, key);
                        flag = true;
                        break;
                    }
                }
                beanMap.put(entry.getValue(), cellValue);
            }
            if (flag) {
                flag = false;
                continue;
            }
            T bean = calRow.getBean(beanMap, type);
            ts.add(bean);
        }
        return ts;
    }

    public static String StringCellValue(Cell cell) {
        String res = StringUtils.EMPTY;
        if (cell == null) {
            return res;
        }
        switch (cell.getCellType()) {
            case BLANK:
                break;
            case STRING:
                res = cell.getStringCellValue();
                break;
            case FORMULA:
                res = cell.getCellFormula();
                break;
            case NUMERIC:
                res = cell.getNumericCellValue() + "";
                break;
            default:
                break;
        }
        if(res != null && res.endsWith(".0")){
            res = res.substring(0, res.indexOf(".0"));
        }
        return res;
    }

    public static int getColumnNum(String colStr) {
        int num = 0;
        int result = 0;
        colStr = StringUtils.upperCase(colStr);
        for (int i = 0; i < colStr.length(); i++) {
            char ch = colStr.charAt(colStr.length() - i - 1);
            num = ch - 'A' + 1;
            num *= Math.pow(26, i);
            result += num;
        }
        return result - 1;
    }
}
