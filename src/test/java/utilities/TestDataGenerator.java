package utilities;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public final class TestDataGenerator {

    private static final String filePath = System.getProperty("user.dir") + "/testData/RegistrationData.xlsx";


    private static final String sheetName = "Users";

    private TestDataGenerator() {
    }

    public static RegistrationData generateUser() {

        File file = new File(filePath);

        try{
            file.getParentFile().mkdir();

            if (!file.exists() || file.length() ==0){
                createExcelFile(file);
            }

            String sequence = String.format("%04d", getNextSequence());

            RegistrationData user = new RegistrationData(
                    "UserFirstName" + sequence,
                    "UserLastName" + sequence,
                    "user" + sequence + "@gmail.com",
                    "TestUser@" + sequence
            );

            saveUserToExcel(user);

            return user;

        }
        catch (IOException e){
            throw new RuntimeException("unable to generate or save registratiion data.. ",e);
        }

    }

    private static void createExcelFile(File file) throws IOException {

        try (Workbook workbook = new org.apache.poi.xssf.usermodel.XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet(sheetName);

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("FirstName");
            header.createCell(1).setCellValue("LastName");
            header.createCell(2).setCellValue("Email");
            header.createCell(3).setCellValue("Password");

            try (java.io.FileOutputStream fos =
                         new java.io.FileOutputStream(file)) {
                workbook.write(fos);
            }
        }
    }


    private static void saveUserToExcel(RegistrationData user)
            throws IOException {

        File file = new File(filePath);

        // Write to a temporary file first to avoid corrupting the original
        File tempFile = new File(filePath + ".tmp");

        try {
            // Keep the original workbook untouched while reading it
            try (Workbook workbook = WorkbookFactory.create(file)) {

                Sheet sheet = workbook.getSheet(sheetName);

                if (sheet == null) {
                    sheet = workbook.createSheet(sheetName);
                }

                int rowNumber = Math.max(1, sheet.getLastRowNum() + 1);
                Row row = sheet.createRow(rowNumber);

                row.createCell(0).setCellValue(user.getFirstName());
                row.createCell(1).setCellValue(user.getLastName());
                row.createCell(2).setCellValue(user.getEmail());
                row.createCell(3).setCellValue(user.getPassword());

                // Save to a temporary file, not to the original workbook
                try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                    workbook.write(fos);
                }
            }

            // Replace the original only after the workbook has closed
            try {
                java.nio.file.Files.move(
                        tempFile.toPath(),
                        file.toPath(),
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING,
                        java.nio.file.StandardCopyOption.ATOMIC_MOVE
                );
            } catch (java.nio.file.AtomicMoveNotSupportedException e) {
                java.nio.file.Files.move(
                        tempFile.toPath(),
                        file.toPath(),
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING
                );
            }

            System.out.println("Excel updated successfully: "
                    + file.getAbsolutePath());

        } finally {
            // Remove leftover temporary file if saving failed
            if (tempFile.exists()) {
                tempFile.delete();
            }
        }
    }

    private static int getNextSequence() {

        File file = new File(filePath);

        if (!file.exists() || file.length() == 0) {
            return 1;
        }

        int maxSequence = 0;

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                return 1;
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                if (row == null || row.getCell(2) == null) {
                    continue;
                }

                String email = row.getCell(2).getStringCellValue();

                if (email.matches("user\\d+@gmail\\.com")) {

                    String number = email
                            .replace("user", "")
                            .replace("@gmail.com", "");

                    maxSequence = Math.max(
                            maxSequence, Integer.parseInt(number));
                }
            }

            return maxSequence + 1;

        } catch (IOException e) {
            throw new RuntimeException(
                    "Unable to determine next sequence.", e);
        }
    }
}