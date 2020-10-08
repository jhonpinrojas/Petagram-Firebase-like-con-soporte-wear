package com.jhon.petagram.resApi;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.jhon.petagram.pojo.Mascota;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MacotaDeserializador implements JsonDeserializer<MascotaResponse> {


    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();
        MascotaResponse mascotaResponse = gson.fromJson(json, MascotaResponse.class);
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        mascotaResponse.setProfilepet(deserializarMascotaDeJson(mascotaResponseData));
        return mascotaResponse;

    }
    private ArrayList<Mascota> deserializarMascotaDeJson(JsonArray mascotaResponseData) {
        ArrayList<Mascota> Mascota = new ArrayList<>();
        for (int i = 0; i < mascotaResponseData.size(); i++) {

            JsonObject MascotaResponseDataObject    = mascotaResponseData.get(i).getAsJsonObject();
            String id                           = MascotaResponseDataObject.get(JsonKeys.USER_ID).getAsString();
            String username                     = MascotaResponseDataObject.get(JsonKeys.USER_USER_NAME).getAsString();
            String media_url                    = MascotaResponseDataObject.get(JsonKeys.MEDIA_URL).getAsString();
            int media_likes                     = MascotaResponseDataObject.get(JsonKeys.MEDIA_LIKES).getAsInt();

            Mascota MascotaProfile = new Mascota();
            MascotaProfile.setId(id);
            MascotaProfile.setNombrecompleto(username);
            MascotaProfile.setFotoinst(media_url);
            MascotaProfile.setLikes(media_likes);

            Mascota.add(MascotaProfile);

        }
        return Mascota;
    }
}
