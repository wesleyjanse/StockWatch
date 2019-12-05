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
                "volAvg INTEGER," +
                "mktCap REAL," +
                "lastDiv REAL," +
                "range TEXT," +
                "changes REAL," +
                "changesPercentage TEXT," +
                "exchange TEXT," +
                "industry TEXT," +
                "website TEXT," +
                "description TEXT," +
                "ceo TEXT," +
                "sector TEXT," +
                "image TEXT,"+
                "favoriet INTEGER)";
        db.execSQL(CREATE_TABLE_FAVORITECOMPANY);

        String CREATE_TABLE_FAVORITECRYPTO = "CREATE TABLE favoriteCrypto(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ticker TEXT," +
                "name TEXT," +
                "price REAL," +
                "changes REAL," +
                "marketCapitalization REAL,"+
                "favoriet INTEGER)";
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
                "date TEXT,"+
                "favoriet INTEGER)";
        db.execSQL(CREATE_TABLE_FAVORITEFOREX);
    }

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


    public long insertForex(Forex forex) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ticker", forex.getTicker());
        values.put("bid", forex.getBid());
        values.put("ask", forex.getAsk());
        values.put("open", forex.getOpen());
        values.put("low", forex.getLow());
        values.put("high", forex.getHigh());
        values.put("changes", forex.getChanges());
        values.put("date", forex.getDate());
        values.put("favoriet", 1);
        long id = db.insert("favoriteForex", null, values);

        db.close();
        return id;
    }

    // insert-methode met ContentValues
    public long insertCrypto(Crypto crypto) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ticker", crypto.getTicker());
        values.put("name", crypto.getName());
        values.put("price", crypto.getPrice());
        values.put("changes", crypto.getChanges());
        values.put("marketCapitalization", crypto.getMarketCapitalization());
        values.put("favoriet", 1);
        long id = db.insert("favoriteCrypto", null, values);

        db.close();
        return id;
    }

    public long insertCompany(Company company) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("symbol", company.getSymbol());
        values.put("name", company.getName());
        values.put("price", company.getPrice());
        values.put("beta", company.getBeta());
        values.put("volAvg", company.getVolAvg());
        values.put("mktCap", company.getMktCap());
        values.put("lastDiv", company.getLastDiv());
        values.put("range", company.getRange());
        values.put("changes", company.getChanges());
        values.put("changesPercentage", company.getChangesPercentage());
        values.put("exchange", company.getExchange());
        values.put("industry", company.getIndustry());
        values.put("website", company.getWebsite());
        values.put("description", company.getDescription());
        values.put("ceo", company.getCeo());
        values.put("sector", company.getSector());
        values.put("image", company.getImage());
        values.put("favoriet", 1);
        long id = db.insert("favoriteCompany", null, values);

        db.close();
        return id;
    }
    // insert-methode met ContentValues
    public long insertMyCrypto(Crypto crypto) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ticker", crypto.getTicker());
        values.put("name", crypto.getName());
        values.put("price", crypto.getPrice());
        values.put("changes", crypto.getChanges());
        values.put("marketCapitalization", crypto.getMarketCapitalization());
        values.put("favoriet", 0);
        long id = db.insert("favoriteCrypto", null, values);

        db.close();
        return id;
    }

    public long insertMyCompany(Company company) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("symbol", company.getSymbol());
        values.put("name", company.getName());
        values.put("price", company.getPrice());
        values.put("beta", company.getBeta());
        values.put("volAvg", company.getVolAvg());
        values.put("mktCap", company.getMktCap());
        values.put("lastDiv", company.getLastDiv());
        values.put("range", company.getRange());
        values.put("changes", company.getChanges());
        values.put("changesPercentage", company.getChangesPercentage());
        values.put("exchange", company.getExchange());
        values.put("industry", company.getIndustry());
        values.put("website", company.getWebsite());
        values.put("description", company.getDescription());
        values.put("ceo", company.getCeo());
        values.put("sector", company.getSector());
        values.put("image", company.getImage());
        values.put("favoriet", 0);
        long id = db.insert("favoriteCompany", null, values);

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
                new String[]{"id", "ticker", "name", "price", "changes", "marketCapitalization,favoriet"}, // kolommen
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

        String selectQuery = "SELECT  * FROM favoriteCrypto  WHERE favoriet = 1 ORDER BY name";

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

    public List<Company> getCompanies() {
        List<Company> lijst = new ArrayList<Company>();

        String selectQuery = "SELECT  * FROM favoriteCompany  WHERE favoriet = 1 ORDER BY symbol";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Company company = new Company(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getDouble(3), cursor.getDouble(4), cursor.getInt(5),
                        cursor.getDouble(6), cursor.getDouble(7), cursor.getString(8), cursor.getDouble(9),
                        cursor.getString(10), cursor.getString(11),cursor.getString(12),cursor.getString(13),cursor.getString(14),cursor.getString(14),cursor.getString(16), cursor.getString(17));
                lijst.add(company);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }

    public List<Forex> getForex() {
        List<Forex> lijst = new ArrayList<Forex>();

        String selectQuery = "SELECT  * FROM favoriteForex  WHERE favoriet = 1 ORDER BY ticker";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Forex forex = new Forex(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2), cursor.getDouble(3),
                        cursor.getDouble(4), cursor.getDouble(5), cursor.getDouble(6), cursor.getDouble(7), cursor.getString(8));
                lijst.add(forex);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }




    public List<Crypto> getMyCryptos() {
        List<Crypto> lijst = new ArrayList<Crypto>();

        String selectQuery = "SELECT  * FROM favoriteCrypto  WHERE favoriet = 0 ORDER BY name ";

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

    public List<Company> getMyCompanies() {
        List<Company> lijst = new ArrayList<Company>();

        String selectQuery = "SELECT  * FROM favoriteCompany  WHERE favoriet = 0 ORDER BY symbol";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Company company = new Company(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getDouble(3), cursor.getDouble(4), cursor.getInt(5),
                        cursor.getDouble(6), cursor.getDouble(7), cursor.getString(8), cursor.getDouble(9),
                        cursor.getString(10), cursor.getString(11),cursor.getString(12),cursor.getString(13),cursor.getString(14),cursor.getString(14),cursor.getString(16), cursor.getString(17));
                lijst.add(company);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }

    public List<Forex> getMyForex() {
        List<Forex> lijst = new ArrayList<Forex>();

        String selectQuery = "SELECT  * FROM favoriteForex  WHERE favoriet = 0 ORDER BY ticker";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Forex forex = new Forex(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2), cursor.getDouble(3),
                        cursor.getDouble(4), cursor.getDouble(5), cursor.getDouble(6), cursor.getDouble(7), cursor.getString(8));
                lijst.add(forex);
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