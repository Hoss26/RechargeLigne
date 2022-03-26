package com.example.rechargeligne;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class InterfaceRecharge extends AppCompatActivity {
    private  static final int REQUEST_CALL =1;

    TextView _txtNumTel,_txtUser,_txtTypeLigne,_txtCodeRecharge,_txtRechargeLigne,_txtConsltSolde,_txtError;
    EditText _edNumTel,_edCodeRecharge,_edRechargeLigne,_edConsltSolde;
    Button _btnRechargerLigne,_btnConsltSolde;
    public String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface_recharge);
        _txtNumTel=(TextView) findViewById(R.id.txtNumTel);
        _txtUser=(TextView) findViewById(R.id.txtUser);
        _txtTypeLigne=(TextView) findViewById(R.id.txtTypeLigne);
        _txtCodeRecharge=(TextView) findViewById(R.id.txtCodeRech);
        _txtRechargeLigne=(TextView) findViewById(R.id.txtRechargeLigne);
        _txtConsltSolde=(TextView) findViewById(R.id.txtConsltSolde);
        _txtError=(TextView) findViewById(R.id.txtError);
        _edNumTel=(EditText) findViewById(R.id.edNumTel);
        _edCodeRecharge=(EditText) findViewById(R.id.edCodeRech);
        _edRechargeLigne=(EditText) findViewById(R.id.edRechargeLigne);
        _edConsltSolde=(EditText) findViewById(R.id.edConsltSolde);
        _btnRechargerLigne=(Button) findViewById(R.id.btnRechargerLigne);
        _btnConsltSolde=(Button) findViewById(R.id.btnConsSolde);

        Intent log = getIntent();
        login = log.getExtras().getString("login");
        _txtUser.setText(login);



        _edNumTel.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String numero = _edNumTel.getText().toString().trim();
                char Ind = _edNumTel.getText().charAt(0);
                String NumTel = String.valueOf(Ind);
                if(numero.length() != 8) {
                    _txtTypeLigne.setText("Le numéro de votre téléphne doit étre 8 chiffres");
                }
                else{
                    if (NumTel.equals("2")) {
                        _txtTypeLigne.setTextColor(Color.parseColor("red"));
                        _txtCodeRecharge.setText("Entrer votre code de recharge (14 chiffres)");
                        _edRechargeLigne.setBackgroundColor(Color.parseColor("red"));
                        _edConsltSolde.setBackgroundColor(Color.parseColor("red"));
                        _edConsltSolde.setText("*100#");
                        _txtTypeLigne.setText("Votre ligne est Ooredoo");
                    } else if (NumTel.equals("5")) {
                        _txtTypeLigne.setTextColor(Color.parseColor("yellow"));
                        _txtCodeRecharge.setText("Entrer votre code de recharge (14 chiffres)");
                        _edRechargeLigne.setBackgroundColor(Color.parseColor("yellow"));
                        _edConsltSolde.setBackgroundColor(Color.parseColor("yellow"));
                        _edConsltSolde.setText("*111#");
                        _txtTypeLigne.setText("Votre ligne est Orange");
                    } else if (NumTel.equals("9")) {
                        _txtTypeLigne.setTextColor(Color.parseColor("blue"));
                        _txtCodeRecharge.setText("Entrer votre code de recharge (13 chiffres)");
                        _edRechargeLigne.setBackgroundColor(Color.parseColor("blue"));
                        _edConsltSolde.setBackgroundColor(Color.parseColor("blue"));
                        _edConsltSolde.setText("*122#");
                        _txtTypeLigne.setText("Votre ligne est Tunisie Télécom");
                    }
                }
                return false;
            }
        });
        _edCodeRecharge.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String code = _edCodeRecharge.getText().toString().trim();
                char numTel = _edNumTel.getText().charAt(0);
                String ligne = String.valueOf(numTel);

                if (ligne.equals("2")) {
                    if(code.length() != 14) {
                        _txtError.setText("Le code de recharge non valide (14 chiffres)");
                    }else {
                        _txtError.setText("");
                        String codeRecharge = _edCodeRecharge.getText().toString().trim();
                        _edRechargeLigne.setText("*100*" + codeRecharge + "#");
                    }
                } else if (ligne.equals("5")) {
                    if(code.length() != 14) {
                        _txtError.setText("Le code de recharge non valide (14 chiffres)");
                    }else {
                        _txtError.setText("");
                        String codeRecharge = _edCodeRecharge.getText().toString().trim();
                        _edRechargeLigne.setText("*101*" + codeRecharge + "#");
                    }
                } else if (ligne.equals("9")) {
                    if(code.length() != 13) {
                        _txtError.setText("Le code de recharge non valide (13 chiffres)");
                    }else {
                        _txtError.setText("");
                        String codeRecharge = _edCodeRecharge.getText().toString().trim();
                        _edRechargeLigne.setText("*123*" + codeRecharge + "#");
                    }
                }

                return false;
            }
        });


        _btnRechargerLigne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RechargerLigne();
            }
        });

        _btnConsltSolde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConsulterSolde();
            }
        });
    }


    private void RechargerLigne(){
        String Code =  _edRechargeLigne.getText().toString();
        if( Code.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(InterfaceRecharge.this, Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(InterfaceRecharge.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);

            } else {

                Intent pp = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Code));
                startActivity(pp);
            }
        }
        else {
            Toast.makeText(InterfaceRecharge.this, "Entrer votre code de recharge", Toast.LENGTH_LONG).show();
        }
    }


    private void ConsulterSolde(){
        String Code =  _edConsltSolde.getText().toString();
        if( Code.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(InterfaceRecharge.this, Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(InterfaceRecharge.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);

            } else {

                Intent pp = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Code));
                startActivity(pp);
            }
        }
        else {
            Toast.makeText(InterfaceRecharge.this, "Entrer votre code de recharge", Toast.LENGTH_LONG).show();
        }
    }



    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                RechargerLigne();
                ConsulterSolde();

            }
            else {
                Toast.makeText(this, "Operation echouée", Toast.LENGTH_LONG).show();
            }
        }
    }
}
