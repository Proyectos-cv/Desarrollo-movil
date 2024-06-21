package com.example.aplicacion_bd;

import androidx.appcompat.app.AppCompatActivity;

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
    EditText numero_control, nombre, telefono;
    Button btn_guardar, btn_control, btn_nommbre, btn_eliminar, btn_modificar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias a las vistas
        numero_control = findViewById(R.id.numero_control);
        nombre = findViewById(R.id.nombre);
        telefono = findViewById(R.id.telefono);
        btn_guardar = findViewById(R.id.btn_guardar);
        btn_control = findViewById(R.id.btn_control);
        btn_nommbre = findViewById(R.id.btn_nombre);
        btn_eliminar = findViewById(R.id.btn_eliminar);
        btn_modificar = findViewById(R.id.btn_modificar);

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarEstudiante();
            }
        });

        btn_nommbre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultaEstudiante();
            }
        });

        btn_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultarEstudiante();
            }
        });

        btn_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarEstudiante();
            }
        });

        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarEstudiante();
            }
        });
    }

    public void guardarEstudiante() {
        //en caso de no existir la BDD se crea y la llama controlEscolar
        //version:1 es que solo existe la version 1 si hacemos cambios seria un 2
        AdminSqLite admin = new AdminSqLite(this, "controlEscolar", null, 1);
        //aquí se abre la BDD en modo lectura/escritura
        SQLiteDatabase bd = admin.getWritableDatabase();

        String control = numero_control.getText().toString();
        String name = nombre.getText().toString();
        String phone = telefono.getText().toString();
        //Está línea es la que ayuda a crear una nueva fila en la BDD
        ContentValues registro = new ContentValues();
        //put inicializa los datos a guardar
        registro.put("numcontrol", control);
        registro.put("nombrealum", name);
        registro.put("telalum", phone);
        //insertamos los datos en la BDD
        bd.insert("estudiantes", null, registro);
        //no olvides cerrar la BDD
        bd.close();
        numero_control.setText("");
        nombre.setText("");
        telefono.setText("");
        //Msj de aviso al usuario
        Toast.makeText(this, "El estudiante se guardo correctamente", Toast.LENGTH_SHORT).show();
    }

    public void consultarEstudiante() {
        AdminSqLite admin = new AdminSqLite(this, "controlEscolar", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String con = numero_control.getText().toString();
        // Cursor nos ayuda a almacenar una fila de datos en caso de encontrar
        //a quien buscamos,
        Cursor fila = bd.rawQuery("select nombrealum, telalum from estudiantes where numcontrol=" + con, null);
        //moveToFirst retorna 1 en caso de encontrar el registro 0 en caso contrario
        if (fila.moveToFirst()) {
            //getString obtiene los datos de la tabla
            nombre.setText(fila.getString(0));
            telefono.setText(fila.getString(1));
        } else
            Toast.makeText(this, "Este número de control no existe",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }
    public void consultaEstudiante(){
        AdminSqLite admin = new AdminSqLite(this, "controlEscolar", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombr = nombre.getText().toString();
        //ojo con las comillas simples, las lleva porque el campo nombre es de tipo texto
        Cursor fila = bd.rawQuery("select numcontrol,telalum from estudiantes where nombrealum='" + nombr +"'", null);
        if (fila.moveToFirst()) {
            numero_control.setText(fila.getString(0));
            telefono.setText(fila.getString(1));
        } else
            Toast.makeText(this, "El estudiante con este nombre no existe",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }
    public void modificarEstudiante(){
        AdminSqLite admin = new AdminSqLite(this, "controlEscolar", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String control = numero_control.getText().toString();
        String nombr = nombre.getText().toString();
        String tel = telefono.getText().toString();
        ContentValues registro = new ContentValues();
        //los put son los campos que modificaremos
        registro.put("numcontrol", control);
        registro.put("nombrealum", nombr);
        registro.put("telalum", tel);
        int cant = bd.update("estudiantes", registro, "numcontrol=" + control, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "Datos actualizados correctamente", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "No existe el estudiante en la BDD",
                    Toast.LENGTH_SHORT).show();
    }
    public void eliminarEstudiante(){
        AdminSqLite admin = new AdminSqLite(this, "controlEscolar", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String con= numero_control.getText().toString();
        //delete retorna un entero, el número de registros que eliminó
        int bandera = bd.delete("estudiantes", "numcontrol=" + con, null);
        bd.close();
        numero_control.setText("");
        nombre.setText("");
        telefono.setText("");
        if (bandera == 1)
            Toast.makeText(this, "Estudiante dado de baja",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe el estudiante en la BDD",
                    Toast.LENGTH_SHORT).show();
    }
}