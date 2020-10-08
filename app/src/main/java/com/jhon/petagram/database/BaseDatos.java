package com.jhon.petagram.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.jhon.petagram.pojo.Contacto;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {
    public Context context;
    public String queryCreartablacontacto;
    public String querycreartablalikes;

    public BaseDatos(@Nullable Context context) {
        super(context,ContanteBaseDatos.DATABASE_NAME, null, ContanteBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
          queryCreartablacontacto   = "CREATE TABLE " + ContanteBaseDatos.TABLE_MASCOTAS + "(" +
            ContanteBaseDatos.TABLE_MASCOTAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ContanteBaseDatos.TABLE_MASCOTAS_NOMBRE + " TEXT, " +
            ContanteBaseDatos.TABLE_MASCOTAS_TELEFONO + " TEXT, " +
            ContanteBaseDatos.TABLE_MASCOTAS_EMAIL + " TEXT, " +
            ContanteBaseDatos.TABLE_MASCOTAS_FOTO + " INTEGER" +
            ")";


        querycreartablalikes = "CREATE TABLE " + ContanteBaseDatos.TABLE_LIKES_MASCOTAS + "(" +
            ContanteBaseDatos.TABLE_LIKES_MASCOTAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ContanteBaseDatos.TABLE_LIKES_MASCOTAS_ID_CONTACTO + " INTEGER, " +
            ContanteBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES + " INTEGER, " +
            "FOREIGN KEY ("+ ContanteBaseDatos.TABLE_LIKES_MASCOTAS_ID_CONTACTO + ")" +
            "REFERENCES "+ ContanteBaseDatos.TABLE_MASCOTAS + "(" +ContanteBaseDatos.TABLE_MASCOTAS_ID+")" + ")";
        db.execSQL(queryCreartablacontacto);
        db.execSQL(querycreartablalikes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ContanteBaseDatos.TABLE_MASCOTAS );
        db.execSQL("DROP TABLE IF EXISTS " + ContanteBaseDatos.TABLE_LIKES_MASCOTAS );
        onCreate(db);

    }
   public ArrayList<Contacto> obtenertodofavoritos() {
        ArrayList<Contacto> contactos = new ArrayList<>();
        String query = "SELECT * FROM " + ContanteBaseDatos.TABLE_MASCOTAS + " order by id desc limit 5 ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);

        while (registros.moveToNext()) {
            Contacto contactoactual = new Contacto();
            contactoactual.setId(registros.getInt(0));
            contactoactual.setNombre(registros.getString(1));
            contactoactual.setTelefono(registros.getString(2));
            contactoactual.setMail(registros.getString(3));
            contactoactual.setFoto(registros.getInt(4));
            String querylikes = "SELECT COUNT("+ContanteBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES+") as likes"+
                    " FROM " + ContanteBaseDatos.TABLE_LIKES_MASCOTAS +
                    " WHERE " + ContanteBaseDatos.TABLE_LIKES_MASCOTAS_ID_CONTACTO + "=" + contactoactual.getId();
            Cursor registroslikes=db.rawQuery(querylikes, null);
            if(registroslikes.moveToNext()){
                contactoactual.setLike(registroslikes.getInt(0));}
            else {  contactoactual.setLike(0);}
            contactos.add(contactoactual);

        }
        db.close();
        return contactos;
    }
    public ArrayList<Contacto> obtenertodocontactos() {
        ArrayList<Contacto> contactos = new ArrayList<>();
        String query = "SELECT * FROM " + ContanteBaseDatos.TABLE_MASCOTAS+ " order by id limit 8 ";;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);

        while (registros.moveToNext()) {
            Contacto contactoactual = new Contacto();
            contactoactual.setId(registros.getInt(0));
            contactoactual.setNombre(registros.getString(1));
            contactoactual.setTelefono(registros.getString(2));
            contactoactual.setMail(registros.getString(3));
            contactoactual.setFoto(registros.getInt(4));

            String querylikes = "SELECT COUNT("+ContanteBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES+") as likes"+
                    " FROM " + ContanteBaseDatos.TABLE_LIKES_MASCOTAS +
                    " WHERE " + ContanteBaseDatos.TABLE_LIKES_MASCOTAS_ID_CONTACTO + "=" + contactoactual.getId();
            Cursor registroslikes=db.rawQuery(querylikes, null);
            if(registroslikes.moveToNext()){
                contactoactual.setLike(registroslikes.getInt(0));}
            else {  contactoactual.setLike(0);}
            contactos.add(contactoactual);
        }
        db.close();
        return contactos;
    }
    public void insertarcontacto (ContentValues contentValues) {
    SQLiteDatabase db = this.getWritableDatabase();
    db.insert(ContanteBaseDatos.TABLE_MASCOTAS,null,contentValues);
    db.close();
    }
    public void insertarcontactolike (ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ContanteBaseDatos.TABLE_LIKES_MASCOTAS,null,contentValues);
        db.close();

    }
    public int obtenerlikescontacto(Contacto contacto){
        int likes=0;
        String qwery = "SELECT COUNT("+ContanteBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES +")" +
                " FROM " + ContanteBaseDatos.TABLE_LIKES_MASCOTAS +
                " WHERE " + ContanteBaseDatos.TABLE_LIKES_MASCOTAS_ID_CONTACTO + "=" +contacto.getId();

         SQLiteDatabase db = this.getWritableDatabase();
         Cursor registros = db.rawQuery(qwery,null );
       if(registros.moveToNext()) {
           likes = registros.getInt(0);
       }
        db.close();
        return likes;
    }
}
