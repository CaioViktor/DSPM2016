package br.ufc.dc.dspm.wami;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Main extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,OnMapReadyCallback {

    private GoogleMap googleMap;
    private GoogleApiClient googleApiClient;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        GoogleApiClient.Builder builder = new GoogleApiClient.Builder(this);
        builder.addApi(LocationServices.API);
        builder.addConnectionCallbacks(this);
        builder.addOnConnectionFailedListener(this);
        googleApiClient = builder.build();

    }
    protected void onResume(){
        super.onResume();
        googleApiClient.connect();
    }
    protected void onPause(){
        super.onPause();
        googleApiClient.disconnect();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        LatLng location = new LatLng(-3.7460927, -38.5743825);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(location).zoom(16).build();
        this.googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
    public void localizar(View v){
        Log.i("LOGP","Pediu localização");
        location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        if(location != null){
            LatLng loc = new LatLng(location.getLatitude(),location.getLongitude());
            MarkerOptions marker = new MarkerOptions().position(loc).title("Você está aqui!");
            this.googleMap.addMarker(marker);
            CameraPosition cameraPosition = new CameraPosition.Builder().target(loc).zoom(16).build();
            this.googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i("LOGP","googleAPI contectado");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("LOGP","googleAPI descontectado");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("LOGP","googleAPI falhou");
    }
}
