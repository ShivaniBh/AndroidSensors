package com.example.user1.AssignmentOne810002540;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class GpsActivity extends Activity {

    Button btnShowLocation;
    TextView tv;
    TextView tv2;
    GpsTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        final MySQLiteHelper db = new MySQLiteHelper(this);
        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
        tv = (TextView)findViewById(R.id.locationCoordinatesView);
        tv2 = (TextView)findViewById(R.id.pastLocationsView);

        String str = db.getAllCoordinates();
        tv2.setText(str);


        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                gps = new GpsTracker(GpsActivity.this);

                // check if GPS enabled
                if(gps.canGetLocation()){
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    Coordinate newc = new Coordinate(10.654789f, -61.400777f); //test value
                    db.addBook(newc);
                    Coordinate b = db.getCoordinate(db.getLastIdEntry()); //get most recent entry in database

                    int delta = checkLatAndLong(b.getLatitude(), latitude, b.getLongtitude(), longitude);
                    if (delta == 1)
                    {
                        db.addBook(new Coordinate((float)latitude, (float)longitude));
                        Toast.makeText(getApplicationContext(),
                                "Added to database", Toast.LENGTH_LONG).show();
                    }

                        new Thread(new Runnable() {
                            public void run() {
                                gps = new GpsTracker(GpsActivity.this);
                                final double latitude = gps.getLatitude();
                                final double longitude = gps.getLongitude();
                                if (latitude!=0) {
                                    Runnable runnable = new Runnable() {
                                        public void run() {
                                            tv.setText("Latitude: " + latitude + "\nLongtitude " + longitude);
                                        }
                                    };
                                }
                            }
                        }).start();



                    //query db and determine if these coordinates are significant
                    //if so, store them


                    tv.setText("Latitude: "+latitude+"\nLongtitude "+longitude);
                }else{
                    gps.showSettingsAlert(); //Turn on location settings
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_gps, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public int checkLatAndLong(float oldLat, double newLat, float oldLong, double newLong){
        float latDifference = (float) (oldLat-newLat);
        float longDifference = (float) (oldLong-newLong);

        if (latDifference <0){
            latDifference = latDifference * -1;
        }

        if (longDifference <0){
            longDifference = longDifference * -1;
        }

        if ((latDifference>0.000999) &&(longDifference>0.000999)){  //use delta of 0.000999
            return 1;
        }
        else
            return 0;



    }
}
