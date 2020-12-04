package com.example.login_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class Usuariodao {

    Context c;
    Usuario u;
    ArrayList<Usuario> lista;
    SQLiteDatabase sql;
    String bd = "bd_app";
    String tabla = "create table if not exists usuario(id integer primary key autoincrement,nombre text,usuario text,contraseña text,email text)";

    public Usuariodao(Context c) {
        this.c = c;
        sql = c.openOrCreateDatabase(bd, c.MODE_PRIVATE, null);
        sql.execSQL(tabla);
        u = new Usuario();
    }


    public boolean insertUsuario(Usuario u) {
        if (buscar(u.getUsuario()) == 0) {
            ContentValues cv = new ContentValues();
            cv.put("nombre", u.getNombre());
            cv.put("usuario", u.getUsuario());
            cv.put("contraseña", u.getPassword());
            cv.put("email", u.getEmail());
            return (sql.insert("usuario", null, cv) > 0);
        } else {
            return false;
        }
    }

    public int buscar(String u) {
        int x = 0;
        lista = selectUsuario();
        for (Usuario us : lista) {
            if (us.getUsuario().equals(u)) {
                x++;
            }
        }
        return x;
    }

    public ArrayList<Usuario> selectUsuario() {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        lista.clear();
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if (cr != null && cr.moveToFirst()) {
            do {
                Usuario u = new Usuario();
                u.setId(cr.getInt(0));
                u.setNombre(cr.getString(1));
                u.setUsuario(cr.getString(2));
                u.setPassword(cr.getString(3));
                u.setEmail(cr.getString(4));
                lista.add(u);
            } while (cr.moveToNext());{

        }

    }
            return lista;
    }
    public int login(String u, String p){
        int a = 0;
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if (cr != null && cr.moveToFirst()) {
            do {
                if(cr.getString(1).equals(u) && cr.getString(2).equals(p)){
                    a++;
                }
            } while (cr.moveToNext());{

            }
        }
        return a;
    }
    public Usuario getUsuario(String u,String p){
        lista = selectUsuario();
        for (Usuario uss:lista) {
            if(uss.getUsuario().equals(u)&&uss.getPassword().equals(p)){
                    return uss;
            }
        }
        return null;
    }
    public Usuario getUsuarioById(int id){
        lista = selectUsuario();
        for (Usuario uss:lista) {
            if(uss.getId()==id){
                return uss;
            }
        }
        return null;
    }

}
