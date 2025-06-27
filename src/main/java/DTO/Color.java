package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Color {
    private Long id;
    private String colorName;
    private String colorHex;
    private String imageUrl;
}

