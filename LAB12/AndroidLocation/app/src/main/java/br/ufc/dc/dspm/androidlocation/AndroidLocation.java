package br.ufc.dc.dspm.androidlocation;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class AndroidLocation extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private TextView addressLabel;
    private TextView locationLabel;
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_location);
        this.addressLabel = (TextView) findViewById(R.id.addressTextView);
        this.locationLabel = (TextView) findViewById(R.id.locationTextView);

        GoogleApiClient.Builder builder = new GoogleApiClient.Builder(this);
        builder.addApi(LocationServices.API);
        builder.addConnectionCallbacks(this);
        builder.addOnConnectionFailedListener(this);
        googleApiClient = builder.build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
        locationLabel.setText("Got connected...");
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        locationLabel.setText("Got disconnected...");
        super.onStop();

    }

    public void doConnect(View view) {
        googleApiClient.connect();
        locationLabel.setText("Got connected...");
    }

    public void doDisconnect(View view) {
        googleApiClient.disconnect();
        locationLabel.setText("Got disconnected...");
    }

    public void getLocation(View view) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        String text = "Location = <" + location.getLatitude() + "," + location.getLongitude() + ">";
        this.locationLabel.setText(text);
        GetAddressTask task = new GetAddressTask(this);
        task.execute(location);
    }

    public void doSubscribe(View view) {
        if (googleApiClient.isConnected()) {
            LocationRequest request = new LocationRequest();
            request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            request.setInterval(5000);
            request.setSmallestDisplacement(2);

            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, request, this);
        }
    }

    public void doUnsubscribe(View view) {
        if (googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(this, "Connected!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionSuspended(int value) {
        Toast.makeText(this, "Disconnected!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Toast.makeText(this, "Connection failed...", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        String text = "Updated Location = <" + location.getLatitude() + "," + location.getLongitude() + ">";
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    private class GetAddressTask extends AsyncTask<Location, Void, String> {
        private Context context;

        public GetAddressTask(Context context) {
            super();
            this.context = context;
        }

        @Override
        protected void onPostExecute(String address) {
            addressLabel.setText(address);
        }

        @Override
        protected String doInBackground(Location... params) {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            Location location = params[0];
            List<Address> addresses;
            try {
                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            } catch (IOException ioe) {
                Log.e("GetAddressTask", "IO Exception in getFromLocation()");
                ioe.printStackTrace();
                return "IO Exception trying to get address";
            } catch (IllegalArgumentException iae) {
                String errorString = "Illegal arguments " + Double.toString(location.getLatitude()) + " , " + Double.toString(location.getLongitude()) + " passed to address service";
                Log.e("GetAddressTask", errorString);
                iae.printStackTrace();
                return errorString;
            }
            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);
                String addressText = address.getAddressLine(0) + ", " + address.getAdminArea() + ", " + address.getCountryCode();
                return addressText;
            } else {
                return "No address found!";
            }
        }
    }
}
