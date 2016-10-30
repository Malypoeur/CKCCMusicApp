package term3.fe.rupp.ckccmusicapp;

import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        DBManager dbManager = new DBManager(getApplicationContext()){
            @Override
            protected void onPostExecute(Cursor cursor) {
                super.onPostExecute(cursor);
                mAdapter = new MyViewAdapter(cursor);
                mAdapter.setSongListener(new SongListener() {
                    @Override
                    public void onSongTitleClickListener(String song_title) {
                        Log.d("Receiver: ", song_title);
                    }
                });
                mRecyclerView.setAdapter(mAdapter);
            }
        };
        dbManager.execute(null,null,null);

    }

    private ArrayList<String> getArrayListData(){
        ArrayList<String> songList = new ArrayList<>();
        songList = new ArrayList<String>();
        songList.add("Come Home for Dinner");
        songList.add("Blank Space");
        songList.add("Drag Me Down");
        songList.add("You song");
        songList.add("Come Home for Dinner");
        songList.add("Blank Space");
        songList.add("Drag Me Down");
        songList.add("You song");
        songList.add("Come Home for Dinner");
        songList.add("Blank Space");
        songList.add("Drag Me Down");
        songList.add("You song");
        return songList;
    }
}