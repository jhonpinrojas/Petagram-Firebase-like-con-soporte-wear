package com.jhon.petagram.resApi;

import com.jhon.petagram.pojo.Mascota;

import java.util.ArrayList;

public class MascotaResponse {

    ArrayList<Mascota> profilepet;

    public ArrayList<Mascota> getProfilepet() {
        return profilepet;
    }

    public void setProfilepet(ArrayList<Mascota> profilepet) {
        this.profilepet = profilepet;
    }
}
