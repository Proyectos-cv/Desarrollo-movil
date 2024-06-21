package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class Tienda extends AppCompatActivity {

    EditText nombre, direccion, telefono,correo,descripcion,password;
        Button btn_guardar,btn_salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);

        nombre = findViewById(R.id.nombre);
        telefono = findViewById(R.id.telefono);
        direccion = findViewById(R.id.direccion);
        correo = findViewById(R.id.correo);
        descripcion = findViewById(R.id.descripcion);
        password = findViewById(R.id.password);
        btn_guardar = findViewById(R.id.btn_guardar);
        btn_salir = findViewById(R.id.btn_salir);

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarTienda();
            }
        });
        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Salir();
            }
        });
    }
    public void Salir(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void guardarTienda(){

        AdminSqLite admin = new AdminSqLite(this, "tienda", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        String nom = nombre.getText().toString();
        String dir = direccion.getText().toString();
        String tel = telefono.getText().toString();
        String cor = correo.getText().toString();
        String des = descripcion.getText().toString();
        String pass = password.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("nombre", nom);
        registro.put("direcciom", dir);
        registro.put("telefono", tel);
        registro.put("correo", cor);
        registro.put("descripcion", des);
        registro.put("passwordtienda", pass);

        bd.insert("tiendas", null, registro);

        bd.close();
        nombre.setText("");
        telefono.setText("");
        direccion.setText("");
        correo.setText("");
        descripcion.setText("");
        password.setText("");

        Toast.makeText(this, "La tienda se guardo correctamente", Toast.LENGTH_SHORT).show();

    }


}