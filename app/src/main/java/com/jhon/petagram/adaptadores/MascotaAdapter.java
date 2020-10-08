package com.jhon.petagram.adaptadores;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.jhon.petagram.Mascotas;
import com.jhon.petagram.R;
import com.jhon.petagram.pojo.Mascota;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder>
{
    ArrayList<Mascota> mascotas;
    Activity activity;

    public MascotaAdapter (ArrayList<Mascota> mascotas,Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }


    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_grid_contacto,parent,false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MascotaViewHolder holder, int position) {
        final Mascota mascota = mascotas.get(position);
        Picasso.with(activity)
                .load(mascota.getFotoinst())
                .placeholder(R.drawable.abejita)
                .into(holder.imageView);
        holder.like.setText(String.valueOf(mascota.getLikes())+"");
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(activity,mascota.getLikes(),Toast.LENGTH_SHORT).show();
                Intent x = new Intent(activity, Mascotas.class);
                x.putExtra("url", mascota.getFotoinst());
                x.putExtra("like", mascota.getLikes());
                activity.startActivity(x);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView name;
        private TextView like;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView   = (ImageView) itemView.findViewById(R.id.imgFoto);
            name        = itemView.findViewById(R.id.name);
            like       = itemView.findViewById(R.id.tvLikes);
        }
    }
}
