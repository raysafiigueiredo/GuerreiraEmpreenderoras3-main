package com.br.guerreiras.model;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "cursos") // Adicione o nome da tabela, se necessário
public class Cursos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome_curso;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String valor;
    
    
    @Lob
    @Column(name = "imagem", columnDefinition = "LONGBLOB")
    private byte[] imagemBytes;
    
    public void setImagem(MultipartFile imagem) throws IOException {
        this.imagemBytes = imagem.getBytes();
    }
    

    // Novo campo 'nivel' como um enum
    public enum Nivel {
        Fácil,
        Intermediário,
        Difícil
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Nivel nivel;

    // Getter e Setter para 'nivel'
    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    // Outros métodos e atributos

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome_curso() {
        return nome_curso;
    }

    public void setNome_curso(String nome_curso) {
        this.nome_curso = nome_curso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public byte[] getImagemBytes() {
        return imagemBytes;
    }

    public void setImagemBytes(byte[] imagemBytes) {
        this.imagemBytes = imagemBytes;
    }

    public Cursos() {
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nome_curso == null) ? 0 : nome_curso.hashCode());
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((valor == null) ? 0 : valor.hashCode());
        result = prime * result + ((nivel == null) ? 0 : nivel.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Cursos other = (Cursos) obj;

        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;

        if (nome_curso == null) {
            if (other.nome_curso != null)
                return false;
        } else if (!nome_curso.equals(other.nome_curso))
            return false;

        if (descricao == null) {
            if (other.descricao != null)
                return false;
        } else if (!descricao.equals(other.descricao))
            return false;

        if (valor == null) {
            if (other.valor != null)
                return false;
        } else if (!valor.equals(other.valor))
            return false;

        if (nivel == null) {
            if (other.nivel != null)
                return false;
        } else if (!nivel.equals(other.nivel))
            return false;

        return true;
    }

    public Cursos(Long id, String nome_curso, String descricao, String valor, Nivel nivel, byte[] imagemBytes) {
        this.id = id;
        this.nome_curso = nome_curso;
        this.descricao = descricao;
        this.valor = valor;
        this.nivel = nivel;
        this.imagemBytes = imagemBytes;
    }

    @Override
    public String toString() {
        return "Cursos [id=" + id + ", nome_curso=" + nome_curso + ", descricao=" + descricao + ", valor=" + valor
                + ", nivel=" + nivel + "]";
    }
}



// IMAGEM DO CURSO
// @Column(columnDefinition = "longblob")
 //private byte[] imagem;
 
