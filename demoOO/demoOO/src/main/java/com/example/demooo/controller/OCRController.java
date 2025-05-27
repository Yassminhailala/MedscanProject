package com.example.demooo.controller;

import com.example.demooo.entity.Medicament;
import com.example.demooo.repository.MedicamentRepository;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.List;

@RestController
@RequestMapping("/api/ocr")
@CrossOrigin(origins = "*")
public class OCRController {

    @Autowired
    private MedicamentRepository medicamentRepository;

    @PostMapping("/scan")
    public ResponseEntity<?> scanAndSearch(@RequestParam("image") MultipartFile image) {
        try {
            BufferedImage bufferedImage = ImageIO.read(image.getInputStream());

            Tesseract tesseract = new Tesseract();
            tesseract.setDatapath("tessdata"); // Path to your tessdata folder
            tesseract.setLanguage("eng");

            String result = tesseract.doOCR(bufferedImage).toLowerCase();

            // Search logic: find any medication where brandName or activeIngredients are mentioned in the OCR result
            List<Medicament> allMeds = medicamentRepository.findAll();
            for (Medicament med : allMeds) {
                if ((med.getBrandName() != null && result.contains(med.getBrandName().toLowerCase())) ||
                        (med.getActiveIngredients() != null && result.contains(med.getActiveIngredients().toLowerCase()))) {
                    return ResponseEntity.ok(med);
                }
            }

            return ResponseEntity.ok("Aucun médicament correspondant trouvé.");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("OCR ou recherche échouée : " + e.getMessage());
        }
    }
}
