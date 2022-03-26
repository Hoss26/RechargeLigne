package com.example.rechargeligne;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText _txtLogin,_txtMotdepasse;
    Button _btnConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _txtLogin = (EditText) findViewById(R.id.txtLogin);
        _txtMotdepasse = (EditText) findViewById(R.id.txtMotdepasse);
        _btnConnexion = (Button) findViewById(R.id.btnConnexion);

        _btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = _txtLogin.getText().toString().trim();
                String motdepasse = _txtMotdepasse.getText().toString().trim();

                if (login.equals("admin") && motdepasse.equals("123456")) {
                    Toast.makeText(MainActivity.this, "Connexion avec succée", Toast.LENGTH_LONG).show();
                    Intent IR = new Intent(MainActivity.this, InterfaceRecharge.class);

                    IR.putExtra("login", login);
                    startActivity(IR);

                } else {
                    Toast.makeText(MainActivity.this, "Connexion échoué ", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}