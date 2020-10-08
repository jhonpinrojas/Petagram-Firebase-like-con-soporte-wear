package com.jhon.petagram.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jhon.petagram.MainActivity;
import com.jhon.petagram.database.Constructorcontactos;
import com.jhon.petagram.fragment.MascotaFragmentview;
import com.jhon.petagram.fragment.ReciclerviewFragmentview;
import com.jhon.petagram.pojo.Contacto;
import com.jhon.petagram.pojo.Mascota;
import com.jhon.petagram.resApi.EndpointApi;
import com.jhon.petagram.resApi.MascotaResponse;
import com.jhon.petagram.resApi.RestApiAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MascotaPresentador implements IMascotaViewPresentador {
    private MascotaFragmentview mascotaFragmentview;
    private Context context;
    private Constructorcontactos constructorcontactos;
    private ArrayList<Mascota> mascotas;

    public MascotaPresentador(MascotaFragmentview mascotaFragmentview, Context context) {
        this.mascotaFragmentview = mascotaFragmentview;
        this.context = context;
        obtenerMediosRecientes();
    }

    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.buildGsonDeserializeMediaRecent();
        EndpointApi endpointsAPI = restApiAdapter.ConexionInstagram(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCall = endpointsAPI.getRecentMedia();
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getProfilepet();
                mostrarcontactosRv();
                MainActivity.mascotas = mascotas;
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Falló la conexión con servidor", Toast.LENGTH_LONG).show();
                Log.e("Connection failed", t.toString());
            }
        });
    }


    @Override
    public void mostrarcontactosRv() {
        mascotaFragmentview.InicializarAdaptadorRV1(mascotaFragmentview.crearmascota(mascotas));
        mascotaFragmentview.generargridLayout();
    }
}
