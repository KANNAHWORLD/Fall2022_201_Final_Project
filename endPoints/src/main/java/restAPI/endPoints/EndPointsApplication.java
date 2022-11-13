package restAPI.endPoints;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;


// This is the main entry point of the spring boot application

// DO NOT CHANGE THIS FILE, IT IS NOT NEEDED

@SpringBootApplication
@EnableAutoConfiguration
public class EndPointsApplication {
	public static void main(String[] args) {
		SpringApplication.run(EndPointsApplication.class, args);
	}

}

// This is for testing, change is not needed
// EVERYTHING BELOW THIS POINT WILL EVENTUALLY BE 
// DELETED

@Controller
class test2{
	@PostMapping("/create")
	@ResponseBody
	void createProduct(@RequestBody simpleTestJson STJ) 
	{
	    // custom logic
	
		System.out.println(STJ.x);
	}
}

@Controller
class HelloController {

	@GetMapping("/")
	@ResponseBody
	public String index() {
		return "Greetings from Spring Boot!";
	}

}

class simpleTestJson
{
//	public void setx(String x)
//	{
//		this.x = x;
//	}
	public String x;
}
