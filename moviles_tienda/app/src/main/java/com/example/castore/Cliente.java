package com.example.castore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Cliente extends AppCompatActivity {
    EditText nombre, direccion,correo,password,ap,am;
    Button btn_guardar,btn_salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        nombre = findViewById(R.id.nombre);
        ap= findViewById(R.id.apep);
        am = findViewById(R.id.apem);

        direccion = findViewById(R.id.direccion);
        correo = findViewById(R.id.correo);

        password = findViewById(R.id.password);
        btn_guardar = findViewById(R.id.btn_guardar);
        btn_salir = findViewById(R.id.btn_salir);


        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCliente();
            }
        });
        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salir();
            }
        });

    }
    public void salir(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void guardarCliente() {
        String input = nombre.getText().toString().trim();
        String input2 = direccion.getText().toString().trim();
        String input3 = correo.getText().toString().trim();
        String input4 = password.getText().toString().trim();
        String input5 = ap.getText().toString().trim();
        String input6 = am.getText().toString().trim();

        if (validarNombre(input) && validarDireccion(input2) && validarCorreo(input3) && validarPass(input4) && validarNombre(input5) && validarNombre(input6)) {

       AdminSqLite admin = new AdminSqLite(this, "castore", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        String nom = nombre.getText().toString().trim();
        String dir = direccion.getText().toString().trim();
        String cor = correo.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String p = ap.getText().toString().trim();
        String m = am.getText().toString().trim();


            String consulta2 = "SELECT emailcliente, passwordcliente FROM clientes WHERE emailcliente = ? AND passwordcliente = ?";
            String[] argumentos2 = new String[]{cor, pass};
            Cursor fila2 = bd.rawQuery(consulta2,argumentos2);
            if (fila2.moveToFirst()) {
                Toast.makeText(this, "El correo y la contraseña ya fueron ingresados", Toast.LENGTH_SHORT).show();
            }else {
                ContentValues registro = new ContentValues();
                registro.put("nombrecliente", nom);
                registro.put("apep", p);
                registro.put("apem", m);
                registro.put("direccioncliente", dir);
                registro.put("emailcliente", cor);
                registro.put("passwordcliente", pass);

                bd.insert("clientes", null, registro);

                bd.close();
                nombre.setText("");
                direccion.setText("");
                correo.setText("");
                password.setText("");
                ap.setText("");
                am.setText("");

                Toast.makeText(this, "El cliente se guardo correctamente", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Ingreso de datos incorrecto", Toast.LENGTH_SHORT).show();

        }
    }
    public boolean validarDireccion(String input) {
        // Expresión regular para aceptar letras, espacios y números, con un límite de 10 caracteres
        String regex = "^[a-zA-Z0-9\\s]+$";

        // Verificar si el campo está vacío
        if (TextUtils.isEmpty(input)) {
            return false;
        }

        // Verificar si el campo coincide con la expresión regular
        return Pattern.matches(regex, input);
    }

    public boolean validarCorreo(String input) {
        // Expresión regular para validar un correo electrónico
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        // Verificar si el campo está vacío
        if (TextUtils.isEmpty(input)) {
            return false;
        }

        // Verificar si el campo coincide con la expresión regular
        return Pattern.matches(regex, input);
    }
    public boolean validarPass(String input) {
        // Expresión regular para aceptar solo letras y números
        String regex = "^[a-zA-Z0-9.,\\-]+$";

        // Verificar si el campo está vacío
        if (TextUtils.isEmpty(input)) {
            return false;
        }

        // Verificar si el campo coincide con la expresión regular
        return Pattern.matches(regex, input);
    }

    public boolean validarNombre(String input) {
        // Expresión regular para aceptar letras, espacios y números, con un límite de 10 caracteres
        String regex = "^[a-zA-Z\\s]+$";

        // Verificar si el campo está vacío
        if (TextUtils.isEmpty(input)) {
            return false;
        }

        // Verificar si el campo coincide con la expresión regular
        return Pattern.matches(regex, input);
    }
}