package com.jhon.petagram.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jhon.petagram.R;
import com.jhon.petagram.adaptadores.MascotaAdapter;
import com.jhon.petagram.pojo.Mascota;
import com.jhon.petagram.presentador.IMascotaViewPresentador;
import com.jhon.petagram.presentador.MascotaPresentador;


import java.util.ArrayList;

public class InstagramFragment extends Fragment implements MascotaFragmentview  {
    private RecyclerView listamascotas;
    ArrayList<Mascota> mascotas;
    private IMascotaViewPresentador presenter1 ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_mascota_favorita,container,false);
        listamascotas = v.findViewById(R.id.ListaM);
        presenter1= new MascotaPresentador(this,getContext());
        return v;
    }

    @Override
    public void generargridLayout() {
        GridLayoutManager glm = new GridLayoutManager(getContext() ,3 );
        listamascotas.setLayoutManager(glm);
    }



    @Override
    public MascotaAdapter crearmascota(ArrayList<Mascota> mascotas) {
        MascotaAdapter mascotaAdapter = new MascotaAdapter(mascotas,getActivity());
        return mascotaAdapter;
    }

    @Override
    public void InicializarAdaptadorRV1(MascotaAdapter mascotaAdapter) {
        listamascotas.setAdapter(mascotaAdapter);
    }

}
