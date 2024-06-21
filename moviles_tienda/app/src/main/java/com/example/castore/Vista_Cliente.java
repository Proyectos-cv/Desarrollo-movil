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

import java.util.regex.Pattern;
import android.text.TextUtils;

public class Vista_Cliente extends AppCompatActivity {
    String idProducto,Cliente;
    public static final String EXTRA_TEXT = "com.example.aplication.example.EXTRA_TEXT";
    Button btn_iniciar,btn_salir,carrito;
    EditText nombre, precio, cantidad, compra, tienda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_cliente);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idProducto = extras.getString("EXTRA_ID_PRODUCTO");
            Cliente = extras.getString("EXTRA_OTRA_VARIABLE");


            btn_iniciar = findViewById(R.id.btn_guardar);
            btn_salir = findViewById(R.id.btn_salir);

            carrito = findViewById(R.id.carrito);

            nombre = findViewById(R.id.nombre);
            precio = findViewById(R.id.precio);
            cantidad = findViewById(R.id.cantidad);
            tienda = findViewById(R.id.tienda);
            compra = findViewById(R.id.compra);

nombre.setText(Cliente);
precio.setText(idProducto);
            AdminSqLite admin = new AdminSqLite(this, "castore", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();

            String consulta = "SELECT nombreproducto,precio,inventario,nombretienda from tiendas,tiepro,productos where tiendas.idtienda = tiepro.idtienda and tiepro.idproducto = productos.idproducto and productos.idproducto ="+idProducto;
            Cursor fila = bd.rawQuery(consulta, null);

            if (fila.moveToFirst()) {
                //getString obtiene los datos de la tabla
                nombre.setText(fila.getString(0));
                precio.setText(fila.getString(1));
                cantidad.setText(fila.getString(2));
                tienda.setText(fila.getString(3));

                    //precio.setText(Cliente);

            } else
                Toast.makeText(this, "Este producto no existe en esta tienda",
                        Toast.LENGTH_SHORT).show();
            bd.close();


            btn_iniciar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    compra();
                }
            });
            btn_salir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    salir();
                }
            });
            carrito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    carro();
                }
            });
           /* buscar_carrito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buscar_carro();
                }
            });*/
        }
    }
    public void salir(){
        Intent intent = new Intent(this, Vista_Productos.class);
        intent.putExtra(EXTRA_TEXT, Cliente);
        startActivity(intent);
    }
    public void compra(){
        String input = compra.getText().toString().trim();
        if (validarCampo(input)) {
            //precio.setText(input);
            // El contenido es válido

        AdminSqLite admin = new AdminSqLite(this, "castore", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nom = nombre.getText().toString();
        String tie = tienda.getText().toString();

        String consulta = "SELECT inventario,precio,productos.idproducto from tiendas,tiepro,productos where tiendas.idtienda = tiepro.idtienda and tiepro.idproducto = productos.idproducto and nombretienda = ? and nombreproducto = ?";
        String[] argumentos = new String[]{tie, nom};
        Cursor fila = bd.rawQuery(consulta, argumentos);

        if (fila.moveToFirst()) {

            String precioStr = fila.getString(0);
            String price = fila.getString(1);
            String id = fila.getString(2);

            int precioInt = Integer.parseInt(precioStr);
            int price_f = Integer.parseInt(price);
            int cantity = Integer.parseInt(compra.getText().toString().trim());
            String restante = String.valueOf(precioInt - cantity);

            String fin = String.valueOf(price_f * cantity);

            if(cantity <= precioInt){
                String consulta9 = "UPDATE productos SET inventario = " + restante + " WHERE idproducto = " + id;
                bd.execSQL(consulta9);

                //Intent intent = new Intent(this, Venta.class);
                //intent.putExtra(EXTRA_TEXT, fin);
                //startActivity(intent);
                Intent intent = new Intent(Vista_Cliente.this, Venta.class);
                intent.putExtra("EXTRA_ID_PRODUCTO", fin);
                intent.putExtra("EXTRA_OTRA_VARIABLE", Cliente);
                startActivity(intent);
            }else{
                Toast.makeText(this, "No hay suficiente cantidad de este producto",
                        Toast.LENGTH_SHORT).show();
            }
        } else
            Toast.makeText(this, "No existe este producto",
                    Toast.LENGTH_SHORT).show();
        bd.close();
        } else {
            Toast.makeText(this, "El input de no es valido",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void carro(){
        AdminSqLite admins = new AdminSqLite(this, "castore", null, 1);

        SQLiteDatabase bds = admins.getWritableDatabase();
        String co = "SELECT idproducto FROM clipro WHERE idproducto = ? AND idcliente = ?";
        String[] ar = new String[]{idProducto, Cliente};
        Cursor fil = bds.rawQuery(co,ar);
        if (fil.moveToFirst()) {
            Toast.makeText(this, "El producto ya esta agregado", Toast.LENGTH_SHORT).show();
        }else {

            AdminSqLite admin = new AdminSqLite(this, "castore", null, 1);

            SQLiteDatabase bd = admin.getWritableDatabase();

            ContentValues registro = new ContentValues();

            registro.put("idproducto", idProducto);
            registro.put("idcliente", Cliente);
            registro.put("cantidad", "1");

            bd.insert("clipro", null, registro);

            bd.close();

            /*nombre.setText("");
            precio.setText("");
            cantidad.setText("");
            tienda.setText("");
            compra.setText("");*/
            Toast.makeText(this, "El producto se guardo correctamente en el carrito", Toast.LENGTH_SHORT).show();
        }
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
        String regex = "^[a-zA-Z0-9\\s]+$";

        // Verificar si el campo está vacío
        if (TextUtils.isEmpty(input)) {
            return false;
        }

        // Verificar si el campo coincide con la expresión regular
        return Pattern.matches(regex, input);
    }
    public boolean validarDireccion(String input) {
        // Expresión regular para aceptar letras, números, espacios y el símbolo '#'
        String regex = "^[a-zA-Z0-9\\s#]+$";

        // Verificar si el campo está vacío
        if (TextUtils.isEmpty(input)) {
            return false;
        }

        // Verificar si el campo coincide con la expresión regular
        return Pattern.matches(regex, input);
    }




   /* public void carro(){
        AdminSqLite admin1 = new AdminSqLite(this, "castore", null, 1);
        SQLiteDatabase bd1 = admin1.getWritableDatabase();
        String nom = nombre.getText().toString();
        String tie = tienda.getText().toString();

        String consulta = "SELECT productos.idproducto from tiendas,tiepro,productos where tiendas.idtienda = tiepro.idtienda and tiepro.idproducto = productos.idproducto and nombretienda = ? and nombreproducto = ?";

        String[] argumentos = new String[]{tie, nom};
        Cursor fila = bd1.rawQuery(consulta, argumentos);

        if (fila.moveToFirst()) {
            bd1.close();

            String ids = fila.getString(0);

            AdminSqLite admin = new AdminSqLite(this, "castore", null, 1);

            SQLiteDatabase bd = admin.getWritableDatabase();

            ContentValues registro = new ContentValues();

            registro.put("idproducto", ids);
           // registro.put("idcliente", text);
            registro.put("cantidad", "1");

            bd.insert("clipro", null, registro);

            bd.close();

            nombre.setText("");
            precio.setText("");
            cantidad.setText("");
            tienda.setText("");
            compra.setText("");
            Toast.makeText(this, "El producto se guardo correctamente en el carrito", Toast.LENGTH_SHORT).show();
        }else {
            // Si la consulta no devuelve filas, maneja el caso adecuadamente
            fila.close();
            bd1.close();
            nombre.setText("");
            precio.setText("");
            cantidad.setText("");
            Toast.makeText(this, "No se encontraron productos", Toast.LENGTH_SHORT).show();
        }
    }
    public void buscar_carro(){
        AdminSqLite admin = new AdminSqLite(this, "castore", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nom = nombre.getText().toString();
        String tie = tienda.getText().toString();

        String consulta = "SELECT precio,inventario from tiendas,tiepro,productos,clipro,clientes where tiendas.idtienda = tiepro.idtienda and tiepro.idproducto = productos.idproducto and productos.idproducto = clipro.idproducto and clipro.idcliente = clientes.idcliente and nombretienda = ? and nombreproducto = ?";
        String[] argumentos = new String[]{tie, nom};
        Cursor fila = bd.rawQuery(consulta, argumentos);

        if (fila.moveToFirst()) {
            //getString obtiene los datos de la tabla
            cantidad.setText(fila.getString(1));
            precio.setText(fila.getString(0));

        } else
            Toast.makeText(this, "Este producto no esta registrado en el carrito",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

    */
}