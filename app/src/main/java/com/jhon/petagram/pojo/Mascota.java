package com.jhon.petagram.pojo;

public class Mascota {

    private String id;
    private String nombrecompleto;
    private String fotoinst;
    private int likes =0; ;

    public String getFotoinst() {
        return fotoinst;
    }

    public void setFotoinst(String fotoinst) {
        this.fotoinst = fotoinst;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombrecompleto() {
        return nombrecompleto;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }



    public Mascota(String foto, String nombrecompleto, int like) {
        this.fotoinst=fotoinst;
        this.nombrecompleto = nombrecompleto;
        this.likes = likes;
    }

    public Mascota() {
    }


}
