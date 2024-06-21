package com.example.proyecto;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

public class AdminSqLite extends SQLiteOpenHelper{


    // Tabla para usuarios
              private static final String TABLE_USERS = "usuarios";
     private static final String COLUMN_USER_ID = "idusuario";
     private static final String COLUMN_USER_EMAIL = "email";
      private static final String COLUMN_USER_PASSWORD = "password";

              // Tabla para tiendas
              private static final String TABLE_STORES = "tiendas";
      private static final String COLUMN_STORE_ID = "idtienda";
      private static final String COLUMN_STORE_NAME = "nombre";
      private static final String COLUMN_STORE_ADDRESS = "direcciom";
      private static final String COLUMN_STORE_PHONE = "telefono";
      private static final String COLUMN_STORE_EMAIL = "correo";
      private static final String COLUMN_STORE_DESCRIPTION = "descripcion";
      private static final String COLUMN_STORE_PASSWORD = "passwordtienda";


    // Sentencia SQL para crear la tabla de usuarios
      private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
                         + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                         + COLUMN_USER_EMAIL + " TEXT,"
                         + COLUMN_USER_PASSWORD + " TEXT"
                         + ")";

      // Sentencia SQL para crear la tabla de tiendas
      private static final String CREATE_TABLE_STORES = "CREATE TABLE " + TABLE_STORES + "("
                         + COLUMN_STORE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                         + COLUMN_STORE_NAME + " TEXT,"
                         + COLUMN_STORE_ADDRESS + " TEXT,"
                         + COLUMN_STORE_PHONE + " TEXT,"
                         + COLUMN_STORE_EMAIL + " TEXT,"
                         + COLUMN_STORE_DESCRIPTION + " TEXT,"
                         + COLUMN_STORE_PASSWORD + " TEXT"
                         + ")";



    public AdminSqLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
         db.execSQL(CREATE_TABLE_STORES);

