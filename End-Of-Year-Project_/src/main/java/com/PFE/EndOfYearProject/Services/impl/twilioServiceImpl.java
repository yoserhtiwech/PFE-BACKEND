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
    public void sendNotification(SMSDto smsDto) {

    }

    @Override
    public void recieveSms(SMSDto smsDto) {

    }

    @Override
    public void recordcall(CallsDto callsDto) {

    }

    @Override
    public void makeCall(CallsDto callsDto) {

    }

    @Override
    public void recieveCall(CallsDto callsDto) {

    }
}
