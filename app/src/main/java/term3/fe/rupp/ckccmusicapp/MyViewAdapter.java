package term3.fe.rupp.ckccmusicapp;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by malypoeur on 10/22/16.
 */

public class MyViewAdapter extends RecyclerView.Adapter<MyViewAdapter.ViewHolder> {

    private ArrayList<String> data;

    private Cursor cursor;

    private static SongListener songListener;

    public MyViewAdapter(ArrayList<String> data) {
        this.data = data;
    }

    public MyViewAdapter(Cursor cursor){
        this.cursor = cursor;
    }

    public void setSongListener(SongListener songListener_){
        songListener = songListener_;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtSongTitle;

        public TextView getTxtSongTitle() {
            return txtSongTitle;
        }

        public ViewHolder(View v) {
            super(v);
            txtSongTitle = (TextView) v.findViewById(R.id.txtSongTitle);
            txtSongTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    songListener.onSongTitleClickListener(txtSongTitle.getText().toString());
                }
            });
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_item_row, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int pos = position + 1;
//        holder.getTxtSongTitle().setText(pos + " . " + data.get(position));
        cursor.moveToPosition(position);
        holder.getTxtSongTitle().setText(pos + " . " + cursor.getString(0));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
//        return data.size();
    }
}
