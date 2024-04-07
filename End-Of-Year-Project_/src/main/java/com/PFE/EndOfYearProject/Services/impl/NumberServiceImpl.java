package com.PFE.EndOfYearProject.Services.impl;

import com.PFE.EndOfYearProject.Services.NumberService;
import com.PFE.EndOfYearProject.dto.NumberDto;
import com.PFE.EndOfYearProject.models.Numbers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumberServiceImpl implements NumberService {
    @Override
    public List<Numbers> findAllNumbers() {
        return null;
    }

    @Override
    public Numbers saveNumber(NumberDto numberDto) {
        return null;
    }

    @Override
    public NumberDto findNumberById(long numberId) {
        return null;
    }
}
