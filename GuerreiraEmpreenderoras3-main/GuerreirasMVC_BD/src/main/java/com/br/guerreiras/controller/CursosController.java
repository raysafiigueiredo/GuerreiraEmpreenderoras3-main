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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ResponseBody;


import com.br.guerreiras.model.Cursos;
import com.br.guerreiras.repository.CursosRepository;

@Controller
@RequestMapping("/novocurso")
public class CursosController {

    @Autowired
    private CursosRepository cursosRepository;

    @GetMapping("/novocurso")
    public ModelAndView novocurso() {
        ModelAndView page = new ModelAndView("novocurso/novocurso");
        page.addObject("cursos", new Cursos());
        return page;
    }
    

    @PostMapping({"/salvarcurso", "/{id}/editar"})
    public ModelAndView salvarCurso(@ModelAttribute Cursos curso, @RequestParam("imagem") MultipartFile imagem) {
        try {
            curso.setImagem(imagem);
            // Lógica para salvar no banco de dados
            Cursos cursoSalvo = cursosRepository.save(curso);

            // Você pode adicionar lógica adicional aqui, se necessário

            // Redirecionar para a página de sucesso ou outra página desejada
            ModelAndView page = new ModelAndView("redirect:/novocurso/listarcurso");
            return page;
        } catch (IOException e) {
            // Lidar com a exceção, por exemplo, redirecionar para uma página de erro
            ModelAndView errorPage = new ModelAndView("error");
            errorPage.addObject("error", "Erro ao processar a imagem.");
            return errorPage;
        }
    }
    
    @GetMapping("/{id}")
	public ModelAndView detalhar(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("novocurso/detalhescurso.html");
 
		Cursos cursos = cursosRepository.getReferenceById(id);
		modelAndView.addObject("cursos", cursos);
 
		return modelAndView;
	}
    
    
    @GetMapping("/listarcurso")
    public ModelAndView listarCursos() {
         //Buscar todos os cursos do banco de dados
        List<Cursos> cursos = cursosRepository.findAll();

         //Retornar uma página que exibe a lista de cursos
        ModelAndView page = new ModelAndView("novocurso/listarcurso");
       page.addObject("cursos", cursos);
       return page;    
    }

    
    @GetMapping(value = "/imagem/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] exibirImagem(@PathVariable("id") Long id) {
        Cursos curso = this.cursosRepository.getById(id);
        return curso.getImagemBytes();
    }
    
    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("novocurso/editarcurso");
    
        Cursos cursos = cursosRepository.getReferenceById(id);
        modelAndView.addObject("cursos", cursos);
    
        return modelAndView;
    } 
   
    @GetMapping("/{id}/excluir")
    public ModelAndView excluir(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/novocurso/listarcurso");

        cursosRepository.deleteById(id);

        return modelAndView;
        
        
     
    }
}
