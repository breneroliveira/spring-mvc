package com.gft.mvc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.mvc.entities.Ingrediente;
import com.gft.mvc.services.IngredienteService;

@Controller
@RequestMapping("ingrediente")
public class IngredienteController {

	@Autowired
	private IngredienteService ingredienteService;
	
	@RequestMapping(path = "form")
	public ModelAndView novoIngrediente() {
		
		ModelAndView mv = new ModelAndView("ingrediente/form.html");
		mv.addObject("ingrediente", new Ingrediente());
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "form")
	public ModelAndView salvarIngrediente(@Valid Ingrediente ingrediente, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("ingrediente/form.html");
		
		boolean novo = true;
		
		if(ingrediente.getId() != null) {
			novo = false;
		}
		
		if(bindingResult.hasErrors()) {
			mv.addObject("ingrediente", ingrediente);
			return mv;
		}
		
		Ingrediente ingredienteSalvo = ingredienteService.salvarIngrediente(ingrediente);
		
		if(novo) {
			mv.addObject("ingrediente", new Ingrediente());
		} else {
			mv.addObject("ingrediente", ingredienteSalvo);
		}
		
		mv.addObject("mensagem", "Ingrediente salvo com sucesso.");
		
		return mv;
	}
	
	@RequestMapping
	public ModelAndView listarIngrediente(String nome) {
		
		ModelAndView mv = new ModelAndView("ingrediente/listar.html");
		mv.addObject("lista", ingredienteService.listarIngrediente(nome));
		
		return mv;
	}
	
	@RequestMapping("/editar")
	public ModelAndView editarIngrediente(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView("ingrediente/form.html");
		Ingrediente ingrediente;
		
		try {
			ingrediente = ingredienteService.obterIngrediente(id);
		} catch (Exception e) {
			ingrediente = new Ingrediente();
			mv.addObject("mensagem", e.getMessage());
		}
		
		mv.addObject("ingrediente", ingrediente);
		
		return mv;
	}
	
	@RequestMapping("/excluir")
	public ModelAndView excluirIngrediente(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/ingrediente");
		
		try {
			ingredienteService.excluirIngrediente(id);
			redirectAttributes.addFlashAttribute("mensagem", "Ingrediente excluído com sucesso.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir o ingrediente. O mesmo está sendo usado em "
												+ "pelo menos uma receita.");
		}
		
		return mv;
	}
}