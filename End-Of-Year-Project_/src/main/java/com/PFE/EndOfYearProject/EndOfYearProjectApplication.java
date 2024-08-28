package com.PFE.EndOfYearProject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
//import org.springframework.boot.SpringApplication;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
//import com.twilio.rest.lookups.v1.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
//import com.twilio.rest.api.v2010.account.Call;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//import com.twilio.type.Twiml;
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class EndOfYearProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EndOfYearProjectApplication.class, args);}

}








