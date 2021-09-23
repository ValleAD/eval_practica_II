package com.itca.eval_practica_ii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class update extends AppCompatActivity {
    /*Bundle bundle = getIntent().getExtras();
    String t = (String) bundle.getString("titulo");*/
    public String x;
    public EditText v;
    public EditText titulo, desc, autor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        titulo = findViewById(R.id.txtTitle);
        desc = findViewById(R.id.txtDescription);
        autor = findViewById(R.id.txtAutor);

        Bundle bundle = new Bundle();

        String dato = getIntent().getStringExtra("valorTitulo");

        v = findViewById(R.id.txtTitle);
        v.setText(dato);

        x = v.getText().toString();


        try {

            ConexionSQLite conexion = new ConexionSQLite(this);
            SQLiteDatabase bd = conexion.getWritableDatabase();

            Cursor fila = bd.rawQuery("select descripcion, autor from tb_Notas where titulo = '" + x + "'"  , null);
            if(fila.moveToFirst()) {
                desc.setText(fila.getString(0));
                autor.setText(fila.getString(1));
            }


        } catch (Exception e) {
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }


    }


    public void update(View view) {
        ConexionSQLite conexion = new ConexionSQLite(this);
        SQLiteDatabase bd = conexion.getWritableDatabase();
        String t = titulo.getText().toString();
        String d = desc.getText().toString();
        String a = autor.getText().toString();


        ContentValues registro = new ContentValues();
        registro.put("titulo", t);
        registro.put("descripcion", d);
        registro.put("autor", a);

        int cant = bd.update("tb_Notas", registro, "titulo = '" + x + "'", null);
        bd.close();
        if (cant == 1) {
            Toast.makeText(this, "Nota Modificada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Nota NO Modificada", Toast.LENGTH_SHORT).show();
        }
    }


    public void eliminarNota(View view) {
        ConexionSQLite conexion = new ConexionSQLite(this);
        SQLiteDatabase bd = conexion.getWritableDatabase();
        String t = titulo.getText().toString();
        int cant = bd.delete("tb_Notas", "titulo = '" + x + "'", null);
        bd.close();
        titulo.setText("");
        desc.setText("");
        autor.setText("");

        Toast.makeText(this, "Nota Eliminada", Toast.LENGTH_SHORT).show();
    }


}