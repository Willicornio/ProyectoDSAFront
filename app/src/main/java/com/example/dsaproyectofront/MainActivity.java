package com.example.dsaproyectofront;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    private APIJuego api;
    Button loguin;
    Button salir;
    EditText nombre;
    EditText pass;
    TextView info;
    Button crearusuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loguin = findViewById(R.id.button);
        salir = findViewById(R.id.button2);
        nombre = findViewById(R.id.nombre);
        pass = findViewById(R.id.pass);
        info = findViewById(R.id.textView);
        api = APIJuego.retrofit.create(APIJuego.class);
        crearusuario = findViewById(R.id.crearusuario);


        loguin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "hola", Toast.LENGTH_SHORT);
                String name = nombre.getText().toString();
                String contraseña = pass.getText().toString();
                Auth a = new Auth(name, contraseña);
                Call<UsuarioTO> usercall = api.login(a);

                usercall.enqueue(new Callback<UsuarioTO>() {
                    @Override
                    public void onResponse(Call<UsuarioTO> call, Response<UsuarioTO> response) {


                        UsuarioTO usuario = response.body();


                        if (name.equals(usuario.getNombre())) {

                            String idUser = "id" + name; //AQUI PODEMOS HACER UN GET Y PASARLE LE OBJETO ENTERO MEJOR, SI PERO NO SE COMO SE PASA ASÍ QUE DE MOMENTO ASI SE QUEDA
                            Intent mIntent = new Intent(MainActivity.this, MenuActivity.class);
                            mIntent.putExtra("idUser", idUser);
                            startActivity(mIntent);


                        } else {

                            Toast.makeText(getApplicationContext(), "Fallo al poner las cosas", Toast.LENGTH_SHORT);
                        }
                    }


                    @Override
                    public void onFailure(Call<UsuarioTO> call, Throwable t) {

                        Toast.makeText(getApplicationContext(), "Fallo con la petición de información", Toast.LENGTH_SHORT);


                    }


                });


            }
        });

        crearusuario.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent mIntent = new Intent(MainActivity.this, CrearUsuario.class);
                startActivity(mIntent);


            }


        });


    }
}
