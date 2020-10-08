package com.jhon.petagram.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jhon.petagram.pojo.Contacto;
import com.jhon.petagram.adaptadores.ContactoAdapter;
import com.jhon.petagram.R;
import com.jhon.petagram.presentador.IRecyclerViewPresentador;
import com.jhon.petagram.presentador.RecyclerViewPresentador;

import java.util.ArrayList;


public class FragmentReciclerview extends Fragment implements ReciclerviewFragmentview {
    private RecyclerView ListaMascotas;
    ArrayList<Contacto> contactos;
    private IRecyclerViewPresentador presenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_reciclerview,container,false);

        ListaMascotas = v.findViewById(R.id.ListaMascotas);
        presenter= new RecyclerViewPresentador(this,getContext());
        return v;

    }


    public void generarLinearLayout() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        ListaMascotas.setLayoutManager(llm);
        //   GridLayoutManager glm = new GridLayoutManager(this ,2 );
    }

    public ContactoAdapter crearadaptador(ArrayList<Contacto> contactos) {
        ContactoAdapter adaptador  = new ContactoAdapter(contactos,getActivity());
        return adaptador;
    }

    public void InicializarAdaptadorRV(ContactoAdapter adaptador) {
        ListaMascotas.setAdapter(adaptador);
    }
}


