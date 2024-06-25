package com.akasa.aviation.service;

import com.akasa.aviation.model.Person;
import com.akasa.aviation.repository.PersonRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.io.InputStream;
import java.time.LocalDate;
//import java.sql.Date;
import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ExcelService {

    @Autowired
    private PersonRepository personRepository;

    private static final Logger logger = LoggerFactory.getLogger(ExcelService.class);

    public void save(MultipartFile file) {
        try {
            List<Person> persons = parseExcelFile(file.getInputStream());
            personRepository.saveAll(persons);
            logger.info("Successfully saved {} records to the database.", persons.size());
        } catch (IOException e) {
            throw new RuntimeException("Fail to store Excel data: " + e.getMessage());
        }
    }

    private List<Person> parseExcelFile(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<Person> persons = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // Skip header row
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Iterator<Cell> cellsInRow = currentRow.iterator();

                Person person = new Person();

                int cellIndex = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIndex) {
                        case 0:
                            person.setCrewname(currentCell.getStringCellValue());
                            break;
                        case 1:
                            person.setStaffnumber(currentCell.getStringCellValue());
                            break;
                        case 2:
                            person.setRanks(currentCell.getStringCellValue());
                            break;
                        case 3:
                            person.setQualificationcode(currentCell.getStringCellValue());
                            break;
                        case 4:
                            System.out.println(currentCell.getCellType());
//                            String ex = currentCell.getStringCellValue();
                            Date dateValue = (Date) currentCell.getDateCellValue();
                            String formatteddate = dateFormat.format(dateValue);
                            person.setExpirydate(formatteddate);


                            break;
                        case 5:
                            System.out.println(currentCell.getCellType());
                            Date dateValue1 = (Date) currentCell.getDateCellValue();
                            String formatteddate1 = dateFormat.format(dateValue1);
                            person.setToday(formatteddate1);

                            break;
                        case 6:
                            System.out.println(currentCell.getCellType());
                            double dataValue = (Double) currentCell.getNumericCellValue();
                            person.setDaysremaining((int) dataValue);
//                            if (currentCell.getCellType() == CellType.NUMERIC) {
//                            person.setDaysremaining(Integer.parseInt(String.valueOf(currentCell.getNumericCellValue())));
//                            }
                            break;
                        case 7:
                            person.setStatus(currentCell.getStringCellValue());
                            break;
                        case 8:
                            person.setPicforelease(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIndex++;
                }
                persons.add(person);
            }
            workbook.close();
            return persons;
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse Excel file: " + e.getMessage());
        }
    }
}
