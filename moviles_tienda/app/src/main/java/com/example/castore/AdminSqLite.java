package com.example.castore;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

public class AdminSqLite extends SQLiteOpenHelper{

    public AdminSqLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla de usuarios
        db.execSQL("CREATE TABLE usuarios (" +
                "idusuario INTEGER PRIMARY KEY AUTOINCREMENT," +
                "email TEXT," +
                "password TEXT)");

        // Crear tabla de tiendas
        db.execSQL("CREATE TABLE tiendas (" +
                "idtienda INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombretienda TEXT," +
                "emailtienda TEXT," +
                "passwordtienda TEXT," +
                "direcciontienda TEXT)");

        // Crear tabla de productos
        db.execSQL("CREATE TABLE productos (" +
                "idproducto INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombreproducto TEXT," +
                "precio INTEGER," +
                "inventario INTEGER)");

        // Crear tabla de clientes
        db.execSQL("CREATE TABLE clientes (" +
                "idcliente INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombrecliente TEXT," +
                "apep TEXT," +
                "apem TEXT," +
                "emailcliente TEXT," +
                "passwordcliente TEXT," +
                "direccioncliente TEXT)");

        // Crear tabla intermedia para la relación entre tiendas y productos
        db.execSQL("CREATE TABLE tiepro (" +
                "idtienda INTEGER," +
                "idproducto INTEGER," +
                "FOREIGN KEY(idtienda) REFERENCES tiendas(idtienda)," +
                "FOREIGN KEY(idproducto) REFERENCES productos(idproducto))");

        // Crear tabla intermedia para la relación entre clientes y productos
        db.execSQL("CREATE TABLE clipro (" +
                "idproducto INTEGER," +
                "idcliente INTEGER," +
                "cantidad INTEGER," +
                "FOREIGN KEY(idproducto) REFERENCES productos(idproducto)," +
                "FOREIGN KEY(idcliente) REFERENCES clientes(idcliente))");

        // Agregar usuario por defecto
        db.execSQL("INSERT INTO usuarios (email, password) VALUES ('admin@gmail.com', 'password123')");

        // Agregar tiendas
        db.execSQL("INSERT INTO tiendas (idtienda, nombretienda, emailtienda, passwordtienda, direcciontienda) VALUES (1, 'Walmart', 'walmart@gmail.com', '123', 'morelos #2')");
        db.execSQL("INSERT INTO tiendas (idtienda, nombretienda, emailtienda, passwordtienda, direcciontienda) VALUES (2, 'Soriana', 'soriana@gmail.com', '456', 'libertad #8')");
        db.execSQL("INSERT INTO tiendas (idtienda, nombretienda, emailtienda, passwordtienda, direcciontienda) VALUES (3, 'Costco', 'cotsco@gmail.com', '789', 'insurgentes #1')");
        db.execSQL("INSERT INTO tiendas (idtienda, nombretienda, emailtienda, passwordtienda, direcciontienda) VALUES (4, 'Liverpool', 'liverpool@gmail.com', '134', 'juarez #3')");
        db.execSQL("INSERT INTO tiendas (idtienda, nombretienda, emailtienda, passwordtienda, direcciontienda) VALUES (5, 'Suburbia', 'suburbia@gmail.com', '234', 'independencia #7')");

        // Inserciones de productos para Soriana
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (1, 'Pan', 2, 100)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (2, 'Leche', 20, 50)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (3, 'Huevos', 5, 80)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (4, 'Carne', 15, 70)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (5, 'Frutas', 10, 90)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (6, 'Verduras', 8, 110)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (7, 'Refresco', 3, 120)");

// Inserciones de productos para Walmart
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (8, 'TV', 500, 10)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (9, 'Computadora', 800, 20)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (10, 'Teléfono', 200, 30)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (11, 'Refrigerador', 1000, 15)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (12, 'Lavadora', 700, 25)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (13, 'Microondas', 100, 35)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (14, 'Licuadora', 50, 40)");

// Inserciones de productos para Costco
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (15, 'Papel Higiénico', 6, 200)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (16, 'Detergente', 10, 150)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (17, 'Pañales', 15, 100)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (18, 'Toallas de Cocina', 8, 180)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (19, 'Botellas de Agua', 5, 220)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (20, 'Cerveza', 12, 130)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (21, 'Vino', 20, 80)");

// Inserciones de productos para Liverpool
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (22, 'Ropa', 30, 300)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (23, 'Zapatos', 50, 200)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (24, 'Bolsas', 40, 250)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (25, 'Accesorios', 20, 400)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (26, 'Perfumes', 80, 150)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (27, 'Joyas', 100, 100)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (28, 'Cosméticos', 25, 350)");

// Inserciones de productos para Suburbia
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (29, 'Playeras', 15, 400)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (30, 'Pantalones', 25, 300)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (31, 'Faldas', 20, 350)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (32, 'Chamarras', 40, 200)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (33, 'Sweaters', 30, 250)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (34, 'Vestidos', 35, 180)");
        db.execSQL("INSERT INTO productos (idproducto, nombreproducto, precio, inventario) VALUES (35, 'Trajes', 60, 150)");

        // Relaciones entre productos y tiendas
// Walmart
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (1, 8)"); // TV
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (1, 9)"); // Computadora
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (1, 10)"); // Teléfono
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (1, 11)"); // Refrigerador
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (1, 12)"); // Lavadora
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (1, 13)"); // Microondas
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (1, 14)"); // Licuadora

// Soriana
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (2, 1)"); // Pan
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (2, 2)"); // Leche
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (2, 3)"); // Huevos
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (2, 4)"); // Carne
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (2, 5)"); // Frutas
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (2, 6)"); // Verduras
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (2, 7)"); // Refresco

// Costco
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (3, 15)"); // Papel Higiénico
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (3, 16)"); // Detergente
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (3, 17)"); // Pañales
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (3, 18)"); // Toallas de Cocina
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (3, 19)"); // Botellas de Agua
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (3, 20)"); // Cerveza
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (3, 21)"); // Vino

// Liverpool
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (4, 22)"); // Ropa
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (4, 23)"); // Zapatos
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (4, 24)"); // Bolsas
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (4, 25)"); // Accesorios
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (4, 26)"); // Perfumes
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (4, 27)"); // Joyas
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (4, 28)"); // Cosméticos

// Suburbia
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (5, 29)"); // Playeras
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (5, 30)"); // Pantalones
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (5, 31)"); // Faldas
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (5, 32)"); // Chamarras
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (5, 33)"); // Sweaters
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (5, 34)"); // Vestidos
        db.execSQL("INSERT INTO tiepro (idtienda, idproducto) VALUES (5, 35)"); // Trajes


        //db.execSQL("INSERT INTO clientes (idcliente, nombrecliente,apep,apem,emailcliente,passwordcliente,direccioncliente) VALUES ('1','arturo','sanchez','roman','arturo@gmail.com','123')"); // Trajes

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
