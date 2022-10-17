package restAPI.endPoints;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.*;
//import org.springframework.boot.web.server.*;
//import org.springframework.boot.*;
//import java.net.http.*;
//import java.net.*;
import org.springframework.web.bind.annotation.*;



@EnableAutoConfiguration
@RestController
public class EndPointsApplication {
	
	@RequestMapping(value="/hello", method = RequestMethod.GET)
	@ResponseBody
    public String home() {
		System.out.println("Hello World! line hit by requests");
        return "Hello World!";
    }
	
	public static void main(String[] args) {
		SpringApplication.run(EndPointsApplication.class, args);
	}

}
