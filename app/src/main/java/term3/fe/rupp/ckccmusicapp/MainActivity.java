package term3.fe.rupp.ckccmusicapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
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

        mAdapter = new MyViewAdapter(DBConnector.getInstance(getApplicationContext()).getDBData());
        mRecyclerView.setAdapter(mAdapter);

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