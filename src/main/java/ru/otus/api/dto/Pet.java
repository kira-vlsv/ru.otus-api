package ru.otus.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Pet extends BaseDTO {
    private long id;
    private Category category;
    private String name;
    private String[] photoUrls;
    private Tag[] tags;
    private String status;
}
