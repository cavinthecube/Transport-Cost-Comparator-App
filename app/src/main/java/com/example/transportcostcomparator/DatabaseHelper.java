package com.example.transportcostcomparator;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.health.connect.datatypes.units.Energy;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database name and version
    private static String name = "Transport.db";
    private static int version = 1;

    public DatabaseHelper(Context context){
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creating Transport table
        db.execSQL("CREATE TABLE Transport ("+"id INTEGER PRIMARY KEY AUTOINCREMENT,"+"Mode TEXT, "+" Transport TEXT, "+"Daily_Cost REAL, "+"Monthly_Cost REAL, "+"Monthly_Distance REAL, "+"Cost_Category TEXT,"+"Recommendation TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Deletes and recreates table if it already exists
        db.execSQL("DROP TABLE IF EXISTS Transport");
        onCreate(db);
    }

    public boolean insertTransport(String Mode, String Transport, double Daily_Cost, double Monthly_Cost, double Monthly_Distance, String Cost_Category, String Recommendation) {
        // allows database to be written to
        SQLiteDatabase n = this.getWritableDatabase();

        // stores appliance information
        ContentValues values = new ContentValues();

        values.put("Mode", Mode);
        values.put("Transport", Transport);
        values.put("Daily_Cost", Daily_Cost);
        values.put("Monthly_Cost", Monthly_Cost);
        values.put("Monthly_Distance", Monthly_Distance);
        values.put("Cost_Category", Cost_Category);
        values.put("Recommendation", Recommendation);

        //Inserts information to table
        long result = n.insert("Transport", null, values);

        return result != -1;
    }

    public Cursor getAppliances() {

        // Opens database so it can be read
        SQLiteDatabase n = this.getReadableDatabase();

        // gets appliances from Energy table
        Cursor cursor = n.rawQuery("SELECT * FROM Transport", null);

        return cursor;
    }

    public String displayDb() {
        String db = "";
        SQLiteDatabase n = getReadableDatabase();

        String query = "SELECT * FROM Transport";

        // stores results in a cursor
        Cursor c = n.rawQuery(query, null);

        // Moves cursor to the last saved appliance
        if (c.moveToLast()) {

            // Gets column position
            int modeIndex = c.getColumnIndex("Mode");
            int transportIndex = c.getColumnIndex("Transport");
            int dailyIndex = c.getColumnIndex("Daily_Cost");
            int monthlyIndex = c.getColumnIndex("Monthly_Cost");
            int distanceIndex = c.getColumnIndex("Monthly_Distance");
            int categoryIndex = c.getColumnIndex("Cost_Category");
            int recIndex = c.getColumnIndex("Recommendation");

            if (modeIndex != -1) {
                db += "Mode: " + c.getString(modeIndex) + "\n\n";
            }
            if (transportIndex != -1) {
                db += "Transport: " + c.getString(transportIndex) + "\n\n";
            }
            if (dailyIndex != -1) {
                db += "Daily Cost: R" + c.getDouble(dailyIndex) + "\n\n";
            }
            if (monthlyIndex != -1) {
                db += "Monthly Cost: R" + c.getDouble(monthlyIndex) + "\n\n";
            }
            if (distanceIndex != -1) {
                db += "Monthly Distance: " + c.getDouble(distanceIndex) + " km\n\n";
            }
            if (categoryIndex != -1) {
                db += "Cost Category: " + c.getString(categoryIndex) + "\n\n";
            }
            if (recIndex != -1) {
                db += "Recommendation: " + c.getString(recIndex) + "\n\n";
            }
        }

        // closes cursor
        c.close();
        return db;
    }

}
