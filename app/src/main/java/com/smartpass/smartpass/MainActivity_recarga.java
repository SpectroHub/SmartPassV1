package com.smartpass.smartpass;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity_recarga extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private EditText edtRecarga;
    private Button btnCancelar3, btnRecarregar;
    private ImageButton imgVisa, imgMaster, imgBoleto;
    private TextView txtSaldo, txtExtrato;
    String url = "";
    String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recarga);

        edtRecarga = findViewById(R.id.edtRecarga);
        btnCancelar3 = findViewById(R.id.btnCancelar3);
        btnRecarregar = findViewById(R.id.btnRecarregar);
        imgVisa = findViewById(R.id.imgVisa);
        imgMaster = findViewById(R.id.imgMaster);
        imgBoleto = findViewById(R.id.imgBoleto);
        txtSaldo = findViewById(R.id.txtSaldo);
        txtExtrato = findViewById(R.id.txtExtrato);

        txtExtrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtExtrato.setTypeface(Typeface.DEFAULT_BOLD);
                txtExtrato.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }
        });

        btnCancelar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnRecarregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    String valor = edtRecarga.getText().toString();

                    if (valor.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Informe o valor da recarga!", Toast.LENGTH_LONG).show();
                    } else {
                        url = "http://192.168.0.10:8080/login/recarga.php";

                        // parametros = ;

                        new MainActivity_recarga.SolicitaDados().execute(url);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Nenhuma conexão foi detectada!", Toast.LENGTH_LONG).show();
                }
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
            Intent intent = new Intent(MainActivity_recarga.this, MainActivity_home.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_recarga) {

        } else if (id == R.id.nav_pontos_recarga) {
            Intent intent = new Intent(MainActivity_recarga.this, MainActivity_pontos_recarga.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_bloqueio) {
            Intent intent = new Intent(MainActivity_recarga.this, MainActivity_bloqueio.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_ajuda) {
            Intent intent = new Intent(MainActivity_recarga.this, MainActivity_ajuda.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public class SolicitaDados extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... urls) {

            return Conexao.postDados(urls[0], parametros);
        }

        @Override
        protected void onPostExecute(String resultado) {

            Toast.makeText(getApplicationContext(), "Recarga efetuada com sucesso!", Toast.LENGTH_LONG).show();
            Intent RetornaInicio = new Intent(MainActivity_recarga.this, LoginActivity.class);
            startActivity(RetornaInicio);
        }
    }
}
