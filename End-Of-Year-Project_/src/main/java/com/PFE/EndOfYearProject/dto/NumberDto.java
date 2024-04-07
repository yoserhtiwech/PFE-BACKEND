package com.PFE.EndOfYearProject.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NumberDto {
    private long id;
    @NotEmpty(message = "Number should not be empty ")
    private long num;
    @NotEmpty(message = "Country should not be empty ")
    private String country;
    @NotEmpty(message = "Region should not be empty ")
    private String region;
    @NotEmpty(message = "Adress should not be empty ")
    private String address;
    @NotEmpty(message = "Type should not be empty ")
    private String type;
}
