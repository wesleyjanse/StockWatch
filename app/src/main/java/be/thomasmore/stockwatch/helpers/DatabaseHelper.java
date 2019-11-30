package be.thomasmore.stockwatch.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.stockwatch.models.Company;
import be.thomasmore.stockwatch.models.Crypto;
import be.thomasmore.stockwatch.models.Forex;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "favorites";

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
        String CREATE_TABLE_FAVORITECOMPANY = "CREATE TABLE favoriteCompany(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "symbol TEXT," +
                "name TEXT," +
                "price REAL," +
                "beta REAL," +
                "volAvg REAL," +
                "mktCap REAL," +
                "lastDiv REAL," +
                "range TEXT," +
                "changes REAL," +
                "changesPercentage REAL," +
                "exchange TEXT," +
                "description TEXT," +
                "industry TEXT," +
                "website TEXT," +
                "ceo TEXT," +
                "sector TEXT," +
                "image TEXT)";
        db.execSQL(CREATE_TABLE_FAVORITECOMPANY);

        String CREATE_TABLE_FAVORITECRYPTO = "CREATE TABLE favoriteCrypto(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ticker TEXT," +
                "name TEXT," +
                "price REAL," +
                "changes REAL," +
                "marketCapitalization REAL)";
        db.execSQL(CREATE_TABLE_FAVORITECRYPTO);

        String CREATE_TABLE_FAVORITEFOREX = "CREATE TABLE favoriteForex(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ticker TEXT," +
                "bid REAL," +
                "ask REAL," +
                "open REAL," +
                "low REAL," +
                "high REAL," +
                "changes REAL," +
                "date TEXT)";
        db.execSQL(CREATE_TABLE_FAVORITEFOREX);

        //insertFavoriteCompany(db);
        insertFavoriteCrypto(db);
        //insertFavoriteForex(db);
    }

    //private void insertFavorite(SQLiteDatabase db, Crypto crypto, Forex forex, Company company) {
    //}
//    private void insertFavoriteCompany(SQLiteDatabase db) {
//
//    }

    private void insertFavoriteCrypto(SQLiteDatabase db) {
        db.execSQL("INSERT INTO favoriteCrypto (id, ticker, name, price, changes, marketCapitalization) VALUES " +
                "(0,'BTC', 'Bitcoin', 7544.64, -0.09, 13635900000)," +
                "(0,'ETH', 'Etherium', 754, 0.29, 13635900);");

//        db.execSQL("INSERT INTO favoriteCrypto (id, ticker, name, price, changes, marketCapitalization) VALUES (2,'ETH', 'Etherium', 750.64, 0.50, 13635900000);");
    }

//    private void insertFavoriteForex(SQLiteDatabase db) {
//    }


    // methode wordt uitgevoerd als database geupgrade wordt
    // hierin de vorige tabellen wegdoen en opnieuw creëren
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS favoriteCrypto");
        // Create tables again
        onCreate(db);
    }

    //-------------------------------------------------------------------------------------------------
    //  CRUD Operations
    //-------------------------------------------------------------------------------------------------

    // insert-methode met ContentValues
    public long insertCrypto(Crypto crypto) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ticker", crypto.getTicker());
        values.put("name", crypto.getName());
        values.put("price", crypto.getPrice());
        values.put("changes", crypto.getChanges());
        values.put("marketCapitalization", crypto.getMarketCapitalization());

        long id = db.insert("favoriteCrypto", null, values);

        db.close();
        return id;
    }

    // update-methode met ContentValues
//    public boolean updatePresident(President president) {
    //      SQLiteDatabase db = this.getWritableDatabase();
//
    //      ContentValues values = new ContentValues();
    //    values.put("name", president.getName());
    //  values.put("term", president.getTerm());
    //values.put("partyId", president.getPartyId());
//
//        int numrows = db.update(
    //              "president",
    //            values,
    //          "id = ?",
    //
//        db.close();
    //      return numrows > 0;
    //}

    // delete-methode
//    public boolean deletePresident(long id) {
    //      SQLiteDatabase db = this.getWritableDatabase();
//
    //      int numrows = db.delete(
    //            "president",
    //          "id = ?",
    //        new String[] { String.valueOf(id) });
//
    //      db.close();
    //    return numrows > 0;
    //   }
//
    // query-methode
    public Crypto getCryptoByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                "favoriteCrypto",      // tabelnaam
                new String[]{"id", "ticker", "name", "price", "changes", "marketCapitalization"}, // kolommen
                "name = ?",  // selectie
                new String[]{name}, // selectieparameters
                null,           // groupby
                null,           // having
                null,           // sorting
                null);          // ??

        Crypto crypto = new Crypto();

        if (cursor.moveToFirst()) {
            crypto = new Crypto(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getDouble(3), cursor.getDouble(4), cursor.getInt(5));
        }
        cursor.close();
        db.close();
        return crypto;
    }

    //
    // rawQuery-methode
    public List<Crypto> getCryptos() {
        List<Crypto> lijst = new ArrayList<Crypto>();

        String selectQuery = "SELECT  * FROM favoriteCrypto ORDER BY name";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Crypto crypto = new Crypto(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getDouble(3), cursor.getDouble(4), cursor.getInt(5));
                lijst.add(crypto);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }

    // rawQuery-methode
    //   public List<Party> getParties() {
    //     List<Party> lijst = new ArrayList<Party>();

    //   String selectQuery = "SELECT  * FROM party ORDER BY name";

    // SQLiteDatabase db = this.getReadableDatabase();
    //    Cursor cursor = db.rawQuery(selectQuery, null);

    //  if (cursor.moveToFirst()) {
    //    do {
    //      Party party = new Party(cursor.getLong(0), cursor.getString(1));
    //    lijst.add(party);
    //         } while (cursor.moveToNext());
    //   }
//
    //      cursor.close();
    //    db.close();
    //  return lijst;
    //  }

    // rawQuery-methode
    //  public int getCountPresidents() {
    //      String selectQuery = "SELECT  * FROM president";

    //    SQLiteDatabase db = this.getReadableDatabase();
    //  Cursor cursor = db.rawQuery(selectQuery, null);
    //     int aantal = cursor.getCount();

    //   cursor.close();
    // db.close();
    //    return aantal;
    // }

}
