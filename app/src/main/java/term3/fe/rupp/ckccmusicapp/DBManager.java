package term3.fe.rupp.ckccmusicapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

/**
 * Created by malypoeur on 10/30/16.
 */

public class DBManager extends AsyncTask<Void, Void, Cursor> {

    private Context context;

    public DBManager(Context context){
        this.context = context;
    }

    @Override
    protected Cursor doInBackground(Void... voids) {
        return DBConnector.getInstance(context).getCursorDBData();
    }
}
