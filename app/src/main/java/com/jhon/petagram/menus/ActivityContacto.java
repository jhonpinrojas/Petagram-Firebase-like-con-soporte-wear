package com.jhon.petagram.menus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.jhon.petagram.JavaMailAPI;
import com.jhon.petagram.R;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ActivityContacto extends AppCompatActivity {

    private TextInputEditText Cajanombre;
    private TextInputEditText Cajamail;
    private TextInputEditText Cajadescripcion;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        Button boton1     =  (Button) findViewById(R.id.button);
        Cajanombre       =  findViewById(R.id.textonombre);
        Cajamail         =  findViewById(R.id.textocorreo);
        Cajadescripcion  =  findViewById(R.id.textodescripcion);

        boton1.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                sendMail();

            }
        });
        /*Toolbar tootb = findViewById(R.id.actionbar);
        setSupportActionBar(tootb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tootb.setLogo(R.drawable.pataperro);*/
    }
    private void sendMail() {

        String subject = "Mascotas" ;
        String mail = Cajamail.getText().toString();
        String descripcion = Cajadescripcion.getText().toString().trim();

        //Send Mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(this,mail,subject,descripcion);
        javaMailAPI.execute();

    }
}