         // Agregar usuario por defecto
         db.execSQL("INSERT INTO " + TABLE_USERS + "(" + COLUMN_USER_EMAIL + ", " + COLUMN_USER_PASSWORD + ") VALUES ('admin@gmail.com', 'password123')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}


/**
 *
 * package com.example.myapp;
 *
 * import android.content.Context;
 * import android.database.sqlite.SQLiteDatabase;
 * import android.database.sqlite.SQLiteOpenHelper;
 *
 * public class DatabaseHelper extends SQLiteOpenHelper {
 *
 *
 *
 *
 *
 *     public DatabaseHelper(Context context) {
 *         super(context, DATABASE_NAME, null, DATABASE_VERSION);
 *     }
 *
 *     @Override
 *     public void onCreate(SQLiteDatabase db) {
 *         // Crear tablas
 *         db.execSQL(CREATE_TABLE_USERS);
 *         db.execSQL(CREATE_TABLE_STORES);
 *
 *         // Agregar usuario por defecto
 *         db.execSQL("INSERT INTO " + TABLE_USERS + "(" + COLUMN_USER_EMAIL + ", " + COLUMN_USER_PASSWORD + ") VALUES ('admin@gmail.com', 'password123')");
 *     }
 *
 *     @Override
 *     public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 *         // Borrar tablas si existen y volver a crearlas
 *         db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
 *         db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORES);
 *         onCreate(db);
 *     }
 * }
 *
 *
 *
 */
































//BASE DE DATOS RELACIONAL


/*
*
* package com.example.proyecto;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSqLite extends SQLiteOpenHelper{

    // Tabla para usuarios
    private static final String TABLE_USERS = "usuarios";
    private static final String COLUMN_USER_ID = "idusuario";
    private static final String COLUMN_USER_EMAIL = "email";
    private static final String COLUMN_USER_PASSWORD = "password";

    // Tabla para tiendas
    private static final String TABLE_STORES = "tiendas";
    private static final String COLUMN_STORE_ID = "idtienda";
    private static final String COLUMN_STORE_NAME = "nombre";
    private static final String COLUMN_STORE_ADDRESS = "direcciom";
    private static final String COLUMN_STORE_PHONE = "telefono";
    private static final String COLUMN_STORE_EMAIL = "correo";
    private static final String COLUMN_STORE_DESCRIPTION = "descripcion";
    private static final String COLUMN_STORE_PASSWORD = "passwordtienda";

    // Tabla para productos
    private static final String TABLE_PRODUCTS = "productos";
    private static final String COLUMN_PRODUCT_ID = "idproducto";
    private static final String COLUMN_PRODUCT_NAME = "nombre";
    private static final String COLUMN_PRODUCT_PRICE = "precio";
    private static final String COLUMN_PRODUCT_DESCRIPTION = "descripcion";

    // Tabla intermedia para la relación entre tiendas y productos
    private static final String TABLE_STORE_PRODUCTS = "tienda_productos";
    private static final String COLUMN_STORE_PRODUCT_ID = "id_tienda_producto";
    private static final String COLUMN_STORE_PRODUCT_STORE_ID = "idtienda";
    private static final String COLUMN_STORE_PRODUCT_PRODUCT_ID = "idproducto";
    private static final String COLUMN_STORE_PRODUCT_STOCK = "stock";

    // Tabla para clientes
    private static final String TABLE_CUSTOMERS = "clientes";
    private static final String COLUMN_CUSTOMER_ID = "idcliente";
    private static final String COLUMN_CUSTOMER_NAME = "nombre";
    private static final String COLUMN_CUSTOMER_EMAIL = "email";

    public AdminSqLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla de usuarios
        db.execSQL("CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USER_EMAIL + " TEXT,"
                + COLUMN_USER_PASSWORD + " TEXT"
                + ")");

        // Crear tabla de tiendas
        db.execSQL("CREATE TABLE " + TABLE_STORES + "("
                + COLUMN_STORE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_STORE_NAME + " TEXT,"
                + COLUMN_STORE_ADDRESS + " TEXT,"
                + COLUMN_STORE_PHONE + " TEXT,"
                + COLUMN_STORE_EMAIL + " TEXT,"
                + COLUMN_STORE_DESCRIPTION + " TEXT,"
                + COLUMN_STORE_PASSWORD + " TEXT"
                + ")");

        // Crear tabla de productos
        db.execSQL("CREATE TABLE " + TABLE_PRODUCTS + "("
                + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PRODUCT_NAME + " TEXT,"
                + COLUMN_PRODUCT_PRICE + " REAL,"
                + COLUMN_PRODUCT_DESCRIPTION + " TEXT"
                + ")");

        // Crear tabla intermedia para la relación entre tiendas y productos
        db.execSQL("CREATE TABLE " + TABLE_STORE_PRODUCTS + "("
                + COLUMN_STORE_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_STORE_PRODUCT_STORE_ID + " INTEGER,"
                + COLUMN_STORE_PRODUCT_PRODUCT_ID + " INTEGER,"
                + COLUMN_STORE_PRODUCT_STOCK + " INTEGER,"
                + "FOREIGN KEY(" + COLUMN_STORE_PRODUCT_STORE_ID + ") REFERENCES " + TABLE_STORES + "(" + COLUMN_STORE_ID + "),"
                + "FOREIGN KEY(" + COLUMN_STORE_PRODUCT_PRODUCT_ID + ") REFERENCES " + TABLE_PRODUCTS + "(" + COLUMN_PRODUCT_ID + ")"
                + ")");

        // Crear tabla de clientes
        db.execSQL("CREATE TABLE " + TABLE_CUSTOMERS + "("
                + COLUMN_CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_CUSTOMER_NAME + " TEXT,"
                + COLUMN_CUSTOMER_EMAIL + " TEXT"
                + ")");

        // Agregar usuario por defecto
        db.execSQL("INSERT INTO " + TABLE_USERS + "(" + COLUMN_USER_EMAIL + ", " + COLUMN_USER_PASSWORD + ") VALUES ('admin@gmail.com', 'password123')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No es necesario implementar nada aquí por ahora
    }
}
*/