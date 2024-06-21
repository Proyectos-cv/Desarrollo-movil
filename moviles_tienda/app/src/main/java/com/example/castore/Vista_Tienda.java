package com.example.castore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Vista_Tienda extends AppCompatActivity {

    String text;

    Button btn_iniciar,btn_salir;
    EditText nombre, precio, cantidad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_tienda);

        Intent intent = getIntent();
        text = intent.getStringExtra(MainActivity.EXTRA_TEXT);

        btn_iniciar = findViewById(R.id.btn_guardar);
        btn_salir = findViewById(R.id.btn_salir);
        nombre = findViewById(R.id.nombre);
        precio = findViewById(R.id.precio);
        cantidad = findViewById(R.id.cantidad);


        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                producto();
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
    public void producto() {

        String nom1 = nombre.getText().toString().trim();
        String dir1 = precio.getText().toString().trim();
        String cor1 = cantidad.getText().toString().trim();
        if (validarNombre(nom1) && validarCampo(dir1) && validarCampo(cor1)) {

            AdminSqLite ad = new AdminSqLite(this, "castore", null, 1);
            SQLiteDatabase chequeo = ad.getWritableDatabase();

            String coo = "SELECT nombretienda from tiendas,tiepro,productos where tiendas.idtienda = tiepro.idtienda and tiepro.idproducto = productos.idproducto and tiendas.idtienda = ? and productos.nombreproducto = ?";
            String[] paa = new String[]{text, nom1};
            Cursor foo = chequeo.rawQuery(coo,paa);

            if (foo.moveToFirst()) {
                chequeo.close();
                Toast.makeText(this, "Este producto ya esta registrado", Toast.LENGTH_SHORT).show();

            }else{

            AdminSqLite admin1 = new AdminSqLite(this, "castore", null, 1);
        SQLiteDatabase bd1 = admin1.getWritableDatabase();

        String consulta = "SELECT MAX(idproducto) FROM productos";
        Cursor fila = bd1.rawQuery(consulta, null);

        if (fila.moveToFirst()) {
            bd1.close();

            String ids = fila.getString(0);
            int idEntero = Integer.parseInt(ids);
            idEntero++;
            String nuevoIds = String.valueOf(idEntero);


            AdminSqLite admin = new AdminSqLite(this, "castore", null, 1);

            SQLiteDatabase bd = admin.getWritableDatabase();

            String nom = nombre.getText().toString().trim();
            String dir = precio.getText().toString().trim();
            String cor = cantidad.getText().toString().trim();

            ContentValues registro = new ContentValues();

            registro.put("idproducto", nuevoIds);
            registro.put("nombreproducto", nom);
            registro.put("precio", dir);
            registro.put("inventario", cor);

            bd.insert("productos", null, registro);

            bd.close();


            AdminSqLite admin2 = new AdminSqLite(this, "castore", null, 1);

            SQLiteDatabase bd2 = admin2.getWritableDatabase();

            ContentValues registro2 = new ContentValues();

            registro2.put("idtienda", text);
            registro2.put("idproducto", nuevoIds);

            bd2.insert("tiepro", null, registro2);

            bd2.close();


            nombre.setText("");
            precio.setText("");
            cantidad.setText("");

            Toast.makeText(this, "El producto se guardo correctamente", Toast.LENGTH_SHORT).show();

            } else {
            // Si la consulta no devuelve filas, maneja el caso adecuadamente
            fila.close();
            bd1.close();
            nombre.setText("");
            precio.setText("");
            cantidad.setText("");
            Toast.makeText(this, "No se encontraron productos", Toast.LENGTH_SHORT).show();
            }

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
        String regex = "^[a-zA-Z\\s]{1,15}$";

        // Verificar si el campo está vacío
        if (TextUtils.isEmpty(input)) {
            return false;
        }

        // Verificar si el campo coincide con la expresión regular
        return Pattern.matches(regex, input);
    }
    public boolean validarCampo(String input) {
        // Expresión regular para aceptar solo números y que tengan entre 1 y 3 dígitos
        String regex = "^[0-9]{1,3}$";

        // Verificar si el campo está vacío
        if (TextUtils.isEmpty(input)) {
            return false;
        }

        // Verificar si el campo coincide con la expresión regular
        return Pattern.matches(regex, input);
    }
}