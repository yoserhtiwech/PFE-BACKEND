package com.PFE.EndOfYearProject.Services;

import com.PFE.EndOfYearProject.RequestResponse.NumberRequest;
import com.PFE.EndOfYearProject.RequestResponse.NumberResponse;
import com.PFE.EndOfYearProject.dto.NumberDto;
import com.PFE.EndOfYearProject.dto.UserDto;
import com.PFE.EndOfYearProject.models.Numbers;

import java.util.List;

public interface NumberService {
    Integer saveNumber(NumberRequest numberRequest);

    NumberDto findNumberById(long numberId );

    List<NumberResponse> getAllNumbers();
    List<NumberResponse> getAvailableNumbers();
    Numbers getUserNumber(UserDto user);
}
