package ru.startandroid.coursework;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Geolocation extends FragmentActivity implements OnMapReadyCallback {
   public LatLng loc;
    public Location location;
    MapFragment mapFragment;
    GoogleMap map;
    public int seconds;
    private boolean on_off_time, running;
    private TextView time_run;
    //  private GoogleApiClient mGoogleApiClient;
    public  LocationManager locationManager;
    private Button startRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geolocation);

        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        on_off_time = true;
        startRun = (Button) findViewById(R.id.startRun);
    //   locationManager=(LocationManager)getSystemService(LOCATION_SERVICE);
        // mGoogleApiClient.connect();
        startRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                runTimer();
                if (on_off_time) {
                    running=true;
                    startRun.setText("Завершить");
                    on_off_time = false;
                } else {
                    running=false;
                    startRun.setText("Старт");
                    on_off_time = true;
                }
            }
        });
    }
//    @Override
//    protected void onResume(){
//        super.onResume();
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,2,locationListeners );
//    }

    private void runTimer() {
        time_run = (TextView) findViewById(R.id.time_run);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int minutes = (seconds % 3600) / 60;
                int second = seconds % 60;
                String time = String.format("%02d:%02d", minutes, second);
                time_run.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }


    public void onClickStart(View view) {
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }



    public void onMapReady(GoogleMap map) {


        map.addMarker(new MarkerOptions()
               .position(new LatLng(0, 0))
                //.position(loc)
                .title("It's you ")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.run)));
    }

//    public LatLng r(Location location){
//    LatLng loc=new LatLng(location.getLatitude(),location.getLongitude());
//    return loc;
//}
}
