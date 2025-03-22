package com.himusharier.ecombackend.dto;

import com.himusharier.ecombackend.model.Product;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Data
public class ProductRequestDto {
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;
    private LocalDate releaseDate;
    private boolean available;
    private int quantity;
    private String imageName;
    private String imageType;
    MultipartFile imageFile;

    public static Product toProduct(ProductRequestDto dto) {
        try {
            // Convert releaseDate string to java.util.Date
//            Date releaseDate = null;
//            if (dto.getReleaseDate() != null) {
//                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//                releaseDate = sdf.parse(dto.getReleaseDate());
//            }

            // Build the Product entity
            return Product.builder()
                    .name(dto.getName())
                    .brand(dto.getBrand())
                    .price(dto.getPrice())
                    .category(dto.getCategory())
                    .description(dto.getDescription())
                    .releaseDate(dto.getReleaseDate())  // Setting the Date type for releaseDate
                    .available(dto.isAvailable())
                    .quantity(dto.getQuantity())
                    // Handle the image file if it's provided
                    .imageName(dto.imageFile != null ? dto.imageFile.getOriginalFilename() : null)  // Get image name from MultipartFile
                    .imageType(dto.imageFile != null ? dto.imageFile.getContentType() : null)  // Get image type from MultipartFile
                    .imageData(dto.imageFile != null ? dto.imageFile.getBytes() : null)  // Get image bytes from MultipartFile
                    .build();
        } catch (IOException e) {
            // Handle the error for file processing
            e.printStackTrace();
            return null;  // Or handle this exception based on your requirements
        }
    }
}
