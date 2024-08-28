package com.PFE.EndOfYearProject.Services;

import com.PFE.EndOfYearProject.dto.CallsDto;
import com.PFE.EndOfYearProject.dto.SMSDto;

import java.net.URISyntaxException;

public interface twilioService {
    public void sendSms(SMSDto smsDto);
    public void sendNotification(SMSDto smsDto);
    public void recieveSms(SMSDto smsDto);
    public void recordcall(CallsDto callsDto);
    public void makeCall(CallsDto callsDto) throws URISyntaxException;
    public void recieveCall(CallsDto callsDto);

}
