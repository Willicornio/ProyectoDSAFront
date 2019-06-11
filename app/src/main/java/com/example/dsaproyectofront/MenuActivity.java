package com.example.dsaproyectofront;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MenuActivity extends AppCompatActivity {

    private APIJuego api;
    Button jugar;
    Button ranking;
    Button web;
    Button atras;

//aaaaaaaaaaaa


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        jugar = findViewById(R.id.jugar);
        ranking = findViewById(R.id.historial);
        web = findViewById(R.id.web);
        atras = findViewById(R.id.atrás);
        api = APIJuego.retrofit.create(APIJuego.class);









        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //se inicia el juego
            }
        });



        ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MenuActivity.this,MainActivity.class);
                startActivity(mIntent);

            }
        });




        web.setOnClickListener(new View.OnClickListener() {
            @Override  //Cuando clias en el boyçon se te abre la pagina web
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.JUEGO.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });





        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mIntent = new Intent(MenuActivity.this,MainActivity.class);
                startActivity(mIntent);

            }
        });

    }






}
