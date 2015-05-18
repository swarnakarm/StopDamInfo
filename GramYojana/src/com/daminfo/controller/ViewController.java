package com.daminfo.controller;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.daminfo.common.util.AppLogger;
import com.daminfo.common.util.UIConstants;

@Controller
public class ViewController {

	
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "StopDAM Welcome Page.");
		model.addObject("message", "This is welcome page!");
		String modelName = "home";
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Collection<GrantedAuthority> roles = user.getAuthorities(); 
		for(GrantedAuthority gAuth : roles){
			AppLogger.getLogger().debug("Authority -- > "+gAuth.getAuthority());
			if(gAuth.getAuthority().equals(UIConstants.ROLE_ADMIN)){
				modelName = "redirect:admin";
			}
		}
		model.setViewName(modelName);
		return model;
	}
 
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {
 
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}
 
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");
 
		return model;
	}
	
	
	@RequestMapping(value = "/admin" , method = RequestMethod.GET)
	public ModelAndView welcomeAdminPage() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "StopDAM Welcome Page.");
		model.addObject("message", "This is welcome page!");
		String modelName = "redirect:home";
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Collection<GrantedAuthority> roles = user.getAuthorities(); 
		for(GrantedAuthority gAuth : roles){
			AppLogger.getLogger().debug("Authority -- > "+gAuth.getAuthority());
			if(gAuth.getAuthority().equals(UIConstants.ROLE_ADMIN)){
				modelName = "admin";
			}
		}
		model.setViewName(modelName);
		return model;
	}
}
