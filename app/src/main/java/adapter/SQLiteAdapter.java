package adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

import data.MySQLiteHelper;
import data.ValuteEntity;

/**
 * Created by okinawa on 08.07.2017.
 */

public class SQLiteAdapter {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = {MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.CHARCODE, MySQLiteHelper.NAME,
            MySQLiteHelper.NOMINAL, MySQLiteHelper.VALUE};

    public SQLiteAdapter(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(ValuteEntity entityList) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.CHARCODE, entityList.getCharCode());
        values.put(MySQLiteHelper.NAME, entityList.getName());
        values.put(MySQLiteHelper.NOMINAL, entityList.getNominal());
        values.put(MySQLiteHelper.VALUE, entityList.getValue());
        database.insert(MySQLiteHelper.TABLE_NAME, null, values);
    }

    public void delete () {
        database.delete(MySQLiteHelper.TABLE_NAME, null,null);
    }

    public List<ValuteEntity> query_all() {
        List<ValuteEntity> valuteEntities = new ArrayList<>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String charCode = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.CHARCODE));
            String nominal = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.NOMINAL));
            String value = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.VALUE));
            String name = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.NAME));
            ValuteEntity entity = new ValuteEntity(charCode,nominal,name,value);
            valuteEntities.add(entity);
            cursor.moveToNext();
        }
        cursor.close();
        return valuteEntities;
    }

}
