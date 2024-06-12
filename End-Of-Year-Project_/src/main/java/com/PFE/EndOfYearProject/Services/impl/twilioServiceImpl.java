package com.PFE.EndOfYearProject.Services.impl;

import com.PFE.EndOfYearProject.Services.twilioService;
import com.PFE.EndOfYearProject.dto.CallsDto;
import com.PFE.EndOfYearProject.dto.SMSDto;
import org.springframework.stereotype.Service;

@Service
public class twilioServiceImpl implements twilioService {
    @Override
    public void sendSms(SMSDto smsDto) {
        
    }

    @Override
    public void makeCall(CallsDto callsDto) {

    }
}
