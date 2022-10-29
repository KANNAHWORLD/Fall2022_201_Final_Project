package restAPI.endPoints;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Authorization {

	
	//Authorizes a new login from a person
	@RequestMapping(value="/OAuth")
	@ResponseBody
	public String OAuth()
	{
		return "Authorization";
	}
	
	
	
}
