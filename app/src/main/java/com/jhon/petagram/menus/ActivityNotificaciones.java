package com.jhon.petagram.menus;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jhon.petagram.R;

public class ActivityNotificaciones extends AppCompatActivity {
    TextView datospublicados;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);

        Bundle datos = this.getIntent().getExtras();
        String token1 = datos.getString("token");
        String instagramid =datos.getString("idinstagram");
        datospublicados = findViewById(R.id.Notifica);
        datospublicados.setText("id dispositivo :" + token1 +" \n  \n" +"id_usuario_instagram :" + instagramid);
    }
}
