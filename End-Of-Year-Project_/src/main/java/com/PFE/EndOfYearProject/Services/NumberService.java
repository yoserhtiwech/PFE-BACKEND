package com.PFE.EndOfYearProject.Services;

import com.PFE.EndOfYearProject.dto.NumberDto;
import com.PFE.EndOfYearProject.dto.UserDto;
import com.PFE.EndOfYearProject.models.Numbers;
import com.PFE.EndOfYearProject.models.Users;

import java.util.List;

public interface NumberService {
    List<Numbers> findAllNumbers();
    Numbers saveNumber(NumberDto numberDto);

    NumberDto findNumberById(long numberId );
}
