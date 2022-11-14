package com.gft.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gft.mvc.entities.Principal;
import com.gft.mvc.services.PrincipalService;

@Controller
@RequestMapping("principal")
public class PrincipalController {
	
	@Autowired
	private PrincipalService principalService;

	@RequestMapping
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("principal/home.html");
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "popula-BD")
	public ModelAndView populaBD() {
		
		ModelAndView mv = new ModelAndView("principal/popula-BD.html");
		mv.addObject("principal", new Principal());
		
		principalService.populaBD();
		
		return mv;
	}
}