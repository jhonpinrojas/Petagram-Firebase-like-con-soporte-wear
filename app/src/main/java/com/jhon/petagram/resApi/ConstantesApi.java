package com.jhon.petagram.resApi;

public final class ConstantesApi {
    public static final String VERSION = "/v8.0/";
    public static final String ROOT_URL = "https://graph.facebook.com" + VERSION;
    public static final String ACCESS_TOKEN = "EAAL0IDxFb30BAPU1RTuDNZCiGFCfzl5geYn9j2HYU8i3ZBBEZCdaGUUEZCWjVuxZApM6kuZBeSg0aC1Hj2ZAsHVaZCZA1Og47bbd50EwZBof6czBHNyhiw7QDphELjaKmxjEiMXgAtmisxpanyf8aUU2Ou8UId1Law1NkZD";
    public static final String KEY_ACCESS_TOKEN = "&access_token=";
    public static final String USER_ID = "17841443085409273";
    public static final String KEY_USER_MEDIA = "/media?fields=id,caption,media_type,media_url,owner,username,like_count";
    public static final String URL_USER_MEDIA = USER_ID + KEY_USER_MEDIA + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

}
