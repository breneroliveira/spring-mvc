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

import com.gft.mvc.entities.UnidadeMedida;
import com.gft.mvc.services.UnidadeMedidaService;

@Controller
@RequestMapping("unidade-medida")
public class UnidadeMedidaController {

	@Autowired
	private UnidadeMedidaService unidadeMedidaService;
	
	@RequestMapping(path = "form")
	public ModelAndView novaUnidadeMedida() {
		
		ModelAndView mv = new ModelAndView("unidade-medida/form.html");
		mv.addObject("unidadeMedida", new UnidadeMedida());
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "form")
	public ModelAndView salvarUnidadeMedida(@Valid UnidadeMedida unidadeMedida, BindingResult bindingResult) {	
		
		ModelAndView mv = new ModelAndView("unidade-medida/form.html");
		
		boolean novo = true;
		
		if(unidadeMedida.getId() != null) {
			novo = false;
		}
		
		if(bindingResult.hasErrors()) {
			mv.addObject("unidadeMedida", unidadeMedida);
			return mv;
		}
		
		UnidadeMedida unidadeMedidaSalva = unidadeMedidaService.salvarUnidadeMedida(unidadeMedida);
		
		if(novo) {
			mv.addObject("unidadeMedida", new UnidadeMedida());
		} else {
			mv.addObject("unidadeMedida", unidadeMedidaSalva);
		}
		
		mv.addObject("mensagem", "Unidade de medida salva com sucesso.");
		
		return mv;
	}
	
	@RequestMapping
	public ModelAndView listarUnidadeMedida(String nome) {
		
		ModelAndView mv = new ModelAndView("unidade-medida/listar.html");
		mv.addObject("lista", unidadeMedidaService.listarUnidadeMedida(nome));
		
		return mv;
	}
	
	@RequestMapping("/editar")
	public ModelAndView editarUnidadeMedida(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView("unidade-medida/form.html");
		UnidadeMedida unidadeMedida;
		
		try {
			unidadeMedida = unidadeMedidaService.obterUnidadeMedida(id);
		} catch (Exception e) {
			unidadeMedida = new UnidadeMedida();
			mv.addObject("mensagem", e.getMessage());
		}
		
		mv.addObject("unidadeMedida", unidadeMedida);
		
		return mv;
	}
	
	@RequestMapping("/excluir")
	public ModelAndView excluirUnidadeMedida(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/unidade-medida");
		
		try {
			unidadeMedidaService.excluirUnidadeMedida(id);
			redirectAttributes.addFlashAttribute("mensagem", "Unidade de medida excluída com sucesso.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir a unidade de medida. A mesma está "
												+ "sendo usada em uma receita por ao menos um ingrediente.");
		}
		
		return mv;
	}
}