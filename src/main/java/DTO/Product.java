package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private String description;
    private Float price;
    private Integer stockQuantity;
    private String imageUrl;
    private Integer rating;
    private Long brandId;
    private Long categoryId;
    private List<Color> colors;
    private List<Tag> tags;
    private List<Size> sizes;
}
