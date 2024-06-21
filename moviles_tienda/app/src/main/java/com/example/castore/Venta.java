package com.example.castore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class Venta extends AppCompatActivity {
    Button btn_salir;
    public static final String EXTRA_TEXT = "com.example.aplication.example.EXTRA_TEXT";

    String total,Cliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta);

        btn_salir = findViewById(R.id.btn_salir);


        TextView ver = (TextView) findViewById(R.id.caja);
        TextView deuda = (TextView) findViewById(R.id.cantidad);

        //Intent intent = getIntent();
        //String text = intent.getStringExtra(MainActivity.EXTRA_TEXT);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            total = extras.getString("EXTRA_ID_PRODUCTO");
            Cliente = extras.getString("EXTRA_OTRA_VARIABLE");


            deuda.setText("$ " + total);

            btn_salir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    salir();
                }
            });
        }
    }
    public void salir(){
        Intent intent = new Intent(Venta.this, Vista_Productos.class);
        intent.putExtra(EXTRA_TEXT, Cliente);
        startActivity(intent);

    }
}