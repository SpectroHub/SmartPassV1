package com.smartpass.smartpass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CadastroActivity extends Activity {

    private EditText edtNome, edtEmail1, edtSenha1, edtNumero, edtCPF, edtCelular;
    private Button btnCancelar, btnCadastrar;
    private ImageView imgSenha;
    String url = "";
    String parametros = "";
    Boolean validaSenha = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtCelular= findViewById(R.id.edtCelular);
        edtNome= findViewById(R.id.edtNome);
        edtEmail1= findViewById(R.id.edtEmail1);
        edtSenha1= findViewById(R.id.edtSenha1);
        edtNumero= findViewById(R.id.edtNumero);
        edtCPF= findViewById(R.id.edtCPF);
        btnCancelar= findViewById(R.id.btnCancelar);
        btnCadastrar= findViewById(R.id.btnCadastrar);
        imgSenha= findViewById(R.id.imgSenha);

        edtSenha1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() > 16 || editable.length() < 8 )
                {
                    Resources resources = getResources();
                    imgSenha.setImageDrawable(resources.getDrawable(R.drawable.error));
                    validaSenha = false;
                }
                else
                {
                    Resources resources = getResources();
                    imgSenha.setImageDrawable(resources.getDrawable(R.drawable.check));
                    validaSenha = true;
                }
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo= connMgr.getActiveNetworkInfo();
                if(networkInfo != null && networkInfo.isConnected())
                {
                    String nome = edtNome.getText().toString();
                    String email = edtEmail1.getText().toString();
                    String senha = edtSenha1.getText().toString();
                    String numero = edtNumero.getText().toString();
                    String cpf = edtCPF.getText().toString();
                    String celular = edtCelular.getText().toString();

                    if(validaSenha == false)
                     {
                         Toast.makeText(getApplicationContext(), "Informe uma senha válida!", Toast.LENGTH_LONG).show();
                     }

                    if(nome.isEmpty() || email.isEmpty() || senha.isEmpty() || numero.isEmpty() || cpf.isEmpty() || celular.isEmpty())
                    {
                        Toast.makeText(getApplicationContext(), "Favor preencher todos os campos!", Toast.LENGTH_LONG).show();
                    }

                    else
                    {
                        url = "http://192.168.0.10:8080/login/registrar.php";

                        parametros = "nome=" + nome + "&email=" + email + "&senha=" + senha + "&numero=" + numero + "&cpf=" + cpf + "&celular=" + celular;

                        new SolicitaDados().execute(url);
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Nenhuma conexão foi detectada!", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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


            if(resultado.contains("email_erro"))
            {
                Toast.makeText(getApplicationContext(), "Cartão já cadastrado para este e-mail!", Toast.LENGTH_LONG).show();
            }
            else if(resultado.contains("numero_erro"))
            {
                Toast.makeText(getApplicationContext(), "Cartão já cadastrado!", Toast.LENGTH_LONG).show();
            }
            else if(resultado.contains("cpf_erro"))
            {
                Toast.makeText(getApplicationContext(), "CPF já cadastrado para este e-mail!", Toast.LENGTH_LONG).show();
            }
            else if(resultado.contains("cpf_erro2"))
            {
                Toast.makeText(getApplicationContext(), "CPF já cadastrado no sistema!", Toast.LENGTH_LONG).show();
            }
            else if(resultado.contains("Registro_OK"))
            {
                Toast.makeText(getApplicationContext(), "Registro concluído com sucesso!", Toast.LENGTH_LONG).show();
                Intent AbreInicio = new Intent(CadastroActivity.this, LoginActivity.class);
                startActivity(AbreInicio);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Ocorreu um erro!", Toast.LENGTH_LONG).show();
            }
        }



    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}