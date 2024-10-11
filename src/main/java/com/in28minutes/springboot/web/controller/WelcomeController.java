package com.in28minutes.springboot.web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

	/**
	* Displays the welcome page for the logged-in user.
	* 
	* @param model The ModelMap object to add attributes to the model
	* @return The name of the view to be rendered ("welcome")
	*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showWelcomePage(ModelMap model) {
		model.put("name", getLoggedinUserName());
		return "welcome";
	}

	/**
	* Retrieves the username of the currently logged-in user.
	* 
	* This method accesses the SecurityContext to obtain the authenticated principal.
	* If the principal is an instance of UserDetails, it returns the username.
	* Otherwise, it returns the string representation of the principal.
	*
	* @return The username of the logged-in user as a String
	*/
	private String getLoggedinUserName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		
		return principal.toString();
	}

}
