package com.example.zeikman.thenewboston;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zeikman on 12/21/15.
 */
public class HotOrNot {
    public static final String KEY_ROW_ID = "_id";
    public static final String KEY_NAME = "persons_name";
    public static final String KEY_HOTNESS = "persons_hotness";

    public static final String DATABASE_NAME = "HotOrNotdb";
    public static final String DATABASE_TABLE = "peopleTable";
    public static final int DATABASE_VERSION = 1;

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    private static class DbHelper extends SQLiteOpenHelper {
        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
                    KEY_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_NAME + " TEXT NOT NULL, " +
                    KEY_HOTNESS + " TEXT NOT NULL);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    public HotOrNot(Context c) {
        ourContext = c;
    }

    public HotOrNot open() throws SQLException {
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        ourHelper.close();
    }

    public long createEntry(String name, String hotness) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(KEY_HOTNESS, hotness);
        return ourDatabase.insert(DATABASE_TABLE, null, cv);
    }

    public String getData() throws SQLException {
        String[] columns = new String[] { KEY_ROW_ID, KEY_NAME, KEY_HOTNESS };
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        String result = "";

        int iRow = c.getColumnIndex(KEY_ROW_ID);
        int iName = c.getColumnIndex(KEY_NAME);
        int iHotness = c.getColumnIndex(KEY_HOTNESS);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            result = result + c.getString(iRow) + " " +
                    c.getString(iName) + " " +
                    c.getString(iHotness) + "\n";
        }

        return result;
    }

    public String getName(long l) throws SQLException {
        String[] columns = new String[] { KEY_ROW_ID, KEY_NAME, KEY_HOTNESS };
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROW_ID + "=" + l, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
            String name = c.getString(1);
            return name;
        }
        return null;
    }

    public String getHotness(long l) throws SQLException {
        String[] columns = new String[] { KEY_ROW_ID, KEY_NAME, KEY_HOTNESS };
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROW_ID + "=" + l, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
            String hotness = c.getString(2);
            return hotness;
        }
        return null;
    }

    public void updateEntry(long lRow, String mName, String mHotness) throws SQLException {
        ContentValues cvUpdate = new ContentValues();
        cvUpdate.put(KEY_NAME, mName);
        cvUpdate.put(KEY_HOTNESS, mHotness);
        ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROW_ID + "=" + lRow, null);
    }

    public void deleteEntry(long lRow1) throws SQLException {
        ourDatabase.delete(DATABASE_TABLE, KEY_ROW_ID + "=" + lRow1, null);
    }
}
