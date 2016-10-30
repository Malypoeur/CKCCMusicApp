package term3.fe.rupp.ckccmusicapp;

import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDetailLayout();
        makeInvisibleDetailLayout();

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
                        setDetailLayout(song_title);
                        makeVisibleDetailLayout();
                    }
                });
                mRecyclerView.setAdapter(mAdapter);
            }
        };
        dbManager.execute(null,null,null);

    }

    private void makeInvisibleDetailLayout(){
        RelativeLayout relativeLayout = (RelativeLayout) this.findViewById(R.id.layoutSongDetail);
        relativeLayout.setVisibility(View.GONE);
        Log.d("MainActivity: ", relativeLayout.getVisibility()+"");
    }

    private void makeVisibleDetailLayout(){
        RelativeLayout relativeLayout = (RelativeLayout) this.findViewById(R.id.layoutSongDetail);
        relativeLayout.setVisibility(View.VISIBLE);
    }

    private void initDetailLayout(){
        TextView txtClose = (TextView) findViewById(R.id.txtClose);
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeInvisibleDetailLayout();
            }
        });
    }

    private void setDetailLayout(String songTitle){
        TextView txtSongTitle = (TextView) findViewById(R.id.txtSongDetail);
        txtSongTitle.setText(songTitle);
    }
}