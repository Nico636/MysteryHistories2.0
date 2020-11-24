package com.damani.mysteryhistories20.Tipos;

import java.io.Serializable;

public class ListElementMuseo implements Serializable {
    private String nombre;
    private String direccion;
    private String valor;
    private String icono;
    private String historia;
    private String horario;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String telefono;
    private String ubicacion;

    public ListElementMuseo(String nombre, String direccion, String valor, String icono, String historia, String horario, String img1, String img2, String img3, String img4, String telefono, String ubicacion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.valor = valor;
        this.icono = icono;
        this.historia = historia;
        this.horario = horario;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.img4 = img4;
        this.telefono = telefono;
        this.ubicacion = ubicacion;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getImg4() {
        return img4;
    }

    public void setImg4(String img4) {
        this.img4 = img4;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }
}
