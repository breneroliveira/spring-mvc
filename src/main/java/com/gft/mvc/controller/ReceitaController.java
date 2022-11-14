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

import com.gft.mvc.entities.Item;
import com.gft.mvc.entities.Receita;
import com.gft.mvc.services.IngredienteService;
import com.gft.mvc.services.ItemService;
import com.gft.mvc.services.ReceitaService;
import com.gft.mvc.services.UnidadeMedidaService;

@Controller
@RequestMapping("receita")
public class ReceitaController {

	@Autowired
	private ReceitaService receitaService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private UnidadeMedidaService unidadeMedidaService;
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(path = "form")
	public ModelAndView novaReceita() {
		
		ModelAndView mv = new ModelAndView("receita/form.html");
		mv.addObject("receita", new Receita());
		mv.addObject("item", new Item());
		mv.addObject("listaIngrediente", ingredienteService.listarTodosIngrediente());
		mv.addObject("listaUnidadeMedida", unidadeMedidaService.listarTodasUnidadeMedida());
		mv.addObject("listaItens", itemService.listarItem());
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "form")
	public ModelAndView salvarReceita(@Valid Receita receita, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("receita/form.html");
		
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
	
	@RequestMapping
	public ModelAndView listarReceita(String nome, String nomeIngrediente) {
		
		ModelAndView mv = new ModelAndView("receita/listar.html");
		mv.addObject("lista", receitaService.listarReceita(nome, nomeIngrediente));
		
		mv.addObject("nome", nome);
		mv.addObject("nomeIngrediente", nomeIngrediente);
		
		return mv;
	}
	
	@RequestMapping("/editar")
	public ModelAndView editarReceita(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView("receita/form.html");
		Receita receita;
		
		try {
			receita = receitaService.obterReceita(id);
		} catch (Exception e) {
			receita = new Receita();
			mv.addObject("mensagem", e.getMessage());
		}
		
		mv.addObject("receita", receita);
		
		return mv;
	}
	
	@RequestMapping("/mostrar")
	public ModelAndView mostrarReceita(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView("receita/view-receita.html");
		Receita receita;
		
		try {
			receita = receitaService.obterReceita(id);
		} catch (Exception e) {
			receita = new Receita();
			mv.addObject("mensagem", e.getMessage());
		}
		
		mv.addObject("receita", receita);
		
		return mv;
	}
	
	@RequestMapping("/excluir")
	public ModelAndView excluirReceita(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/receita");
		
		try {
			receitaService.excluirReceita(id);
			redirectAttributes.addFlashAttribute("mensagem", "Receita excluída com sucesso.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir a receita.");
		}
		
		return mv;
	}
}