package com.iloveandroid.cardandcolors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.MySportViewHolder> {

    private ArrayList<Sport> mSportsData;
    private Context mContext;

    public SportAdapter(ArrayList<Sport> mSportsData, Context mContext) {
        this.mSportsData = mSportsData;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public SportAdapter.MySportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_list, parent,false);
        return new MySportViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SportAdapter.MySportViewHolder holder, int position) {
        // Get current sport.
        Sport currentSport = mSportsData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentSport);
    }

    @Override
    public int getItemCount() {
        return mSportsData.size();
    }


    public  class MySportViewHolder extends RecyclerView.ViewHolder {
        TextView mTextTitle, mTextInfo, mNews;
        private ImageView mSportsImage;

        public MySportViewHolder(@NonNull View itemView) {
            super( itemView );
            mNews = itemView.findViewById(R.id.title);
            mTextInfo = itemView.findViewById(R.id.subTitle);
            mTextTitle = itemView.findViewById(R.id.newsTitle);
            mSportsImage = itemView.findViewById(R.id.sportImage);
        }

        void bindTo(Sport currentSport){
            // Populate the textviews with data.
            mNews.setText(currentSport.getNews());
            mTextTitle.setText(currentSport.getTitle());
            mTextInfo.setText(currentSport.getInfo());
            Glide.with(mContext).load(currentSport.getImageResource()).into(mSportsImage);
        }
    }




}
