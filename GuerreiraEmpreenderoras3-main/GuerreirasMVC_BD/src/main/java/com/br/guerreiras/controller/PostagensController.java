package com.br.guerreiras.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import com.br.guerreiras.model.Postagens;
import com.br.guerreiras.repository.PostagensRepository;

@Controller
@RequestMapping("/postagens")
public class PostagensController {
	
	@Autowired
    private PostagensRepository postagensRepository;
	
	
	   @GetMapping("/incluirPostagem")
	    public ModelAndView incluirPostagem() {
	        ModelAndView page = new ModelAndView("postagens/incluirPostagem");
	        page.addObject("postagens", new Postagens());
	        return page;
	    }
	    

	    @PostMapping({"/salvarPoste", "/{id}/editar"})
	    public ModelAndView salvarPoste(@ModelAttribute Postagens poste, @RequestParam("imagem") MultipartFile imagem) {
	        try {
	           poste.setImagem(imagem);
	           Postagens posteSalvo = postagensRepository.save(poste);

	            ModelAndView page = new ModelAndView("redirect:/postagens/listarPostagem");
	            return page;
	            
	        } catch (IOException e) {
	            ModelAndView errorPage = new ModelAndView("error");
	            errorPage.addObject("error", "Erro ao processar a imagem.");
	            return errorPage;
	        }
	    }
	    
		/*
		 * @GetMapping("/{id}") public ModelAndView detalhar(@PathVariable Long id) {
		 * ModelAndView modelAndView = new ModelAndView("novocurso/detalhescurso.html");
		 * 
		 * Postagens postagens = postagensRepository.getReferenceById(id);
		 * modelAndView.addObject("postagens", postagens);
		 * 
		 * return modelAndView; }
		 */
	    
	    
	    @GetMapping("/listarPostagem")
	    public ModelAndView listarPostagem() {
	         //Buscar todos os cursos do banco de dados
	        List<Postagens> postagens = postagensRepository.findAll();
	        

	         //Retornar uma página que exibe a lista de cursos
	        ModelAndView page = new ModelAndView("postagens/listarPostagem");
	       page.addObject("postagens", postagens);
	       return page;    
	    }

	    
	    @GetMapping(value = "/imagem/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	    @ResponseBody
	    public byte[] exibirImagem(@PathVariable("id") Long id) {
	        Postagens postagens = this.postagensRepository.getReferenceById(id);
	        return postagens.getImagemBytes();
	    }
	    
	    @GetMapping("/{id}/editar")
	    public ModelAndView editar(@PathVariable Long id) {
	        ModelAndView modelAndView = new ModelAndView("postagens/editarPostagens");
	    
	        Postagens postagens = postagensRepository.getReferenceById(id);
	        modelAndView.addObject("postagens", postagens);
	    
	        return modelAndView;
	    } 
	   
	    @GetMapping("/{id}/excluir")
	    public ModelAndView excluir(@PathVariable Long id) {
	        ModelAndView modelAndView = new ModelAndView("redirect:/postagens/listarPostagem");

	        postagensRepository.deleteById(id);

	        return modelAndView;
	        
	        
	     
	    }
	}

