package com.example.castore;

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
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;
import android.text.TextUtils;

public class Tienda extends AppCompatActivity {

    EditText nombre, direccion, correo, password;
    Button btn_guardar, btn_salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);

        nombre = findViewById(R.id.nombre);

        direccion = findViewById(R.id.direccion);
        correo = findViewById(R.id.correo);

        password = findViewById(R.id.password);
        btn_guardar = findViewById(R.id.btn_guardar);
        btn_salir = findViewById(R.id.btn_salir);
/*
//////
        Intent intent = getIntent();
        String text = intent.getStringExtra(MainActivity.EXTRA_TEXT);

        TextView ver = (TextView) findViewById(R.id.direccion);

        ver.setText("Hola " + text);
      ////////////
*/

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

    public void Salir() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void guardarTienda() {


        AdminSqLite admin = new AdminSqLite(this, "castore", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        String nom = nombre.getText().toString().trim();
        String dir = direccion.getText().toString().trim();
        String cor = correo.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (validarNombre(nom) && validarDireccion(dir) && validarCorreo(cor) && validarPass(pass)) {

            String consulta2 = "SELECT emailtienda, passwordtienda FROM tiendas WHERE emailtienda = ? AND passwordtienda = ?";
            String[] argumentos2 = new String[]{cor, pass};
            Cursor fila2 = bd.rawQuery(consulta2,argumentos2);
            if (fila2.moveToFirst()) {
                Toast.makeText(this, "El correo y la contraseña ya fueron ingresados", Toast.LENGTH_SHORT).show();
            }else {
            ContentValues registro = new ContentValues();

            registro.put("nombretienda", nom);
            registro.put("direcciontienda", dir);
            registro.put("emailtienda", cor);
            registro.put("passwordtienda", pass);

            bd.insert("tiendas", null, registro);

            bd.close();
            nombre.setText("");
            direccion.setText("");
            correo.setText("");
            password.setText("");

            Toast.makeText(this, "La tienda se guardo correctamente", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Ingreso de datos incorrecto", Toast.LENGTH_SHORT).show();

        }

    }
    public boolean validarDireccion (String input){
        // Expresión regular para aceptar letras, espacios y números, con un límite de 10 caracteres
        String regex = "^[a-zA-Z0-9\\s]+$";

        // Verificar si el campo está vacío
        if (TextUtils.isEmpty(input)) {
            return false;
        }

        // Verificar si el campo coincide con la expresión regular
        return Pattern.matches(regex, input);
    }

    public boolean validarCorreo (String input){
        // Expresión regular para validar un correo electrónico
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        // Verificar si el campo está vacío
        if (TextUtils.isEmpty(input)) {
            return false;
        }

        // Verificar si el campo coincide con la expresión regular
        return Pattern.matches(regex, input);
    }
    public boolean validarPass (String input){
        // Expresión regular para aceptar solo letras y números
        String regex = "^[a-zA-Z0-9.,\\-]+$";

        // Verificar si el campo está vacío
        if (TextUtils.isEmpty(input)) {
            return false;
        }

        // Verificar si el campo coincide con la expresión regular
        return Pattern.matches(regex, input);
    }

    public boolean validarNombre (String input){
        // Expresión regular para aceptar letras, espacios y números, con un límite de 10 caracteres
        String regex = "^[a-zA-Z\\s]{1,30}$";

        // Verificar si el campo está vacío
        if (TextUtils.isEmpty(input)) {
            return false;
        }

        // Verificar si el campo coincide con la expresión regular
        return Pattern.matches(regex, input);
    }
}