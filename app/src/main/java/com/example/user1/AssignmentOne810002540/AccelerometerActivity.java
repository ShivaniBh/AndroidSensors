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


public class AccelerometerActivity extends Activity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    TextView title, tv, tv1, tv2;
    RelativeLayout layout;
    private int state=0;
    Coordinate c;
    Coordinate last;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        layout = (RelativeLayout)findViewById(R.id.relative2);
        title = (TextView)findViewById(R.id.name);
        tv = (TextView)findViewById(R.id.xval);
        tv1 = (TextView)findViewById(R.id.yval);
        tv2 = (TextView)findViewById(R.id.zval);

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
            title.setText("Accelerometer");
        }
        else {
            title.setText("No Accelerometer Sensor Available");
        }

        Button onButton = (Button)findViewById(R.id.accelerometerOn);
        Button offButton = (Button)findViewById(R.id.accelerometerOff);
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
        getMenuInflater().inflate(R.menu.menu_accelerometer, menu);
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

            title.setText("Accelerometer");
            tv.setText("X : " + "\n" + x);
            tv1.setText("Y : " + "\n" + y);
            tv2.setText("Z : " + "\n" + z);

            //Store significant changes to the database
            MySQLiteHelper db = new MySQLiteHelper(this);
            Coordinate last = db.getSensorCoordinate(db.getLastSensorEntry());
            c = new Coordinate("Accelerometer", x, y, z);
            int yy = calcDifference(last.getxVal(), x, last.getyVal(), y, last.getzVal(), z);
            if (yy == 1) {
                db.addSensorReading(c);
                Toast.makeText(getApplicationContext(),
                        "Added to database", Toast.LENGTH_LONG).show();
            } else {
                //nothing
            }

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

    public void sensorStart(){
        state =1;
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }

    public void sensorToggle(){
        state=0;
        mSensorManager.unregisterListener(this);

    }

    @Override
    protected void onResume(){
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
