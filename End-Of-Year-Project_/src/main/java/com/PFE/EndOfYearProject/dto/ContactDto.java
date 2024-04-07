package com.PFE.EndOfYearProject.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactDto {
    public long id ;
    @NotEmpty
    public long num;
    @NotEmpty
    public String username;
    @NotEmpty
    public String address;
}
