package com.PFE.EndOfYearProject.Services.impl;

import com.PFE.EndOfYearProject.Services.twilioService;
import com.PFE.EndOfYearProject.dto.CallsDto;
import com.PFE.EndOfYearProject.dto.SMSDto;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.Twiml;
import java.net.URI;
import java.net.URISyntaxException;



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
    public void makeCall(CallsDto callsDto) throws URISyntaxException {
        Twilio.init("ACf6bff304d69310a7cd68a1ff6079b223", "e7ec745df7cee6c309d15ad6026797d1");
        Call call = Call.creator(
                    //  new PhoneNumber(callsDto.getUser().getNumbers()),
                     //  new PhoneNumber(callsDto.getContactDto().getNum()),
                        new PhoneNumber("+21699725005"),
                        new PhoneNumber("+12514281224"),
                        new URI("https://demo.twilio.com/docs/voice.xml"))
                .create();
               // .getTo(true);
        System.out.println(call.getStatus());
    }

    @Override
    public void recieveCall(CallsDto callsDto) {

    }

}

