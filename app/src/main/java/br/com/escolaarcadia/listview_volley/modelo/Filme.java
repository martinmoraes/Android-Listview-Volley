package br.com.escolaarcadia.listview_volley.modelo;

import java.util.ArrayList;

/**
 * Created by Martin on 25/08/2015.
 */
public class Filme {
    private String titulo;
    private String imagemUrl;
    private int ano;
    private double nota;
    private ArrayList<String> genero;

    public Filme() {
    }

    public Filme(String nome, String imagemUrl, int ano, double nota,
                 ArrayList<String> genero) {
        this.titulo = nome;
        this.imagemUrl = imagemUrl;
        this.ano = ano;
        this.nota = nota;
        this.genero = genero;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String nome) {
        this.titulo = nome;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public ArrayList<String> getGenero() {
        return genero;
    }

    public void setGenero(ArrayList<String> genero) {
        this.genero = genero;
    }

}
