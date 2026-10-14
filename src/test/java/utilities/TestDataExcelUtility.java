package utilities;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

public class TestDataExcelUtility {

    private static final String filePath = System.getProperty("user.dir") + "/testData/RegistrationData.xlsx";
    private static final String sheetName = "Users";

    public static void appendRegistrationData(
            RegistrationData data) throws IOException {

        Workbook workbook;
        Sheet sheet;

        File file = new File(filePath);

        // Create parent directories if they don't exist
//        file.getParentFile().mkdirs();

        if (file.exists()) {

            try(FileInputStream fis = new FileInputStream(file)){
                workbook = WorkbookFactory.create(fis);
            }

        } else {

            workbook = new XSSFWorkbook();

        }

        sheet = workbook.getSheet(sheetName);

        if (sheet == null) {

            sheet = workbook.createSheet(sheetName);

            Row header = sheet.createRow(0);

            header.createCell(0).setCellValue("First Name");
            header.createCell(1).setCellValue("Last Name");
            header.createCell(2).setCellValue("Email");
            header.createCell(3).setCellValue("Password");
            header.createCell(4).setCellValue("Status");
            header.createCell(5).setCellValue("Created On");


//            sheet.createFreezePane(0,1);
        }

        int rowNumber = sheet.getLastRowNum() + 1;

        Row row = sheet.createRow(rowNumber);

        row.createCell(0).setCellValue(data.getFirstName());
        row.createCell(1).setCellValue(data.getLastName());
        row.createCell(2).setCellValue(data.getEmail());
        row.createCell(3).setCellValue(data.getPassword());
        row.createCell(4).setCellValue("REGISTERED");
        row.createCell(5).setCellValue(LocalDateTime.now().toString());

//        for (int i = 0; i < 4; i++) {
//            sheet.autoSizeColumn(i);
//        }

        try (FileOutputStream fos =
                     new FileOutputStream(filePath)) {

            workbook.write(fos);
        }

        workbook.close();
    }
}
