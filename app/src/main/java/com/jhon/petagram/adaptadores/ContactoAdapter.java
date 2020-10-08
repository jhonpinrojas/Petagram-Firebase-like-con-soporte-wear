package com.jhon.petagram.adaptadores;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jhon.petagram.database.Constructorcontactos;
import com.jhon.petagram.pojo.Contacto;
import com.jhon.petagram.R;

import java.util.ArrayList;

public class ContactoAdapter extends RecyclerView.Adapter<ContactoAdapter.ContactoViewHolder>
{
    ArrayList <Contacto> contactos;
    Activity activity;

    public ContactoAdapter (ArrayList<Contacto> contactos,Activity activity) {
        this.contactos = contactos;
        this.activity = activity;
    }
    //inflar el layout y lo pasara al vieholder para que el obtena cada elemento view
    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_contacto,parent,false);// inflar la lista y crear los cardview
        //Asociar layout card_view a nuestro recicler view mediante el adapter
        return new ContactoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactoViewHolder holder, int position) {
        //setear los valores de la lista de contactos,asocia cada elemento de la lista con cada view
        final Contacto contacto = contactos.get(position);

        holder.imageView.setImageResource(contacto.getFoto());
        holder.name.setText(contacto.getNombre());
        holder.like.setText(String.valueOf(contacto.getLike())+ "" + " ");
        holder.imagebuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,"Diste like " + contacto.getNombre(),Toast.LENGTH_SHORT).show();
                Constructorcontactos constructorcontactos = new Constructorcontactos(activity);
                constructorcontactos.darlike(contacto);
                holder.like.setText(constructorcontactos.obtenerlikescontacto(contacto) + "" +" ");

            }
        });
    }

    @Override
    public int getItemCount() { //cantidad de elememntos que contienela lista
        return contactos.size();
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView name;
        private ImageButton imagebuton;
        public TextView like;
        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView   = (ImageView) itemView.findViewById(R.id.imageView);
            name        = itemView.findViewById(R.id.name);
            imagebuton = itemView.findViewById(R.id.imagebuton);
            like      = itemView.findViewById(R.id.likenumber);

        }
    }
}
