package com.jhon.petagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.gson.Gson;
import com.jhon.petagram.adaptadores.PageAdapter;
import com.jhon.petagram.fragment.FragmentReciclerview;
import com.jhon.petagram.fragment.InstagramFragment;
import com.jhon.petagram.fragment.MascotaFavoritaFragment;
import com.jhon.petagram.menus.ActivityAbout;
import com.jhon.petagram.menus.ActivityContacto;
import com.jhon.petagram.menus.ActivityNotificaciones;
import com.jhon.petagram.pojo.Mascota;
import com.jhon.petagram.presentador.MascotaPresentador;
import com.jhon.petagram.resApi.ConstantesApi;
import com.jhon.petagram.resApi.EndpointApi;
import com.jhon.petagram.resApi.JsonKeys;
import com.jhon.petagram.resApi.MascotaResponse;
import com.jhon.petagram.resApi.RestApiAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String msg;
    private String token;
    public static ArrayList<Mascota> mascotas;
    private static final String TAG = "MainActivity";
    //  private ImageButton favoritoimagen;
 //   private TextView like1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        tootb.setLogo(R.drawable.pataperro);
        toolbar     = findViewById(R.id.toolbarfr);
        tabLayout   = findViewById(R.id.tablayout);
        viewPager   =findViewById(R.id.viewpager);
        Toolbar tootb = findViewById(R.id.actionbar);
        setSupportActionBar(tootb);
        setupviewpager();

        mascotas = new ArrayList<>();
        //mantiene un hilo alterno que esta realizando la lectura del restapi adapter
        //para buscar algun cambio en el array de datos que obtenemos de esa clase MascotaResponse
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true) {
                    likecomparativa ();
                    try {
                        sleep(8000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();

        if (toolbar != null){
            setSupportActionBar(toolbar);
        }
    }
    private ArrayList<Fragment> agregarfragments (){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentReciclerview());
        fragments.add(new InstagramFragment());
        return fragments;
    }
   public void setupviewpager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarfragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_house_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_pets_24);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones,menu);
        return (true);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Favoritos:
                Intent intent1 = new Intent(MainActivity.this, MisContactos.class);
                startActivity(intent1);
                break;
           case R.id.About:
                Intent intent = new Intent(this, ActivityAbout.class);
                startActivity(intent);
                break;
            case R.id.Contacto:
                Intent intent2 = new Intent(this, ActivityContacto.class);
                startActivity(intent2);
                break;
            case R.id.Notificaciones:
                notificacion();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    public void notificacion(){
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }
                        // Get new Instance ID token
                        token = task.getResult().getToken();
                        // Log and toast
                        msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, msg);
                        Intent i = new Intent(getApplicationContext(), ActivityNotificaciones.class);
                        i.putExtra ("token",msg );
                        i.putExtra("idinstagram", ConstantesApi.USER_ID);
                        startActivity(i);
                    }
                });

    }
    //lanzar notificacion de like
    public void launchNotification(String url, int likes) {
        Intent i = new Intent(this, Mascotas.class);
        i.putExtra(Mascotas.KEY_EXTRA_URL, url);
        i.putExtra(Mascotas.KEY_EXTRA_LIKES, likes);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT);

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Action accion =
                new NotificationCompat.Action.Builder(R.drawable.ic_baseline_stars_24, "Ver notificacion", pendingIntent)
                        .build();
        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                        .setBackground(BitmapFactory.decodeResource(getResources(),R.drawable.ic_baseline_pets_24))
                        .setGravity(Gravity.CENTER_VERTICAL);

        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this, getString(R.string.channelId))
                .setSmallIcon(R.drawable.ic_baseline_stars_24)
                .setContentTitle("Te han dado like")
                .setContentText("Click para mostrar foto")
                .setSound(uri)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .extend(wearableExtender.addAction(accion));

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
                notificationManager.notify(0, nBuilder.build());
    }
    //realiza comparativa entre el array de objetos que representan las fotos y las compara
    // con un nuevo array para verificar si hubo cambio en los valores de like,si llego a hacer algun cambio en los valores
    //recibidos en el array invoca el metodo launchNotification();
    public void likecomparativa (){
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.buildGsonDeserializeMediaRecent();
        EndpointApi endpointsAPI = restApiAdapter.ConexionInstagram(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCall = endpointsAPI.getRecentMedia();
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                ArrayList<Mascota> nuevamascotas = new ArrayList<>();;
                MascotaResponse mascotaResponse = response.body();
                nuevamascotas = mascotaResponse.getProfilepet();
                for (int i = 0; i < nuevamascotas.size(); i++) {
                    Mascota nuevamascota = nuevamascotas.get(i);
                for (int j = 0; j < mascotas.size(); j++) {
                    Mascota Viejamascota = mascotas.get(j);
                    if (nuevamascota.getId().equals(Viejamascota.getId())) {
                        if (nuevamascota.getLikes() > Viejamascota.getLikes()) {
                            System.out.println("Tienes un nuevo like");
                            launchNotification(nuevamascota.getFotoinst(), nuevamascota.getLikes());
                            break;
                             }
                         }
                     }
                }
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Falló la conexión con servidor", Toast.LENGTH_LONG).show();
                Log.e("Connection failed", t.toString());
            }
        });
      }
    }