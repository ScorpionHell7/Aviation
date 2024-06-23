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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

                Iterator<Cell> cellsInRow = currentRow.iterator();
                Person person = new Person();

                int cellIndex = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIndex) {
                        case 0:
                            person.setName(currentCell.getStringCellValue());
                            break;
                        case 1:
                            person.setAge((int) currentCell.getNumericCellValue());
                            break;
                        case 2:
                            person.setEmail(currentCell.getStringCellValue());
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
