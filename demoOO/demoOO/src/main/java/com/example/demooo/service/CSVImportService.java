package com.example.demooo.service;

import com.example.demooo.entity.Medicament;
import com.example.demooo.repository.MedicamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class CSVImportService {

    @Autowired
    private MedicamentRepository medicamentRepository;

    public void importCSV(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip header
                }

                String[] fields = line.split(",");

                Medicament m = new Medicament();
                m.setApplicationNumber(fields[0]);
                m.setSponsorName(fields[1]);
                m.setBrandName(fields[2]);
                m.setProductNumber(fields[3]);
                m.setReferenceDrug(fields[4]);
                m.setActiveIngredients(fields[5]);
                m.setReferencedStandard(fields[6]);
                m.setDosageForm(fields[7]);
                m.setRoute(fields[8]);
                m.setMarketingStatus(fields[9]);

                medicamentRepository.save(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
