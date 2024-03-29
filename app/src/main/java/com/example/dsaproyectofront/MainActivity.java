package com.example.dsaproyectofront;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private APIJuego api;
    UsuarioTO usuarioTO;
    Button loguin;
    Button salir;
    EditText nombre;
    EditText pass;
    TextView info;
    Button crearusuario;
    TextView textViewnom;
    TextView textViewInfo;
    TextView pruebamapa;
    Mapa mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loguin = findViewById(R.id.button);
        salir = findViewById(R.id.button2);
        nombre = findViewById(R.id.nombre);
        pass = findViewById(R.id.pass);
        info = findViewById(R.id.textViewInfo);
        api = APIJuego.retrofit.create(APIJuego.class);
        crearusuario = findViewById(R.id.crearusuario);
        textViewnom = findViewById(R.id.textViewnom);
        textViewInfo = findViewById(R.id.textViewInfo);




        loguin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nombre.getText().toString();
                String contraseña = pass.getText().toString();
                Auth a = new Auth(name, contraseña);
                Call<UsuarioTO> usercall = api.login(a);

                usercall.enqueue(new Callback<UsuarioTO>() {
                    @Override
                    public void onResponse(Call<UsuarioTO> usercall, Response<UsuarioTO> response) {

                        switch (response.code()) {
                            case 201:
                                 UsuarioTO usuario = response.body();
                                 String nombreTO= response.body().getNombre();
                                 Toast.makeText(getApplicationContext(),"Bienvenido  " + nombreTO, Toast.LENGTH_SHORT).show();
                                    if (name.equals(usuario.getNombre())) {
                                        Intent mIntent = new Intent(MainActivity.this, MenuActivity.class);
                                        mIntent.putExtra("id", usuario.getIdUser());
                                        startActivity(mIntent);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Fallo al poner las credenciales", Toast.LENGTH_SHORT).show();
                                    }
                                    break;

                            case 404:
                                Toast.makeText(getApplicationContext(), "Este usuario no existe", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }




                    @Override
                    public void onFailure(Call<UsuarioTO> call, Throwable t) {

                        Toast.makeText(getApplicationContext(), "Fallo con la petición de información", Toast.LENGTH_SHORT).show();


                    }


                });


            }
        });


        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.exit(0);

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
