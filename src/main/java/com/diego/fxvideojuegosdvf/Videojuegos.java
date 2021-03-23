/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.diego.fxvideojuegosdvf;

import java.time.LocalDate;

/**
 * 
 * 
 */
public class Videojuegos {

    private int id;
    private String nombre;
    private String descripcion;
    private String pegi;
    private boolean PS4;
    private boolean PC;
    private boolean XBox;
    private boolean NintendoSwitch;
    private LocalDate fechaLanzamiento;
    private String url;
    
    public Videojuegos() {
    }

    public Videojuegos(int id, String nombre, String descripcion, String pegi, boolean PS4, boolean PC, boolean XBox, boolean NintendoSwitch, LocalDate fechaLanzamiento, String url) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.pegi = pegi;
        this.PS4 = PS4;
        this.PC = PC;
        this.XBox = XBox;
        this.NintendoSwitch = NintendoSwitch;
        this.fechaLanzamiento = fechaLanzamiento;
        this.url = url;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isPS4() {
        return PS4;
    }

    public void setPS4(boolean PS4) {
        this.PS4 = PS4;
    }

    public boolean isPC() {
        return PC;
    }

    public void setPC(boolean PC) {
        this.PC = PC;
    }

    public boolean isXBox() {
        return XBox;
    }

    public void setXBox(boolean XBox) {
        this.XBox = XBox;
    }

    public boolean isNintendoSwitch() {
        return NintendoSwitch;
    }

    public void setNintendoSwitch(boolean NintendoSwitch) {
        this.NintendoSwitch = NintendoSwitch;
    }

    public String getPegi() {
        return pegi;
    }

    public void setPegi(String pegi) {
        this.pegi = pegi;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Videojuegos{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", pegi=" + pegi + ", PS4=" + PS4 + ", PC=" + PC + ", XBox=" + XBox + ", NintendoSwitch=" + NintendoSwitch + ", fechaLanzamiento=" + fechaLanzamiento + ", url=" + url + '}';
    }
    
}
