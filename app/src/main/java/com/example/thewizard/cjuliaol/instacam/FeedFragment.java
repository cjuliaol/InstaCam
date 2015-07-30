package com.example.thewizard.cjuliaol.instacam;


import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {


    private static final String TAG = "FeedFragment";
    private List<Photo> mPhotos;
    private FeedAdapter mAdapter;

    public FeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.feed_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mPhotos = new ArrayList<Photo>();
        mAdapter = new FeedAdapter(getActivity(), mPhotos);
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    public void addPhoto(Photo photo) {

        mPhotos.add(0, photo);
        mAdapter.notifyDataSetChanged();
    }
}
