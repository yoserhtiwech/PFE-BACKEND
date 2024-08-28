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
/*@Bean
public CorsFilter corsFilter() {
	CorsConfiguration corsConfiguration = new CorsConfiguration();
	corsConfiguration.setAllowCredentials(true);
	corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
	corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
			"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
			"Access-Control-Request-Method", "Access-Control-Request-Headers"));
	corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
			"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
	corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
	urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
	return new CorsFilter(urlBasedCorsConfigurationSource);
}}*/
	// Find your Account SID and Auth Token at twilio.com/console
	// and set the environment variables. See http://twil.io/secure
	//public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
	//public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

	//public static void main(String[] args) {Twilio.init("ACf6bff304d69310a7cd68a1ff6079b223", "e7ec745df7cee6c309d15ad6026797d1");Message message = Message.creator(					new com.twilio.type.PhoneNumber("+21699725005"), new com.twilio.type.PhoneNumber("+12514281224"),						"Where's Wallace?").create();System.out.println(message.getSid());}









