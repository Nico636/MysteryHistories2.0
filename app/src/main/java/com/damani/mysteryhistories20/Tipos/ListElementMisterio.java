package com.damani.mysteryhistories20.Tipos;

import java.io.Serializable;

public class ListElementMisterio implements Serializable {
    private String titulo;
    private String icono;
    private String historia;
    private String img1;
    private String ubicacion;
    private String ciudad;

    public ListElementMisterio(String titulo, String icono, String historia, String img1, String ubicacion, String ciudad) {
        this.titulo = titulo;
        this.icono = icono;
        this.historia = historia;
        this.img1 = img1;
        this.ubicacion = ubicacion;
        this.ciudad = ciudad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
