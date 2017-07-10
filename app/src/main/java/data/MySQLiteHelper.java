package data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by okinawa on 08.07.2017.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "sberteth.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "valute";
    public static final String COLUMN_ID = "_id";
    public static final String CHARCODE = "charCode";
    public static final String NOMINAL = "nominal";
    public static final String NAME = "name";
    public static final String VALUE = "value";

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "( " + COLUMN_ID + " integer primary key autoincrement,"
            + CHARCODE + " text not null,"
            + NOMINAL + " text not null,"
            + NAME + " text not null,"
            + VALUE + " text not null);";


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
