package com.example.demooo;

import com.example.demooo.service.CSVImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.InputStream;

@SpringBootApplication
public class DemoOoApplication implements CommandLineRunner {

    @Autowired
    private CSVImportService importService;

    public static void main(String[] args) {
        SpringApplication.run(DemoOoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("drugs.csv");
        if (inputStream != null) {
            importService.importCSV(inputStream);
            System.out.println("CSV data imported successfully.");
        } else {
            System.out.println("drugs.csv not found in resources folder.");
        }
    }
}
