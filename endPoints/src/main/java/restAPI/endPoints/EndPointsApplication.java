package restAPI.endPoints;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EndPointsApplication {

	
	  @RequestMapping(value="/", method = RequestMethod.GET)
	  @ResponseBody 
	  public String home() 
	  {
		  System.out.println("Hello World! line hit by requests"); 
		  return "Hello World!"; 
	  }
	 

	public static void main(String[] args) {
		SpringApplication.run(EndPointsApplication.class, args);
	}

}
