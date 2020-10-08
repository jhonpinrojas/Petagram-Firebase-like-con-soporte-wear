package com.jhon.petagram.presentador;

import android.content.Context;
import com.jhon.petagram.database.Constructorcontactos;
import com.jhon.petagram.fragment.ReciclerviewFragmentview;

import com.jhon.petagram.pojo.Contacto;


import java.util.ArrayList;

public class RecyclerViewPresentador implements IRecyclerViewPresentador{
    private ReciclerviewFragmentview reciclerviewFragmentview;
    private Context context;
    private Constructorcontactos constructorcontactos;
    private ArrayList<Contacto> contactos;

        public RecyclerViewPresentador(ReciclerviewFragmentview reciclerviewFragmentview, Context context) {
        this.reciclerviewFragmentview = reciclerviewFragmentview;
        this.context = context;
        obtenercontactosbasedatos();
    }


    @Override
    public void obtenercontactosbasedatos() {
        constructorcontactos = new Constructorcontactos(context);
        contactos=constructorcontactos.obtenerdatos();
        mostrarcontactosRv();
    }

    @Override
    public void mostrarcontactosRv() {
        reciclerviewFragmentview.InicializarAdaptadorRV(reciclerviewFragmentview.crearadaptador(contactos));
        reciclerviewFragmentview.generarLinearLayout();
    }
}

