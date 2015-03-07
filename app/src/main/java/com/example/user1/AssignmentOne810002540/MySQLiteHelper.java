package com.example.user1.AssignmentOne810002540;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MySQLiteHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "GpsDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_GPS_TABLE = "CREATE TABLE gpsCoordinates ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "longtitude REAL, "+
                "latitude REAL )";

        // create gps table
        db.execSQL(CREATE_GPS_TABLE);

        String CREATE_SENSOR_TABLE = "CREATE TABLE sensorCoordinates ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "sensorName TEXT, "+
                "yVal REAL, "+
                "zVal REAL, "+
                "xVal REAL )";

        // create sensor table
        db.execSQL(CREATE_SENSOR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS gpsCoordinates");
        db.execSQL("DROP TABLE IF EXISTS sensorCoordinates");

        // create fresh gps table
        this.onCreate(db);
    }

    // gps table name
    private static final String TABLE_COORDINATES = "gpsCoordinates";
    private static final String SENSOR_COORDINATES = "sensorCoordinates";

    // gps Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_LONGTITUDE = "longtitude";
    private static final String KEY_LATITUDE = "latitude";

    // sensor Table Columns names
    private static final String KEY_ID2 = "id";
    private static final String KEY_SENSORNAME = "sensorName";
    private static final String KEY_XVAL = "xVal";
    private static final String KEY_YVAL = "yVal";
    private static final String KEY_ZVAL = "zVal";

    private static final String[] COLUMNS = {KEY_ID,KEY_LONGTITUDE,KEY_LATITUDE};

    private static final String[] COLUMNS2 = {KEY_ID2,KEY_SENSORNAME,KEY_XVAL, KEY_YVAL, KEY_ZVAL};

    public void addBook(Coordinate coord){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LONGTITUDE, coord.getLongtitude());
        values.put(KEY_LATITUDE, coord.getLatitude());


        db.insert(TABLE_COORDINATES, // table
                null,
                values);
        db.close();
    }

    public void addSensorReading(Coordinate coord){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SENSORNAME, coord.getSensorName());
        values.put(KEY_XVAL, coord.getxVal());
        values.put(KEY_YVAL, coord.getxVal());
        values.put(KEY_ZVAL, coord.getxVal());

        db.insert(SENSOR_COORDINATES, // table
                null,
                values);
        db.close();
    }

    public Coordinate getCoordinate(int id){
        Coordinate cc = new Coordinate(0.000f, 0.000f);
       // if (id<0){
        //    return cc;
       // }
       // else {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor =
                    db.query(TABLE_COORDINATES, // a. table
                            COLUMNS, // b. column names
                            " id = ?", // c. selections
                            new String[]{String.valueOf(id)}, // d. selections args
                            null, // e. group by
                            null, // f. having
                            null, // g. order by
                            null); // h. limit

            //if we got results get the first one
            if (cursor != null)
                cursor.moveToFirst();


            Coordinate coord = new Coordinate();
            coord.setId(Integer.parseInt(cursor.getString(0)));
            coord.setLongtitude(cursor.getFloat(1));
            coord.setLatitude(cursor.getFloat(2));

            return coord;
      //  }
    }


    public Coordinate getSensorCoordinate(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =
                db.query(SENSOR_COORDINATES, // a. table
                        COLUMNS2, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        //if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();


        Coordinate coord = new Coordinate();
        coord.setId(Integer.parseInt(cursor.getString(0)));
        coord.setSensorName(cursor.getString(1));
        coord.setxVal(cursor.getFloat(2));
        coord.setyVal(cursor.getFloat(3));
        coord.setzVal(cursor.getFloat(4));
        return coord;
    }

    public int getLastIdEntry(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_COORDINATES;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToLast(); //last entry in database
        int id = Integer.parseInt(cursor.getString(0));
        return id;

    }

    public int getLastSensorEntry(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + SENSOR_COORDINATES;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToLast(); //last entry in database
        int id = Integer.parseInt(cursor.getString(0));
        return id;
    }


    //public List<Book> getAllCoordinates() {
    public String getAllCoordinates() {
        List<Coordinate> coords = new LinkedList<Coordinate>();

        String s = "";
        String query = "SELECT  * FROM " + TABLE_COORDINATES;


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Coordinate coord = null;
        if (cursor.moveToFirst()) {
            do {
                coord = new Coordinate();
                coord.setId(Integer.parseInt(cursor.getString(0)));
                coord.setLatitude(cursor.getFloat(1));
                coord.setLongtitude(cursor.getFloat(2));
                s = s+"Longtitude: "+coord.getLongtitude()+" Latitude: "+coord.getLatitude() +"\n";

                // Add coord to coords
                coords.add(coord);
            } while (cursor.moveToNext());
        }

        //return coords; //return list of books
        return s;
    }




    public List<String> getAllSensorCoordinatesTry() {
        List<String> coords = new ArrayList<String>();


        String query = "SELECT  * FROM " + SENSOR_COORDINATES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Coordinate coord = null;
        if (cursor.moveToFirst()) {
            do {
                coord = new Coordinate();
                coord.setId(Integer.parseInt(cursor.getString(0)));
                coord.setSensorName(cursor.getString(1));
                coord.setxVal(cursor.getFloat(2));
                coord.setyVal(cursor.getFloat(3));
                coord.setzVal(cursor.getFloat(4));
                String s ="Sensor: "+coord.getSensorName()+" \nX: "+coord.getxVal()+" \nY: "+coord.getyVal()+" \nZ: "+coord.getzVal();
                s = s+"\n";

                // Add coord to coords
                //coords.add(coord);
                coords.add(s);
            } while (cursor.moveToNext());
        }
        return coords; //return list of coordinates from sensors

    }

}