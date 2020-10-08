package com.jhon.petagram.resApi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndpointApi {

    @GET(ConstantesApi.URL_USER_MEDIA)
    Call<MascotaResponse> getRecentMedia();
}
