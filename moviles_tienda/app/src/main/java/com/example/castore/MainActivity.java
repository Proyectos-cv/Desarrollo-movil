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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_iniciar,btn_cliente;
    EditText email, password;

    public static final String EXTRA_TEXT = "com.example.aplication.example.EXTRA_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_iniciar = findViewById(R.id.btn_iniciar);
        btn_cliente = findViewById(R.id.cliente);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciar();
            }
        });
        btn_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cliente();
            }
        });
    }
    public void iniciar(){
        AdminSqLite admin = new AdminSqLite(this, "castore", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String ema = email.getText().toString();
        String con = password.getText().toString();


        String consulta = "SELECT idusuario,email, password FROM usuarios WHERE email = ? AND password = ?";
        String[] argumentos = new String[]{ema, con};
        Cursor fila = bd.rawQuery(consulta, argumentos);

       /* String consulta9 = "INSERT INTO clipro (idproducto, idcliente,cantidad ) VALUES ('1', '1', '1')";
        bd.execSQL(consulta9);*//*

        String consulta3 = "ALTER TABLE clientes ADD COLUMN passwordcliente TEXT";
        bd.execSQL(consulta3);*/

        String consulta2 = "SELECT idtienda FROM tiendas WHERE emailtienda = ? AND passwordtienda = ?";
        String[] argumentos2 = new String[]{ema, con};
        Cursor fila2 = bd.rawQuery(consulta2, argumentos2);

        String consulta3 = "SELECT idcliente FROM clientes WHERE emailcliente = ? AND passwordcliente = ?";
        String[] argumentos3 = new String[]{ema, con};
        Cursor fila3 = bd.rawQuery(consulta3, argumentos3);

        if (fila.moveToFirst()) {

           String text = fila.getString(0);

            Intent intent = new Intent(this, Tienda.class);
            intent.putExtra(EXTRA_TEXT, text);
            startActivity(intent);
            //Toast.makeText(this, "LOG IN",
              //      Toast.LENGTH_SHORT).show();
        } else if (fila2.moveToFirst()) {
            String text = fila2.getString(0);
            Intent intent = new Intent(this, Vista_Tienda.class);
            intent.putExtra(EXTRA_TEXT, text);
            startActivity(intent);
        } else if (fila3.moveToFirst()) {
            /*String text = fila3.getString(0);
            Intent intent = new Intent(this, Vista_Cliente.class);
            intent.putExtra(EXTRA_TEXT, text);
            startActivity(intent);*/
            Intent intent = new Intent(this, Vista_Productos.class);
            String text = fila3.getString(0);
            intent.putExtra(EXTRA_TEXT, text);
            startActivity(intent);
        } else
            Toast.makeText(this, "Usuario o contraseña incorrectos",
                    Toast.LENGTH_SHORT).show();

        bd.close();

    }
    public void cliente(){
        Intent intent = new Intent(this, Cliente.class);
        startActivity(intent);
    }
}