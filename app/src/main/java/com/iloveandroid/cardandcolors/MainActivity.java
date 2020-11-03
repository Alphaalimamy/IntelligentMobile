package com.iloveandroid.cardandcolors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SportAdapter mAdapter;
    ArrayList<Sport>mSportsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        mRecyclerView = findViewById(R.id.recyclerview);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mSportsData = new ArrayList<>();

        mAdapter = new SportAdapter(mSportsData, this);
        mRecyclerView.setAdapter(mAdapter);
        initialize();

    }

    private void initialize(){
        String[] news = getResources().getStringArray(R.array.sports_titles);
        String[] sportList = getResources().getStringArray(R.array.news);
        String[] sportInfo = getResources().getStringArray(R.array.sports_info);
        @SuppressLint("Recycle") TypedArray sportsImageResources = getResources().obtainTypedArray(R.array.sports_images);

        mSportsData.clear();
        for (int i = 0; i < sportList.length; i++) {
            mSportsData.add(new Sport(news[i],sportList[i], sportInfo[i],
                    sportsImageResources.getResourceId(i,0)));
        }
        sportsImageResources.recycle();
        mAdapter.notifyDataSetChanged();
    }
}