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

public class MainActivity extends AppCompatActivity {
   Button btn_iniciar;
    EditText email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_iniciar = findViewById(R.id.btn_iniciar);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciar();
            }
        });
    }
    public void iniciar(){
        AdminSqLite admin = new AdminSqLite(this, "tienda", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String ema = email.getText().toString();
        String con = password.getText().toString();


        String consulta = "SELECT email, password FROM usuarios WHERE email = ? AND password = ?";
        String[] argumentos = new String[]{ema, con};
        Cursor fila = bd.rawQuery(consulta, argumentos);

        if (fila.moveToFirst()) {
            Intent intent = new Intent(this, Tienda.class);
            startActivity(intent);
        } else
            Toast.makeText(this, "Usuario o contraseña incorrectos",
                    Toast.LENGTH_SHORT).show();
        bd.close();

    }
}