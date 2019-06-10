package com.example.dsaproyectofront;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearUsuario extends AppCompatActivity {


    private APIJuego api;
    EditText nombre;
    EditText pass;
    Button registrarse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearusuario);
        registrarse = findViewById(R.id.registrarse);
        nombre = findViewById(R.id.nombre);
        pass = findViewById(R.id.pass);
        api = APIJuego.retrofit.create(APIJuego.class);


        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pas = pass.getText().toString();
                final String nom = nombre.getText().toString();


                Call<Boolean> usercall = api.crearusuario(nom, pas);

                usercall.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {


                        Boolean a = response.body();

                        if (a == true) {

                            String idUser = "id" + nom;


                        } else {

                            Toast.makeText(getApplicationContext(), "Fallo al poner las cosas", Toast.LENGTH_SHORT);
                        }
                    }


                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                        Toast.makeText(getApplicationContext(), "Fallo con la petición de información", Toast.LENGTH_SHORT);


                    }


                });


            }
        });


    }
}