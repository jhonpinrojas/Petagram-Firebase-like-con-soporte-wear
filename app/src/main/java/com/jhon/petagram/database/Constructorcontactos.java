package com.jhon.petagram.database;

import android.content.ContentValues;
import android.content.Context;

import com.jhon.petagram.R;
import com.jhon.petagram.pojo.Contacto;

import java.util.ArrayList;

public class Constructorcontactos {

    private static final int LIKE = 1;
    private Context context;
    public Constructorcontactos(Context context) {
        this.context = context;
    }
    public ArrayList<Contacto> obtenerdatos (){
     BaseDatos db = new BaseDatos(context);
     insertarcontactos(db);
     return db.obtenertodocontactos();
    }

    public void insertarcontactos(BaseDatos db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContanteBaseDatos.TABLE_MASCOTAS_NOMBRE , "Otto");
        contentValues.put(ContanteBaseDatos.TABLE_MASCOTAS_FOTO , R.drawable.abejita);
        db.insertarcontacto(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ContanteBaseDatos.TABLE_MASCOTAS_NOMBRE , "Lucy");;
        contentValues.put(ContanteBaseDatos.TABLE_MASCOTAS_FOTO , R.drawable.castor);
        db.insertarcontacto(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ContanteBaseDatos.TABLE_MASCOTAS_NOMBRE , "kely");
        contentValues.put(ContanteBaseDatos.TABLE_MASCOTAS_FOTO , R.drawable.gato);
        db.insertarcontacto(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ContanteBaseDatos.TABLE_MASCOTAS_NOMBRE , "Bruno");
        contentValues.put(ContanteBaseDatos.TABLE_MASCOTAS_FOTO , R.drawable.perrito);
        db.insertarcontacto(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ContanteBaseDatos.TABLE_MASCOTAS_NOMBRE, "fox");
        contentValues.put(ContanteBaseDatos.TABLE_MASCOTAS_FOTO , R.drawable.zorrito);
        db.insertarcontacto(contentValues);

        contentValues.put(ContanteBaseDatos.TABLE_MASCOTAS_NOMBRE , "rex");
        contentValues.put(ContanteBaseDatos.TABLE_MASCOTAS_FOTO , R.drawable.mascota_5);
        db.insertarcontacto(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ContanteBaseDatos.TABLE_MASCOTAS_NOMBRE , "pancho");;
        contentValues.put(ContanteBaseDatos.TABLE_MASCOTAS_FOTO , R.drawable.mascota_6);
        db.insertarcontacto(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ContanteBaseDatos.TABLE_MASCOTAS_NOMBRE , "snow");
        contentValues.put(ContanteBaseDatos.TABLE_MASCOTAS_FOTO , R.drawable.mascota_7);
        db.insertarcontacto(contentValues);



    }

    public ArrayList<Contacto> obtenerfavoritos (){
        ArrayList<Contacto> mascotas = new ArrayList<>();
        BaseDatos db = new BaseDatos(context);
        db.obtenertodofavoritos();
        return db.obtenertodofavoritos();

    }
    public void darlike (Contacto contacto){
         BaseDatos db = new BaseDatos(context);
         ContentValues contentValues = new ContentValues();
         contentValues.put(ContanteBaseDatos.TABLE_LIKES_MASCOTAS_ID_CONTACTO, contacto.getId());
         contentValues.put(ContanteBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES, LIKE);
         db.insertarcontactolike(contentValues);
    }
    public int obtenerlikescontacto(Contacto contacto){
        BaseDatos db = new BaseDatos(context);
        return  db.obtenerlikescontacto(contacto);
    }
}
