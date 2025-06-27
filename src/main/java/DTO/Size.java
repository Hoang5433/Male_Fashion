package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Size {
    private Long id;
    private String sizeName;
    private String description;
}
