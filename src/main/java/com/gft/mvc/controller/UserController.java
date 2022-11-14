package com.gft.mvc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gft.mvc.security.model.User;
import com.gft.mvc.services.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(path = "form")
	public ModelAndView novoUser() {
		
		ModelAndView mv = new ModelAndView("user/form.html");
		mv.addObject("user", new User());
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "form")
	public ModelAndView salvarUser(@Valid User user, BindingResult bindingResult) {
		
		
		ModelAndView mv = new ModelAndView("user/form.html");
		
		boolean novo = true;
		
		if(user.getId() != null) {
			novo = false;
		}
		
		if(bindingResult.hasErrors()) {
			mv.addObject("user", user);
			return mv;
		}
		
		User userSalva = userService.salvarUser(user);
		
		if(novo) {
			mv.addObject("user", new User());
		} else {
			mv.addObject("user", userSalva);
		}
		
		mv.addObject("mensagem", "Usuário salvo com sucesso.");
		
		return mv;
	}
}