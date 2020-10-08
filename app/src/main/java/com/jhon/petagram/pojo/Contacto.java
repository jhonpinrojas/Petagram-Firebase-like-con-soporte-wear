package com.jhon.petagram.pojo;


public class Contacto {
    private int id;
    private String nombre;
    private String telefono;
    private String mail;
    private int foto;
    private int like =0;

    public Contacto() {

    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getLike() {
        return like;
    }

    public Contacto (int foto, String nombre,int like){
        this.foto=foto;
        this.nombre=nombre;
        this.like= like;
    }
    public Contacto(int foto, String nombre, String telefono, String mail,int like) {
        this.foto=foto;
        this.nombre = nombre;
        this.telefono = telefono;
        this.mail= mail;
        this.like= like;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


}
