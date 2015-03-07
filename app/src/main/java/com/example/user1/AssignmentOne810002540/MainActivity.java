package com.example.user1.AssignmentOne810002540;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accelerometerPage();
        magneticFieldPage();
        gravityPage();
        proximityPage();
        pressurePage();
        temperaturePage();
        linearAccelerationPage();
        rotationVectorPage();
        gyroscopePage();
        gpsPage();
        databasePage();


    }

    public void accelerometerPage() {
        Button btnNavigator = (Button) findViewById(R.id.accelerometerButton);
        btnNavigator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AccelerometerActivity.class);
                startActivity(i);
            }
        });
    }
    public void magneticFieldPage() {
        Button btnNavigator = (Button) findViewById(R.id.magneticFieldButton);
        btnNavigator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MagneticFieldActivity.class);
                startActivity(i);
            }
        });
    }
    public void gravityPage() {
        Button btnNavigator = (Button) findViewById(R.id.gravityButton);
        btnNavigator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GravityActivity.class);
                startActivity(i);
            }
        });
    }
    public void proximityPage() {
        Button btnNavigator = (Button) findViewById(R.id.proximityButton);
        btnNavigator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Proximity.class);
                startActivity(i);
            }
        });
    }

    public void pressurePage() {
        Button btnNavigator = (Button) findViewById(R.id.pressureButton);
        btnNavigator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PressureActivity.class);
                startActivity(i);
            }
        });
    }
    public void linearAccelerationPage() {
        Button btnNavigator = (Button) findViewById(R.id.linearAccelerationButton);
        btnNavigator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LinearAccelerationActivity.class);
                startActivity(i);
            }
        });
    }
    public void rotationVectorPage() {
        Button btnNavigator = (Button) findViewById(R.id.rotationVectorButton);
        btnNavigator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RotationVectorActivity.class);
                startActivity(i);
            }
        });
    }
    public void gyroscopePage() {
        Button btnNavigator = (Button) findViewById(R.id.gyroscopeButton);
        btnNavigator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GyroscopeActivity.class);
                startActivity(i);
            }
        });
    }

    public void temperaturePage() {
        Button btnNavigator = (Button) findViewById(R.id.temperatureButton);
        btnNavigator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TemperatureActivity.class);
                startActivity(i);
            }
        });
    }

    public void gpsPage() {
        Button btnNavigator = (Button) findViewById(R.id.gpsButton);
        btnNavigator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GpsActivity.class);
                startActivity(i);
            }
        });
    }

    public void databasePage() {
        Button btnNavigator = (Button) findViewById(R.id.databaseButton);
        btnNavigator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DatabaseActivity.class);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

