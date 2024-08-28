package com.PFE.EndOfYearProject.Mapper;

import com.PFE.EndOfYearProject.dto.NumberDto;
import com.PFE.EndOfYearProject.models.Numbers;

import java.util.List;

public class NumberMapper {
    public static NumberDto convertToNumberDto(Numbers numbers) {
        NumberDto numberDto= NumberDto.builder().id(numbers.getId()).num(numbers.getNum()).build();
        return numberDto;
    }

    public static Numbers convertToNumber(NumberDto numbers) {
        return null;
    }
}
