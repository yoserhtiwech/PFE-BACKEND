package com.PFE.EndOfYearProject.Services;

import com.PFE.EndOfYearProject.dto.CallsDto;
import com.PFE.EndOfYearProject.dto.SMSDto;

public interface twilioService {
    public void sendSms(SMSDto smsDto);


    public void makeCall(CallsDto callsDto);
}
