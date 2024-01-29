package com.br.guerreiras.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.br.guerreiras.model.Postagens;


public interface PostagensRepository extends JpaRepository<Postagens, Long> {
	
	 List<Postagens> findAll();

}