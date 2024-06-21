package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.mas);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suma();
            }
        });

        Button button1 = (Button) findViewById(R.id.menos);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resta_1();
            }
        });

        Button button2 = (Button) findViewById(R.id.por);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multi_1();
            }
        });

        Button button3 = (Button) findViewById(R.id.entre);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                divi_1();
            }
        });
    }
    public void suma(){
        EditText a = (EditText) findViewById(R.id.numero);
        String a1 = a.getText().toString();

        EditText b = (EditText) findViewById(R.id.numero2);
        String b2 = b.getText().toString();

        EditText res = (EditText) findViewById(R.id.res);

        int resultado1 = Integer.parseInt(a1);
        int resultado2 = Integer.parseInt(b2);

        String rest = Integer.toString(resultado1 + resultado2);
        res.setText(rest);
    }
    public void resta_1(){
        EditText a = (EditText) findViewById(R.id.numero);
        String a1 = a.getText().toString();

        EditText b = (EditText) findViewById(R.id.numero2);
        String b2 = b.getText().toString();

        EditText res = (EditText) findViewById(R.id.res);

        int resultado1 = Integer.parseInt(a1);
        int resultado2 = Integer.parseInt(b2);

        String rest = Integer.toString(resultado1 - resultado2);
        res.setText(rest);
    }
    public void multi_1(){
        EditText a = (EditText) findViewById(R.id.numero);
        String a1 = a.getText().toString();

        EditText b = (EditText) findViewById(R.id.numero2);
        String b2 = b.getText().toString();

        EditText res = (EditText) findViewById(R.id.res);

        int resultado1 = Integer.parseInt(a1);
        int resultado2 = Integer.parseInt(b2);

        String rest = Integer.toString(resultado1 * resultado2);
        res.setText(rest);
    }
    public void divi_1(){
        EditText a = (EditText) findViewById(R.id.numero);
        String a1 = a.getText().toString();

        EditText b = (EditText) findViewById(R.id.numero2);
        String b2 = b.getText().toString();

        EditText res = (EditText) findViewById(R.id.res);

        int resultado1 = Integer.parseInt(a1);
        int resultado2 = Integer.parseInt(b2);

        String rest = Integer.toString(resultado1 / resultado2);
        res.setText(rest);
    }
}