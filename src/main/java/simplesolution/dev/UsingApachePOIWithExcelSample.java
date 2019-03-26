package simplesolution.dev;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;

public class UsingApachePOIWithExcelSample {

    public static void main(String... args) {
        createSimpleFile();

        Workbook hssfWorkbook = new HSSFWorkbook();
        createXLSFile(hssfWorkbook, "employees.xls");

        Workbook xssfWorkbook = new XSSFWorkbook();
        createXLSFile(xssfWorkbook, "employees.xlsx");
    }

    private static void createSimpleFile() {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("simplesolution.dev");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Simple Solution");
        try (OutputStream outputStream = new FileOutputStream("sample.xls")) {
            workbook.write(outputStream);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void createXLSFile(Workbook workbook, String fileName) {
        Sheet sheet = workbook.createSheet("Employee List");
        createHeaderRow(workbook, sheet);

        Employee employee = Employee.createEmployee(2303, "Buffet", "Jimmy", 1975, 3, 4, "The Alamo", "San Antonio", new BigDecimal(75324.7634));
        createEmployeeRow(workbook, sheet, employee, 1);

        employee = Employee.createEmployee(2304, "Cartman", "Eric", 1973, 11, 8, "Empire State Building", "New York", new BigDecimal(59000.0256));
        createEmployeeRow(workbook, sheet, employee, 2);

        employee = Employee.createEmployee(2307, "Jefferson", "George", 1980, 12, 10, "Mockingbird Lane", "Fargo", new BigDecimal(159000.342));
        createEmployeeRow(workbook, sheet, employee, 3);

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(6);
        try (OutputStream outputStream = new FileOutputStream(fileName)) {
            workbook.write(outputStream);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void createHeaderRow(Workbook workbook, Sheet sheet) {
        Row row = sheet.createRow(0);
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Cell cell = row.createCell(0);
        cell.setCellValue("Employee ID");
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(1);
        cell.setCellValue("First Name");
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(2);
        cell.setCellValue("Last Name");
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(3);
        cell.setCellValue("Birth Date");
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(4);
        cell.setCellValue("Address");
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(5);
        cell.setCellValue("City");
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(6);
        cell.setCellValue("Salary");
        cell.setCellStyle(headerCellStyle);
    }

    private static void createEmployeeRow(Workbook workbook, Sheet sheet, Employee employee, int rowIndex) {
        Row row = sheet.createRow(rowIndex);
        row.createCell(0).setCellValue(employee.getEmployeeId());
        row.createCell(1).setCellValue(employee.getFirstName());
        row.createCell(2).setCellValue(employee.getLastName());

        Cell birthDateCell = row.createCell(3);
        birthDateCell.setCellValue(employee.getBirthDate());
        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy"));
        birthDateCell.setCellStyle(cellStyle);

        row.createCell(4).setCellValue(employee.getAddress());
        row.createCell(5).setCellValue(employee.getCity());
        Cell salaryCell = row.createCell(6);
        salaryCell.setCellValue(employee.getSalary().doubleValue());
        CellStyle salaryCellStyle = workbook.createCellStyle();
        salaryCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("#,##0.00"));
        salaryCell.setCellStyle(salaryCellStyle);
    }

}

