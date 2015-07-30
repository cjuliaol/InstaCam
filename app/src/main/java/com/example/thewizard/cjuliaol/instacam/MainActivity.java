package com.example.thewizard.cjuliaol.instacam;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.File;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class MainActivity extends AppCompatActivity implements MaterialTabListener{

    private static final String TAG = "MainActivityLog";
    private static final int NEW_PHOTO_REQUEST = 20;
    private FeedFragment mFeedFragment;
    private ProfileFragment mProfileFragment;
    private MaterialTabHost mTabbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTabbar = (MaterialTabHost) findViewById(R.id.tab_bar);
        mTabbar.addTab(mTabbar.newTab().setIcon(ContextCompat.getDrawable(this, R.drawable.ic_home)).setTabListener(this));
        mTabbar.addTab(mTabbar.newTab().setIcon(ContextCompat.getDrawable(this, R.drawable.ic_profile)).setTabListener(this));

        ImageButton camera_fab = (ImageButton) findViewById(R.id.camera_fab);
        camera_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,NewPhotoActivity.class);
                startActivityForResult(intent,NEW_PHOTO_REQUEST);

            }
        });

         mFeedFragment = (FeedFragment) getFragmentManager().findFragmentById(R.id.feed_container);
        if (mFeedFragment == null) {
            mFeedFragment = new FeedFragment();
            getFragmentManager().beginTransaction()
                    .add(R.id.feed_container, mFeedFragment)
                    .commit();
        }
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        int position = materialTab.getPosition();
        mTabbar.setSelectedNavigationItem(position);
        Fragment fragment = null;


        switch (position) {
            case 0:
                if (mFeedFragment == null) {
                    mFeedFragment = new FeedFragment();
                    Log.d(TAG,"mFeedFragment is null");
                }
               fragment = mFeedFragment;
                break;
            case 1:
                if (mProfileFragment == null) {
                    mProfileFragment = new ProfileFragment();
                }
                fragment = mProfileFragment;
                break;
        }
        getFragmentManager().beginTransaction()
                .replace(R.id.feed_container,fragment)
                .commit();

    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == NEW_PHOTO_REQUEST) {
            if( resultCode == RESULT_OK) {
               Photo photo = (Photo) data.getSerializableExtra(NewPhotoActivity.PHOTO_EXTRA);
                 mFeedFragment.addPhoto(photo);
            }
        }
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
