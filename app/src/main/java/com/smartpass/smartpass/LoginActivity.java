package com.smartpass.smartpass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

    private EditText edtEmail;
    private EditText edtSenha;
    private TextView txtCadastro, txtEsqueceu;
    private Button btnEntrar;
    String url = "";
    String parametros = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        txtCadastro = findViewById(R.id.txtCadastro);
        txtEsqueceu = findViewById(R.id.txtEsqueceu);
        btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo= connMgr.getActiveNetworkInfo();
                if(networkInfo != null && networkInfo.isConnected())
                {
                    String email = edtEmail.getText().toString();
                    String senha = edtSenha.getText().toString();

                    if(email.isEmpty() || senha.isEmpty())
                    {
                        Toast.makeText(getApplicationContext(), "Informe o usuário e senha!", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        url = "http://192.168.0.10:8080/login/logar.php";

                        parametros = "email=" + email + "&senha=" + senha;

                        new SolicitaDados().execute(url);
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Nenhuma conexão foi detectada!", Toast.LENGTH_LONG).show();
                }
            }
        });

        txtEsqueceu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtEsqueceu.setTypeface(Typeface.DEFAULT_BOLD);
                txtEsqueceu.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }
        });

        txtCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtCadastro.setTypeface(Typeface.DEFAULT_BOLD);
                txtCadastro.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                Intent Cadastro = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(Cadastro);
            }
        });

    }

    public class SolicitaDados extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... urls) {

            return Conexao.postDados(urls[0], parametros);
        }

        @Override
        protected void onPostExecute(String resultado) {

            //Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

            if(resultado.contains("login_ok"))
            {
                Intent AbreInicio = new Intent(LoginActivity.this, MainActivity_home.class);
                startActivity(AbreInicio);
            }
            else if(resultado.contains("login_erro"))
           {
               Toast.makeText(getApplicationContext(), "Usuário ou senha estão incorretos!", Toast.LENGTH_LONG).show();
           }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
