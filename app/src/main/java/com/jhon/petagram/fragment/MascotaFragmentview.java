package com.jhon.petagram.fragment;

import com.jhon.petagram.adaptadores.ContactoAdapter;
import com.jhon.petagram.adaptadores.MascotaAdapter;
import com.jhon.petagram.pojo.Mascota;

import java.util.ArrayList;

public interface MascotaFragmentview {
    public void generargridLayout();
    public MascotaAdapter crearmascota (ArrayList<Mascota> mascotas);
    public void InicializarAdaptadorRV1(MascotaAdapter mascotaAdapter);
}
