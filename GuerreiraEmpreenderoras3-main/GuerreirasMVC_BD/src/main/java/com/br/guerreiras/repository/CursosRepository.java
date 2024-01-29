package com.br.guerreiras.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.br.guerreiras.model.Cursos;


public interface CursosRepository extends JpaRepository<Cursos, Long> {
	
	 List<Cursos> findAll();

}
