package com.smartpass.smartpass;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity_bloqueio extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private EditText edtNumero2, edtEmail2, edtSenha2;
    private Button btnCancelar2, btnBloquear;
    private RadioButton rdbRoubo, rdbAvaria , rdbPerda , rdbOutros;
    String url = "";
    String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bloqueio);

        edtNumero2 = findViewById(R.id.edtNumero2);
        edtEmail2 = findViewById(R.id.edtEmail2);
        edtSenha2 = findViewById(R.id.edtSenha2);
        btnCancelar2 = findViewById(R.id.btnCancelar2);
        btnBloquear = findViewById(R.id.btnBloquear);
        rdbRoubo = findViewById(R.id.rdbRoubo);
        rdbAvaria = findViewById(R.id.rdbAvaria);
        rdbPerda = findViewById(R.id.rdbPerda);
        rdbOutros = findViewById(R.id.rdbOutros);

        rdbRoubo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rdbAvaria.setChecked(false);
                rdbPerda.setChecked(false);
                rdbOutros.setChecked(false);
            }
        });

        rdbAvaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rdbRoubo.setChecked(false);
                rdbPerda.setChecked(false);
                rdbOutros.setChecked(false);
            }
        });

        rdbPerda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rdbRoubo.setChecked(false);
                rdbAvaria.setChecked(false);
                rdbOutros.setChecked(false);
            }
        });

        rdbOutros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rdbRoubo.setChecked(false);
                rdbAvaria.setChecked(false);
                rdbPerda.setChecked(false);
            }
        });


        btnBloquear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    String numero = edtNumero2.getText().toString();
                    String email = edtEmail2.getText().toString();
                    String senha = edtSenha2.getText().toString();
                    String motivo = "";
                    if(motivo == "")
                    {
                        if(rdbRoubo.isChecked())
                        {
                            motivo = rdbRoubo.getText().toString();
                        }else if(rdbAvaria.isChecked())
                        {
                            motivo = rdbAvaria.getText().toString();
                        }else if(rdbPerda.isChecked())
                        {
                            motivo = rdbPerda.getText().toString();
                        }else if(rdbOutros.isChecked())
                        {
                            motivo = rdbOutros.getText().toString();
                        }
                    }

                    if (email.isEmpty() || senha.isEmpty() || numero.isEmpty() || motivo.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Informe os dados solicitados!", Toast.LENGTH_LONG).show();
                    } else {
                        url = "http://192.168.0.10:8080/login/bloquear.php";

                        parametros = "numero=" + numero + "&email=" + email + "&senha=" + senha;

                        new SolicitaDados().execute(url);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Nenhuma conexão foi detectada!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancelar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public class SolicitaDados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return Conexao.postDados(urls[0], parametros);
        }

        @Override
        protected void onPostExecute(String resultado) {

            if (resultado.contains("dados_erro")) {
                Toast.makeText(getApplicationContext(), "Dados incorretos!", Toast.LENGTH_LONG).show();
            } else if (resultado.contains("dados_ok")) {

                new AlertDialog.Builder(MainActivity_bloqueio.this).setTitle("Confirmação").setMessage("Tem certeza que deseja bloquear o seu cartão?").setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String motivo = "";
                        String num_cartao = edtNumero2.getText().toString();
                        if(motivo == "")
                        {
                            if(rdbRoubo.isChecked())
                            {
                                motivo = rdbRoubo.getText().toString();
                            }else if(rdbAvaria.isChecked())
                            {
                                motivo = rdbAvaria.getText().toString();
                            }else if(rdbPerda.isChecked())
                            {
                                motivo = rdbPerda.getText().toString();
                            }else if(rdbOutros.isChecked())
                            {
                                motivo = rdbOutros.getText().toString();
                            }
                        }

                        url = "http://192.168.0.10:8080/login/bloquearmotivo.php";
                        parametros = "motivo=" + motivo + "&num_cartao=" + num_cartao;
                        new PostaDados().execute(url);
                    }
                }).setNegativeButton("Não", null).show();

            } else {
                Toast.makeText(getApplicationContext(), "Ocorreu um erro!", Toast.LENGTH_LONG).show();
            }
        }

    }
    public class PostaDados extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... urls) {

            return Conexao.postDados(urls[0], parametros);
        }

        @Override
        protected void onPostExecute(String resultado) {

            Toast.makeText(getApplicationContext(), "Cartão bloqueado!", Toast.LENGTH_LONG).show();
            Intent RetornaInicio = new Intent(MainActivity_bloqueio.this, LoginActivity.class);
            startActivity(RetornaInicio);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent home = new Intent(MainActivity_bloqueio.this, MainActivity_home.class);
            startActivity(home);
            finish();
        } else if (id == R.id.nav_recarga) {
            Intent recarga = new Intent(MainActivity_bloqueio.this, MainActivity_recarga.class);
            startActivity(recarga);
            finish();
        } else if (id == R.id.nav_pontos_recarga) {
            Intent pontos = new Intent(MainActivity_bloqueio.this, MainActivity_pontos_recarga.class);
            startActivity(pontos);
            finish();
        } else if (id == R.id.nav_bloqueio) {

        } else if (id == R.id.nav_ajuda) {
            Intent ajuda = new Intent(MainActivity_bloqueio.this, MainActivity_ajuda.class);
            startActivity(ajuda);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
