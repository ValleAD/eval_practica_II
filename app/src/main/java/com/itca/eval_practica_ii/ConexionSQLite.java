package com.itca.eval_practica_ii;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import androidx.annotation.Nullable;

public class ConexionSQLite extends SQLiteOpenHelper {
    public ConexionSQLite(@Nullable Context context) {
        super(context, "EvalNotas.db", null, 1);
    }

    /*public SQLiteDatabase bd() {
        SQLiteDatabase bd = this.getWritableDatabase();
        return bd;
    }*/

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table tb_Notas(id integer not null primary key autoincrement, titulo text, descripcion text, autor text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists tb_Notas");
        onCreate(sqLiteDatabase);
    }





}