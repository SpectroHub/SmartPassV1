package com.smartpass.smartpass;

import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;

public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    private static final String TAG = "MapsFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        try {

            LocationManager locationManager = (LocationManager) Objects.requireNonNull(getActivity()).getSystemService(Context.LOCATION_SERVICE);

            Criteria criteria = new Criteria();

            assert locationManager != null;
            String provider = locationManager.getBestProvider(criteria, true);

            Toast.makeText(getActivity(), "Provider: " + provider, Toast.LENGTH_LONG).show();

            mMap = googleMap;

            //mMap.setOnMapClickListener(this);

            mMap.getUiSettings().setZoomControlsEnabled(true);

            mMap.setMyLocationEnabled(true);

        }catch (SecurityException ex){
            Log.e(TAG, "Error", ex);
        }


        LatLng estFloresta = new LatLng(-19.919680, -43.930786);
        mMap.addMarker(new MarkerOptions().position(estFloresta).title("Posto de atendimento Floresta"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estFloresta));

        LatLng estSavassi = new LatLng(-19.936659, -43.935382);
        mMap.addMarker(new MarkerOptions().position(estSavassi).title("Posto de atendimento Savassi"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estSavassi));

        LatLng estBarreiro = new LatLng(-19.973713, -44.021128);
        mMap.addMarker(new MarkerOptions().position(estBarreiro).title("Estação Barreiro"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estBarreiro));

        LatLng estDiamante = new LatLng(-19.993842, -44.023532);
        mMap.addMarker(new MarkerOptions().position(estDiamante).title("Estação Diamante"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estDiamante));

        LatLng estVendaNova = new LatLng(-19.821226, -43.952818);
        mMap.addMarker(new MarkerOptions().position(estVendaNova).title("Estação Venda Nova"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estVendaNova));

        LatLng estMove_Vilarinho = new LatLng(-19.821424, -43.947237);
        mMap.addMarker(new MarkerOptions().position(estMove_Vilarinho).title("Estação MOVE - Vilarinho"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_Vilarinho));

        LatLng estMove_Pampulha = new LatLng(-19.8421052, -43.9695724);
        mMap.addMarker(new MarkerOptions().position(estMove_Pampulha).title("Estação MOVE - Pampulha"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_Pampulha));

        LatLng estMove_SaoGabriel = new LatLng(-19.864572, -43.927276);
        mMap.addMarker(new MarkerOptions().position(estMove_SaoGabriel).title("Estação MOVE - São Gabriel"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_SaoGabriel));

        LatLng estMove_RiodeJaneiro = new LatLng(-19.916282, -43.938006);
        mMap.addMarker(new MarkerOptions().position(estMove_RiodeJaneiro).title("Quiosque MOVE - Rio De Janeiro"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_RiodeJaneiro));

        LatLng estMove_SaoPaulo = new LatLng(-19.915391, -43.939731);
        mMap.addMarker(new MarkerOptions().position(estMove_SaoPaulo).title("Quiosque MOVE - São Paulo"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_SaoPaulo));

        LatLng estMove_Carijos = new LatLng(-19.919730, -43.930726);
        mMap.addMarker(new MarkerOptions().position(estMove_Carijos).title("Quiosque MOVE - Carijós"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_Carijos));

        LatLng estMove_Tupinambas = new LatLng(-19.919741, -43.930725);
        mMap.addMarker(new MarkerOptions().position(estMove_Tupinambas).title("Quiosque MOVE - Tupinambás"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_Tupinambas));

        LatLng estMove_CristianoGuimaraes = new LatLng(-19.8247047, -43.955493);
        mMap.addMarker(new MarkerOptions().position(estMove_CristianoGuimaraes).title("Estação MOVE - Cristiano Guimarães"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_CristianoGuimaraes));

        LatLng estMove_Planalto = new LatLng(-19.8247622, -43.9556507);
        mMap.addMarker(new MarkerOptions().position(estMove_Planalto).title("Estação MOVE - Planalto"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_Planalto));

        LatLng estMove_SaoJoaoBatista = new LatLng(-19.8272872, -43.9589894);
        mMap.addMarker(new MarkerOptions().position(estMove_SaoJoaoBatista).title("Estação MOVE - São João Batista"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_SaoJoaoBatista));

        LatLng estMove_LagoadoNado = new LatLng(-19.829850, -43.962089);
        mMap.addMarker(new MarkerOptions().position(estMove_LagoadoNado).title("Estação MOVE - Lagoa do Nado"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_LagoadoNado));

        LatLng estMove_Montese = new LatLng(-19.833756, -43.963982);
        mMap.addMarker(new MarkerOptions().position(estMove_Montese).title("Estação MOVE - Montese"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_Montese));

        LatLng estMove_MonteCastelo = new LatLng(-19.838310, -43.966195);
        mMap.addMarker(new MarkerOptions().position(estMove_MonteCastelo).title("Estação MOVE - Monte Castelo"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_MonteCastelo));

        LatLng estMove_Candelaria = new LatLng(-19.810158, -43.965433);
        mMap.addMarker(new MarkerOptions().position(estMove_Candelaria).title("Estação MOVE - Candelária"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_Candelaria));

        LatLng estMove_MinasCaixa = new LatLng(-19.812168, -43.960340);
        mMap.addMarker(new MarkerOptions().position(estMove_MinasCaixa).title("Estação MOVE - Minas Caixa"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_MinasCaixa));

        LatLng estMove_QuadrasdaVilarinho = new LatLng(-19.815185, -43.954650);
        mMap.addMarker(new MarkerOptions().position(estMove_QuadrasdaVilarinho).title("Estação MOVE - Quadras da Vilarinho"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_QuadrasdaVilarinho));

        LatLng estMove_UPAVendaNova = new LatLng(-19.817773, -43.952895);
        mMap.addMarker(new MarkerOptions().position(estMove_UPAVendaNova).title("Estação MOVE - UPA Venda Nova"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_UPAVendaNova));

        LatLng estMove_SantaRosa = new LatLng(-19.847955, -43.966462);
        mMap.addMarker(new MarkerOptions().position(estMove_SantaRosa).title("Estação MOVE - Santa Rosa"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_SantaRosa));

        LatLng estMove_Mineirao = new LatLng(-19.857651, -43.960028);
        mMap.addMarker(new MarkerOptions().position(estMove_Mineirao).title("Estação MOVE - Mineirão"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_Mineirao));

        LatLng estMove_UFMG = new LatLng(-19.862684, -43.958155);
        mMap.addMarker(new MarkerOptions().position(estMove_UFMG).title("Estação MOVE - UFMG"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_UFMG));

        LatLng estMove_Liberdade = new LatLng(-19.866330, -43.956776);
        mMap.addMarker(new MarkerOptions().position(estMove_Liberdade).title("Estação MOVE - Liberdade"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_Liberdade));

        LatLng estMove_ColegioMilitar = new LatLng(-19.872655, -43.954404);
        mMap.addMarker(new MarkerOptions().position(estMove_ColegioMilitar).title("Estação MOVE - Colégio Militar"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_ColegioMilitar));

        LatLng estMove_SaoFrancisco = new LatLng(-19.879360, -43.951958);
        mMap.addMarker(new MarkerOptions().position(estMove_SaoFrancisco).title("Estação MOVE - São Francisco"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_SaoFrancisco));

        LatLng estMove_Cachoeirinha = new LatLng(-19.880018, -43.951730);
        mMap.addMarker(new MarkerOptions().position(estMove_Cachoeirinha).title("Estação MOVE - Cachoeirinha"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_Cachoeirinha));

        LatLng estMove_AmericoVespucio = new LatLng(-19.885037, -43.951057);
        mMap.addMarker(new MarkerOptions().position(estMove_AmericoVespucio).title("Estação MOVE - Américo Vespúcio"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_AmericoVespucio));

        LatLng estMove_Aparecida = new LatLng(-19.888967, -43.950103);
        mMap.addMarker(new MarkerOptions().position(estMove_Aparecida).title("Estação MOVE - Aparecida"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_Aparecida));

        LatLng estMove_Operarios = new LatLng(-19.894926, -43.949606);
        mMap.addMarker(new MarkerOptions().position(estMove_Operarios).title("Estação MOVE - Operários"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_Operarios));

        LatLng estMove_HospitalBeloHorizonte = new LatLng(-19.898551, -43.947333);
        mMap.addMarker(new MarkerOptions().position(estMove_HospitalBeloHorizonte).title("Estação MOVE - Hospital Belo Horizonte"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_HospitalBeloHorizonte));

        LatLng estMove_IAPI = new LatLng(-19.902061, -43.945127);
        mMap.addMarker(new MarkerOptions().position(estMove_IAPI).title("Estação MOVE - IAPI"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_IAPI));

        LatLng estMove_HospitalOdilonBehrens = new LatLng(-19.904636, -43.944464);
        mMap.addMarker(new MarkerOptions().position(estMove_HospitalOdilonBehrens).title("Estação MOVE - Hospital Odilon Behrens"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_HospitalOdilonBehrens));

        LatLng estMove_SENAI = new LatLng(-19.906127, -43.943618);
        mMap.addMarker(new MarkerOptions().position(estMove_SENAI).title("Estação MOVE - SENAI"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_SENAI));

        LatLng estMove_MinasShopping = new LatLng(-19.877354, -43.928878);
        mMap.addMarker(new MarkerOptions().position(estMove_MinasShopping).title("Estação MOVE - Minas Shopping"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_MinasShopping));

        LatLng estMove_OuroMinas = new LatLng(-19.877854, -43.929032);
        mMap.addMarker(new MarkerOptions().position(estMove_OuroMinas).title("Estação MOVE - Ouro Minas"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_OuroMinas));

        LatLng estMove_Uniao = new LatLng(-19.881009, -43.929939);
        mMap.addMarker(new MarkerOptions().position(estMove_Uniao).title("Estação MOVE - União"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_Uniao));

        LatLng estMove_Ipiranga = new LatLng(-19.883042, -43.930019);
        mMap.addMarker(new MarkerOptions().position(estMove_Ipiranga).title("Estação MOVE - Ipiranga"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_Ipiranga));

        LatLng estMove_CidadeNova = new LatLng(-19.887493, -43.929338);
        mMap.addMarker(new MarkerOptions().position(estMove_CidadeNova).title("Estação MOVE - Cidade Nova"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_CidadeNova));

        LatLng estMove_FeiradosProdutores = new LatLng(-19.891369, -43.928563);
        mMap.addMarker(new MarkerOptions().position(estMove_FeiradosProdutores).title("Estação MOVE - Feira dos Produtores"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_FeiradosProdutores));

        LatLng estMove_SaoJudasTadeu = new LatLng(-19.895725, -43.927379);
        mMap.addMarker(new MarkerOptions().position(estMove_SaoJudasTadeu).title("Estação MOVE - São Judas Tadeu"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_SaoJudasTadeu));

        LatLng estMove_SagradaFamilia = new LatLng(-19.900194, -43.928209);
        mMap.addMarker(new MarkerOptions().position(estMove_SagradaFamilia).title("Estação MOVE - Sagrada Família"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_SagradaFamilia));

        LatLng estMove_SilvianoBrandao = new LatLng(-19.903592, -43.931958);
        mMap.addMarker(new MarkerOptions().position(estMove_SilvianoBrandao).title("Estação MOVE - Silviano Brandão"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(estMove_SilvianoBrandao));
    }
}
