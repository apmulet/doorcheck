package com.doorcheck;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DoorController {

	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}
}
