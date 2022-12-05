package com.bcafinance.gjwaspringbootjpa.utils;


import com.bcafinance.gjwaspringbootjpa.models.Applicants;
import com.bcafinance.gjwaspringbootjpa.models.Citizen;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public static boolean isCsv(MultipartFile multipartFile)
    {
        if(!ConstantMessage.CONTENT_TYPE_CSV.equals(multipartFile.getContentType()))
        {
            return false;
        }
        return true;
    }

    public static List<Citizen> csvToCitizenData(InputStream inputStream) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        CSVParser csvParser = new CSVParser(bufferedReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().
                        withIgnoreHeaderCase().
                        withTrim()
        );
        List<Citizen> lsCitizens = new ArrayList<Citizen>();
        try {

            Iterable<CSVRecord> iterRecords = csvParser.getRecords();

            for (CSVRecord record : iterRecords) {
                Citizen citizen = new Citizen();
                citizen.setFullName(record.get("FullName"));
                citizen.setAddress(record.get("Address"));
                citizen.setIdCardNumber(record.get("IDCardNumber"));
                citizen.setBirthDate(LocalDate.parse(record.get("BirthDate")));
                lsCitizens.add(citizen);
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {

            if (!csvParser.isClosed()) {
                csvParser.close();
            }
            return lsCitizens;
        }
    }
    public static List<Applicants> csvtoApplicantData(InputStream inputStream) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        CSVParser csvParser = new CSVParser(bufferedReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().
                        withIgnoreHeaderCase().
                        withTrim()
        );
        List<Applicants> lsApplicant = new ArrayList<Applicants>();
        try {

            Iterable<CSVRecord> iterRecords = csvParser.getRecords();

            for (CSVRecord record : iterRecords) {
                Applicants applicant = new Applicants();
                applicant.setTitle(record.get("Title"));
                applicant.setFirstName(record.get("FirstName"));
                applicant.setLastname(record.get("LastName"));
                applicant.setAvatar(record.get("Avatar"));
                applicant.setLanguage(record.get("Language"));
                applicant.setSkill(record.get("Skill"));
                applicant.setUniversity(record.get("University"));
                lsApplicant.add(applicant);
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {

            if (!csvParser.isClosed()) {
                csvParser.close();
            }
            return lsApplicant;
        }
    }

}