package com.jhon.petagram;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;
import com.jhon.petagram.pojo.Mascota;
import com.jhon.petagram.resApi.EndpointApi;
import com.jhon.petagram.resApi.MascotaResponse;
import com.jhon.petagram.resApi.RestApiAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Mascotas extends AppCompatActivity {
    public static final String KEY_EXTRA_URL = "url";
    public static final String KEY_EXTRA_LIKES = "like";
    private ImageView imgFotoDetalle;
    private TextView tvLikesDetalle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_mascota);

        Bundle extras = getIntent().getExtras();
        Toolbar tootb = findViewById(R.id.actionbar);
        setSupportActionBar(tootb);

        imgFotoDetalle = findViewById(R.id.imgFotoDetalle);
        String url   = extras.getString(KEY_EXTRA_URL);
        int likes    = extras.getInt(KEY_EXTRA_LIKES);
        tvLikesDetalle     = (TextView) findViewById(R.id.tvLikesDetalle);
        tvLikesDetalle.setText(String.valueOf(likes));
        Picasso.with(this)
                .load(url)
                .placeholder(R.drawable.abejita)
                .into(imgFotoDetalle);
    }
  public boolean onKeyDown (int keyCode, KeyEvent event) {

      if (keyCode == KeyEvent.KEYCODE_BACK){
          Intent intent= new Intent(this,MainActivity.class);
          startActivity(intent);
      }
      return super.onKeyDown(keyCode, event);
  }

}
