package com.example.castore;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.graphics.Color;


public class Vista_Productos extends AppCompatActivity {
    public static final String EXTRA_TEXT = "com.example.aplication.example.EXTRA_TEXT";
    Button btn_carrito, btn_salir;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_productos);

        btn_carrito = findViewById(R.id.btn_carro);
        btn_salir = findViewById(R.id.btn_salir);

        btn_carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carro();
            }
        });
        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salir();
            }
        });
        Intent intent = getIntent();
        text = intent.getStringExtra(MainActivity.EXTRA_TEXT);


        // Obtén la referencia a la tabla en el layout
        TableLayout tabla = findViewById(R.id.tabla);

// Crear instancia de la clase AdminSqLite para acceder a la base de datos
        AdminSqLite admin = new AdminSqLite(this, "castore", null, 1);

// Obtener instancia de la base de datos para lectura
        SQLiteDatabase bd = admin.getReadableDatabase();

// Definir la consulta SQL para obtener todos los datos de la tabla "productos"
        String consulta = "SELECT * FROM productos";

// Ejecutar la consulta y obtener un cursor que apunta a los resultados
        Cursor cursor = bd.rawQuery(consulta, null);


            TableRow filaDatos1 = new TableRow(this);

            TextView Nombrea = new TextView(this);
            Nombrea.setText("ID");
            filaDatos1.addView(Nombrea);

            TextView Nombre = new TextView(this);
            Nombre.setText("NOMBRE");
            filaDatos1.addView(Nombre);

            TextView Precio = new TextView(this);
            Precio.setText("PRECIO");
            filaDatos1.addView(Precio);

            TextView Inventario = new TextView(this);
            Inventario.setText(" Cantidad");
            filaDatos1.addView(Inventario);

            Nombrea.setTextSize(18); // Establece el tamaño del texto a 18sp
            Nombre.setTextSize(18); // Establece el tamaño del texto a 18sp
            Precio.setTextSize(18); // Establece el tamaño del texto a 18sp
            Inventario.setTextSize(18); // Establece el tamaño del texto a 18sp

        Nombrea.setTextColor(Color.WHITE); // Cambia el color del texto a blanco
        Nombre.setTextColor(Color.WHITE); // Cambia el color del texto a blanco
        Precio.setTextColor(Color.WHITE); // Cambia el color del texto a blanco
        Inventario.setTextColor(Color.WHITE); // Cambia el color del texto a blanco

            filaDatos1.setBackgroundColor(Color.GRAY); // Establece el color de fondo a gris

            tabla.addView(filaDatos1);

// Verificar si hay al menos una fila de resultado
        if (cursor.moveToFirst()) {
            do {
                // Crear una nueva fila para los datos
                TableRow filaDatos = new TableRow(this);

                // Obtener los valores de cada columna de la fila actual
                final int idProducto = cursor.getInt(cursor.getColumnIndex("idproducto"));
                String nombreProducto = cursor.getString(cursor.getColumnIndex("nombreproducto"));
                int precio = cursor.getInt(cursor.getColumnIndex("precio"));
                int inventario = cursor.getInt(cursor.getColumnIndex("inventario"));

                // Crear TextViews para cada dato y establecer el texto
                TextView textViewIdProducto = new TextView(this);
                textViewIdProducto.setText(String.valueOf(idProducto));

                TextView textViewNombreProducto = new TextView(this);
                textViewNombreProducto.setText(nombreProducto);

                TextView textViewPrecio = new TextView(this);
                textViewPrecio.setText(String.valueOf(precio));

                TextView textViewInventario = new TextView(this);
                textViewInventario.setText(String.valueOf(inventario));

                // Agregar TextViews a la fila de datos
                filaDatos.addView(textViewIdProducto);
                filaDatos.addView(textViewNombreProducto);
                filaDatos.addView(textViewPrecio);
                filaDatos.addView(textViewInventario);

                // Crear un botón para esta fila
                Button boton = new Button(this);
                boton.setText("Ver");
                boton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Al hacer clic en el botón, enviar el id del producto a la actividad Vista_Tienda
                        Intent intent = new Intent(Vista_Productos.this, Vista_Cliente.class);
                        //intent.putExtra(EXTRA_TEXT,  String.valueOf(idProducto));
                        intent.putExtra("EXTRA_ID_PRODUCTO", String.valueOf(idProducto));
                        intent.putExtra("EXTRA_OTRA_VARIABLE", text);
                        startActivity(intent);
                    }
                });

                // Agregar el botón a la fila de datos
                filaDatos.addView(boton);

                // Agregar la fila de datos a la tabla
                tabla.addView(filaDatos);

            } while (cursor.moveToNext());
        }

// Cerrar el cursor y la conexión a la base de datos
        cursor.close();
        bd.close();



    }
    public void carro(){
        Intent intent = new Intent(Vista_Productos.this, Vista_Carrito.class);
        intent.putExtra(EXTRA_TEXT, text);
        startActivity(intent);
    }
    public void salir(){
        Intent intent = new Intent(Vista_Productos.this, MainActivity.class);
        startActivity(intent);
    }
}