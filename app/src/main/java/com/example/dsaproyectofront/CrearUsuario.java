package com.example.dsaproyectofront;

import android.content.Intent;
import android.os.Parcelable;
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
    TextView textViewN;
    TextView textViewC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearusuario);
        registrarse = findViewById(R.id.registrarse);
        nombre = findViewById(R.id.nombre);
        pass = findViewById(R.id.pass);
        api = APIJuego.retrofit.create(APIJuego.class);
        textViewN = findViewById(R.id.textViewN);
        textViewC = findViewById(R.id.textViewC);


        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pas = pass.getText().toString();
                String nom = nombre.getText().toString();

                Auth auth = new Auth(nom, pas);


                Call<UsuarioTO> call = api.crearusuario(auth);

                call.enqueue(new Callback<UsuarioTO>() {
                    @Override
                    public void onResponse(Call<UsuarioTO> call, Response<UsuarioTO> response) {
                      switch (response.code()){
                          case 201:
                              String id = "id"+nom;
                              UsuarioTO usuario = new UsuarioTO();
                              usuario = response.body();
                              Intent mIntent = new Intent(CrearUsuario.this, MenuActivity.class);
                              mIntent.putExtra("id", id );
                              startActivity(mIntent);
                              break;

                          case 404:
                              Toast.makeText(getApplicationContext(),"El usuario con nombre " + nom + " ya existe", Toast.LENGTH_SHORT).show();
                              break;
                      }
                    }
                    @Override
                    public void onFailure(Call<UsuarioTO> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Fallo con la petición de información", Toast.LENGTH_SHORT);
                    }

                });
            }
        });


    }
}