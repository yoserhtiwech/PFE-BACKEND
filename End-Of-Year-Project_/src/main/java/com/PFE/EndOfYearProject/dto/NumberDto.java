package com.PFE.EndOfYearProject.dto;

import com.PFE.EndOfYearProject.models.Users;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NumberDto {
    private Integer id;
    private Integer num;
    private UserDto user;
}
