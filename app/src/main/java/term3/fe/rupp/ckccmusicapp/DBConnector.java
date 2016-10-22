package term3.fe.rupp.ckccmusicapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;


/**
 * Created by malypoeur on 10/22/16.
 */

public class DBConnector extends SQLiteAssetHelper {

    private static final String MY_DB_NAME = "music.sqlite";

    // singleton pattern

    private static DBConnector instance = null;

    public static DBConnector getInstance(Context context){
        if(instance == null){
            instance = new DBConnector(context);
        }
        return instance;
    }

    // change from public to private for singleton pattern purpose
    private DBConnector(Context context){
        super(context,MY_DB_NAME,null,1);
    }

    public ArrayList<String> getDBData(){
        ArrayList<String> list = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM SONGS", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            list.add(cursor.getString(0));
            Log.d("DB: ", cursor.getString(0));
            cursor.moveToNext();
        }
        return list;
    }

}
