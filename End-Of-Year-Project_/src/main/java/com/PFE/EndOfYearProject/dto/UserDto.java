package com.PFE.EndOfYearProject.dto;

import com.PFE.EndOfYearProject.models.Numbers;
import com.PFE.EndOfYearProject.models.roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Integer id ;
    private String fullname;
    private String email;
    private String password;
    private String poste ;
    private String roles;
    private Integer numbers;
    private String groupe;
    private LocalDateTime createdDate;
}
