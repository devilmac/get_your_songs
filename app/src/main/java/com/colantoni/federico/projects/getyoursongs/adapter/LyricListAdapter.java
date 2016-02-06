package com.colantoni.federico.projects.getyoursongs.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.colantoni.federico.projects.getyoursongs.R;
import com.colantoni.federico.projects.networkmodule.model.SearchLyricResult;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LyricListAdapter extends RecyclerView.Adapter<LyricListAdapter.LyricItemViewHolder> {

    private List<SearchLyricResult> mItemLists;

    private Context context;

    public LyricListAdapter(List<SearchLyricResult> mItemLists) {

        this.mItemLists = mItemLists;
    }

    @Override

    public LyricItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lyric_list_item_layout, parent, false);

        context = parent.getContext();

        return new LyricItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LyricItemViewHolder holder, int position) {

        final SearchLyricResult itemList = mItemLists.get(position);

        holder.artist.setText(itemList.getArtist());
        holder.lyricTitle.setText(itemList.getSong());
        holder.lyricCover.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {

        return mItemLists.size();
    }

    public static class LyricItemViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.lyricCover)
        ImageView lyricCover;

        @Bind(R.id.artist)
        TextView artist;

        @Bind(R.id.lyricTitle)
        TextView lyricTitle;

        public LyricItemViewHolder(View itemView) {

            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
