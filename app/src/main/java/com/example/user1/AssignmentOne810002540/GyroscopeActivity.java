package com.example.user1.AssignmentOne810002540;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class GyroscopeActivity extends Activity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mGyroscope;
    TextView title, tv, tv1, tv2;
    RelativeLayout layout;
    private int state=0;

    Coordinate c;
    Coordinate last;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);

        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        layout = (RelativeLayout)findViewById(R.id.relative10);
        title = (TextView)findViewById(R.id.gyroscopeName);
        tv = (TextView)findViewById(R.id.gyroscopeXVal);
        tv1 = (TextView)findViewById(R.id.gyroscopeYVal);
        tv2 = (TextView)findViewById(R.id.gyroscopeZVal);

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null){
            title.setText("Gyroscope");
        }
        else {
            // Failure! No gyroscope
            title.setText("No Gyroscope Sensor Available");
        }

        Button onButton = (Button)findViewById(R.id.gyroscopeOn);
        Button offButton = (Button)findViewById(R.id.gyroscopeOff);
        onButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start
                sensorStart();
            }
        });

        offButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //stop
                sensorToggle();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gyroscope, menu);
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

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        title.setText("Gyroscope");
        tv.setText("X " + "\n" +x);
        tv1.setText("Y " + "\n" +y);
        tv2.setText("Z " + "\n" +z);

        MySQLiteHelper db = new MySQLiteHelper(this);
        Coordinate last = db.getSensorCoordinate(db.getLastSensorEntry());
        c = new Coordinate("Gyroscope", x, y, z);
        int yy = calcDifference(last.getxVal(), x, last.getyVal(), y, last.getzVal(), z);
        if (yy==1) {
            db.addSensorReading(c);
            Toast.makeText(getApplicationContext(),
                    "Added to database", Toast.LENGTH_LONG).show();
        }
        else {
            //nothing
        }

    }

    public void sensorStart(){
        state =1;
        mSensorManager.registerListener(this, mGyroscope, SensorManager.SENSOR_DELAY_NORMAL);

    }

    public void sensorToggle(){
        state=0;
        mSensorManager.unregisterListener(this);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private int calcDifference(float oldx, float newx, float oldy, float newy, float oldz, float newz){
        float diffx = newx-oldx;
        float diffy = newy-oldy;
        float diffz = newz-oldz;

        //convert everything to positive values
        if (diffx <0){diffx*=-1;}
        if (diffy <0){diffy*=-1;}
        if (diffz <0){diffz*=-1;}

        if ((diffx>1)||(diffy>1)||(diffz>1)){
            return 1;
        }
        return 0;
    }

    @Override
    protected void onResume(){
        super.onResume();
        mSensorManager.registerListener(this, mGyroscope, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
