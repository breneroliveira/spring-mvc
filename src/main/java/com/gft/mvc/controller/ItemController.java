package com.gft.mvc.controller;

import javax.validation.Valid;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gft.mvc.entities.Receita;
import com.gft.mvc.services.IngredienteService;
import com.gft.mvc.services.ReceitaService;
import com.gft.mvc.services.UnidadeMedidaService;

@Controller
@RequestMapping("item")
public class ItemController {
	
	@Autowired
	private ReceitaService receitaService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private UnidadeMedidaService unidadeMedidaService;

	@RequestMapping(path = "form-receita")
	public ModelAndView novaReceita() {
		
		ModelAndView mv = new ModelAndView("item/form.html");
		mv.addObject("receita", new Receita());
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "form-receita")
	public ModelAndView salvarReceita(@Valid Receita receita, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("item/form.html");
		
		boolean novo = true;
		
		if(receita.getId() != null) {
			novo = false;
		}
		
		if(bindingResult.hasErrors()) {
			mv.addObject("receita", receita);
			return mv;
		}
		
		Receita receitaSalva = receitaService.salvarReceita(receita);
		
		if(novo) {
			mv.addObject("receita", new Receita());
		} else {
			mv.addObject("receita", receitaSalva);
		}
		
		mv.addObject("mensagem", "Receita salva com sucesso.");
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "form")
	public ModelAndView salvarItem(@Valid Item item, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("item/form.html");
		mv.addObject("listaIngrediente", ingredienteService.listarTodosIngrediente());
		mv.addObject("listaUnidadeMedida", unidadeMedidaService.listarTodasUnidadeMedida());
		
		return mv;
	}
	
	@RequestMapping
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("item/mensagem.html");
		
		return mv;
	}
}