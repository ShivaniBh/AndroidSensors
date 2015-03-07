package com.example.user1.AssignmentOne810002540;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class DatabaseActivity extends Activity {


  //  TextView tv;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

      //  tv = (TextView)findViewById(R.id.databaseResults);
        lv = (ListView)findViewById(R.id.databaseResults);
        MySQLiteHelper db = new MySQLiteHelper(this);

      //  String str;
     //   str = db.getAllSensorCoordinates();
      //  tv.setText(str);

        //get sensor coordinates as a List
        List sensorResults = new ArrayList<String>();
        sensorResults = db.getAllSensorCoordinatesTry();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sensorResults);
        lv.setAdapter(arrayAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_database, menu);
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
