package com.PFE.EndOfYearProject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CallsDto {
    public Integer id;
    public UserDto user;
    public ContactDto contactDto;
    private byte[] recording ;
}
