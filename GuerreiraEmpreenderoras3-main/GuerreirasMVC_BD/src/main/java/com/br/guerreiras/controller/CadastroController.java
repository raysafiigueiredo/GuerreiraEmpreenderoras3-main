package com.br.guerreiras.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.guerreiras.model.Cadastro;
import com.br.guerreiras.repository.CadastroRepository;


@Controller
@RequestMapping("/cadastro")
public class CadastroController {

	@Autowired
	private CadastroRepository cadastroRepository;
	
	@GetMapping 
	public ModelAndView listar() {
		ModelAndView page = new ModelAndView("cadastro/listar");
		List<Cadastro> cadastros = cadastroRepository.findAll();
		page.addObject("cadastros", cadastros);
		return page;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView page = new ModelAndView("cadastro/cadastro");
		page.addObject("cadastro", new Cadastro());
		return page;
	}

	@PostMapping({"/cadastrar", "/{id}/editar"})
	public ModelAndView cadastrar(@ModelAttribute Cadastro cadastro) {
	    ModelAndView modelAndView = new ModelAndView("redirect:/cadastro");

	    cadastroRepository.save(cadastro);

	    return modelAndView;
	}
	
	@GetMapping("/{id}")
	public ModelAndView detalhar(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("cadastro/detalhar.html");
 
		Cadastro cadastro = cadastroRepository.getReferenceById(id);
		modelAndView.addObject("cadastro", cadastro);
 
		return modelAndView;
	}
	
	@GetMapping("/{id}/editar")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("cadastro/edicao");
 
		Cadastro cadastro = cadastroRepository.getReferenceById(id);
		modelAndView.addObject("cadastro", cadastro);
 
		return modelAndView;
	}
 
	@GetMapping("/{id}/excluir")
	public ModelAndView excluir(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/cadastro");
 
		cadastroRepository.deleteById(id);
 
		return modelAndView;
	}
}
