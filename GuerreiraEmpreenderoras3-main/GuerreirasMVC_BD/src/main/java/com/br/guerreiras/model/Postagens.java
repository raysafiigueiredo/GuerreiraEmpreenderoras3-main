package com.br.guerreiras.model;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "postagens") // Adicione o nome da tabela, se necessário
public class Postagens {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false, length = 1000)
	private String descricao;

	@Column(nullable = false)
	private String area;

	@Column(nullable = false)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDateTime data = LocalDateTime.now(); // ou outro valor padrão

	@Lob
	@Column(name = "imagem", columnDefinition = "LONGBLOB")
	private byte[] imagemBytes;

	public void setImagem(MultipartFile imagem) throws IOException {
		this.imagemBytes = imagem.getBytes();
	}

	// Outros métodos e atributos

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public byte[] getImagemBytes() {
		return imagemBytes;
	}

	public void setImagemBytes(byte[] imagemBytes) {
		this.imagemBytes = imagemBytes;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Postagens() {

	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Postagens other = (Postagens) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;

		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;

		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;

		return true;
	}

	public Postagens(Long id, String nome, String descricao, String area, LocalDateTime data, byte[] imagemBytes) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.data = data;
		this.area = area;
		this.imagemBytes = imagemBytes;
	}

	@Override
	public String toString() {
		return "Postagens [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", data=" + data
				+ ", imagemBytes=" + Arrays.toString(imagemBytes) + ",area=" + area + "]";
	}

}
