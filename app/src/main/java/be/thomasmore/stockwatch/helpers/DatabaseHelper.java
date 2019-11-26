package be.thomasmore.stockwatch.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "usa";

    // uitgevoerd bij instantiatie van DatabaseHelper
    // -> als database nog niet bestaat, dan creëren (call onCreate)
    // -> als de DATABASE_VERSION hoger is dan de huidige versie,
    //    dan upgraden (call onUpgrade)

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // methode wordt uitgevoerd als de database gecreëerd wordt
    // hierin de tables creëren en opvullen met gegevens
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_PARTY = "CREATE TABLE party (" +
                "id INTEGER PRIMARY KEY," +
                "name TEXT)";
        db.execSQL(CREATE_TABLE_PARTY);

        String CREATE_TABLE_PRESIDENT = "CREATE TABLE president (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "term TEXT, " +
                "partyId INTEGER, " +
                "FOREIGN KEY (partyId) REFERENCES party(id))";
        db.execSQL(CREATE_TABLE_PRESIDENT);

        insertParties(db);
        insertPresidents(db);
    }

    private void insertParties(SQLiteDatabase db) {
        db.execSQL("INSERT INTO party (id, name) VALUES (1, 'Republican Party');");
        db.execSQL("INSERT INTO party (id, name) VALUES (2, 'Democratic Party');");
    }

    private void insertPresidents(SQLiteDatabase db) {
        db.execSQL("INSERT INTO president (name, term, partyId) VALUES ('Roosevelt, Franklin Delano', '1933-1945', 2);");
        db.execSQL("INSERT INTO president (name, term, partyId) VALUES ('Truman, Harry ', '1945-1953', 2);");
        db.execSQL("INSERT INTO president (name, term, partyId) VALUES ('Eisenhower, Dwight David', '1953-1961', 1);");
        db.execSQL("INSERT INTO president (name, term, partyId) VALUES ('Kennedy, John Fitzgerald', '1961-1963', 2);");
        db.execSQL("INSERT INTO president (name, term, partyId) VALUES ('Johnson, Lyndon Baines ', '1963-1969', 2);");
        db.execSQL("INSERT INTO president (name, term, partyId) VALUES ('Nixon, Richard Milhous ', '1969-1974', 1);");
        db.execSQL("INSERT INTO president (name, term, partyId) VALUES ('Ford, Gerald Rudolph ', '1974-1977', 1);");
        db.execSQL("INSERT INTO president (name, term, partyId) VALUES ('Carter, James Earl Jr.', '1977-1981', 2);");
        db.execSQL("INSERT INTO president (name, term, partyId) VALUES ('Reagan, Ronald Wilson', '1981-1989', 1);");
        db.execSQL("INSERT INTO president (name, term, partyId) VALUES ('Bush, George Herbert Walker ', '1989-1993', 1);");
        db.execSQL("INSERT INTO president (name, term, partyId) VALUES ('Clinton, William Jefferson ', '1993-2001', 2);");
        db.execSQL("INSERT INTO president (name, term, partyId) VALUES ('Bush, George Walker', '2001-2009', 1);");
    }

    // methode wordt uitgevoerd als database geupgrade wordt
    // hierin de vorige tabellen wegdoen en opnieuw creëren
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS president");
        db.execSQL("DROP TABLE IF EXISTS party");

        // Create tables again
        onCreate(db);
    }

    //-------------------------------------------------------------------------------------------------
    //  CRUD Operations
    //-------------------------------------------------------------------------------------------------

    // insert-methode met ContentValues
    public long insertPresident(President president) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", president.getName());
        values.put("term", president.getTerm());
        values.put("partyId", president.getPartyId());

        long id = db.insert("president", null, values);

        db.close();
        return id;
    }

    // update-methode met ContentValues
    public boolean updatePresident(President president) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", president.getName());
        values.put("term", president.getTerm());
        values.put("partyId", president.getPartyId());

        int numrows = db.update(
                "president",
                values,
                "id = ?",
                new String[] { String.valueOf(president.getId()) });

        db.close();
        return numrows > 0;
    }

    // delete-methode
    public boolean deletePresident(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int numrows = db.delete(
                "president",
                "id = ?",
                new String[] { String.valueOf(id) });

        db.close();
        return numrows > 0;
    }

    // query-methode
    public President getPresident(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                "president",      // tabelnaam
                new String[] { "id", "name", "term", "partyId" }, // kolommen
                "id = ?",  // selectie
                new String[] { String.valueOf(id) }, // selectieparameters
                null,           // groupby
                null,           // having
                null,           // sorting
                null);          // ??

        President president = new President();

        if (cursor.moveToFirst()) {
            president = new President(cursor.getLong(0),
                    cursor.getString(1), cursor.getString(2), cursor.getLong(3));
        }
        cursor.close();
        db.close();
        return president;
    }

    // rawQuery-methode
    public List<President> getPresidents() {
        List<President> lijst = new ArrayList<President>();

        String selectQuery = "SELECT  * FROM president ORDER BY term";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                President president = new President(cursor.getLong(0),
                        cursor.getString(1), cursor.getString(2), cursor.getLong(3));
                lijst.add(president);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }

    // rawQuery-methode
    public List<Party> getParties() {
        List<Party> lijst = new ArrayList<Party>();

        String selectQuery = "SELECT  * FROM party ORDER BY name";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Party party = new Party(cursor.getLong(0), cursor.getString(1));
                lijst.add(party);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }

    // rawQuery-methode
    public int getCountPresidents() {
        String selectQuery = "SELECT  * FROM president";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        int aantal = cursor.getCount();

        cursor.close();
        db.close();
        return aantal;
    }

}
