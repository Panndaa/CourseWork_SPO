package ru.startandroid.coursework;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class Geolocation extends AppCompatActivity
        implements OnMapReadyCallback {

    private static final String TAG = Geolocation.class.getSimpleName();
    private GoogleMap mMap;
    private int seconds;
    private boolean on_off_time, running, readMyParameter;
    private TextView time_run, running_speed, distance;
    private Button startRun;
    private float mDistance, mSpeed;
    //    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient mFusedLocationProviderClient;
    //
//    // A default location (Sydney, Australia) and default zoom to use when location permission is
//    // not granted.
    private final LatLng mDefaultLocation = new LatLng(53.912167, 27.594224);
    private LatLng mLocationOnStart;
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted;
    //
//    // The geographical location where the device is currently located. That is, the last-known
//    // location retrieved by the Fused Location Provider.
    private Location mLastKnownLocation;
    //
//    // Keys for storing activity state.
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";


    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retrieve location and camera position from saved instance state.
        if (savedInstanceState != null) {
            mLastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            CameraPosition mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }

        setContentView(R.layout.activity_geolocation);

//        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
//
//        // Build the map.

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        on_off_time = true;
        startRun = (Button) findViewById(R.id.startRun);
        running_speed = (TextView) findViewById(R.id.running_speed);
        distance = (TextView) findViewById(R.id.distance);
        startRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (on_off_time) {
                    runTimer();
                    readMyParameter = true;
                    getMySpeed(mLastKnownLocation);
                    getMyDistance(mLastKnownLocation);
                    mLocationOnStart = new LatLng(mLastKnownLocation.getLatitude(),
                            mLastKnownLocation.getLongitude());
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(mLastKnownLocation.getLatitude(),
                                    mLastKnownLocation.getLongitude()))
                            .title("Начальная точка")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.run)));

                    running = true;
                    startRun.setText("Завершить");
                    on_off_time = false;
                } else {
                    readMyParameter = false;
                    running = false;
                    startRun.setText("Старт");
                    on_off_time = true;
                }
            }
        });

    }

    //    /**
//     * Saves the state of the map when the activity is paused.
//     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mMap != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, mMap.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, mLastKnownLocation);
            super.onSaveInstanceState(outState);
        }
    }


    /**
     * Manipulates the map when it's available.
     * This callback is triggered when the map is ready to be used.
     */
    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
//
//        // Use a custom info window adapter to handle multiple lines of text in the
//        // info window contents.
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            //
            @Override
            // Return null here, so that getInfoContents() is called next.
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                // Inflate the layouts for the info window, title and snippet.
                View infoWindow = getLayoutInflater().inflate(R.layout.custom_info_contents,
                        (LinearLayout) findViewById(R.id.map), false);

                TextView title = ((TextView) infoWindow.findViewById(R.id.title));
                title.setText(marker.getTitle());

                TextView snippet = ((TextView) infoWindow.findViewById(R.id.snippet));
                snippet.setText(marker.getSnippet());

                return infoWindow;
            }
        });

        // Prompt the user for permission.
        getLocationPermission();
//
        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();

        // Get the current location of the device and set the position of the map.
        getDeviceLocation();
    }
//

    /**
     * Gets the current location of the device, and positions the map's camera.
     */
    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (mLocationPermissionGranted) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = task.getResult();
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(mLastKnownLocation.getLatitude(),
                                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
//                            mMap.addMarker(new MarkerOptions()
//                                    .position(new LatLng(mLastKnownLocation.getLatitude(),
//                                            mLastKnownLocation.getLongitude()))
//                                    .title("Начальная точка")
//                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.run)));


                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
                            mMap.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }


    //    /**
//     * Prompts the user for permission to use the device location.
//     */
    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    /**
     * Handles the result of the request for location permissions.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }


    /**
     * Updates the map's UI settings based on whether the user has granted location permission.
     */
    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void getMySpeed(Location location) {
        if (readMyParameter) {
            running_speed = (TextView) findViewById(R.id.running_speed);
            mSpeed = location.getSpeed();
            running_speed.setText(" " + mSpeed);
        }
        running_speed.setText(" " + mSpeed);
    }

    private void getMyDistance(Location location) {
        if (readMyParameter) {
            mDistance = location.distanceTo(location);
            distance.setText("" + mDistance);
        } else distance.setText("" + mDistance);
    }

    //    }
    private void runTimer() {
        time_run = (TextView) findViewById(R.id.time_run);
        final Handler handler = new Handler();
        Thread te= new Thread(new Runnable() {
            @Override
            public void run() {
                int minutes = (seconds % 3600) / 60;
                int second = seconds % 60;
                String time = String.format("%02d:%02d", minutes, second);
                time_run.setText(time);
                if (running) {
                    seconds++;
                }
                else {
                  handler.removeCallbacks(this);
                }
                handler.postDelayed(this, 1000);
            }
        });
        te.run();
    }
}


