package com.example.thewizard.cjuliaol.instacam;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class NewPhotoActivity extends AppCompatActivity {
    private static final String TAG = "NewPhotoActivityLog";
    private Photo mPhoto;
    private static final int CAMERA_REQUEST=10;
    public static final String PHOTO_EXTRA="PHOTO_EXTRA";
    private ImageView mPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_photo);

        mPreview = (ImageView) findViewById(R.id.photo_preview);
        final EditText caption = (EditText) findViewById(R.id.new_photo_caption);
        Button saveButton = (Button) findViewById(R.id.save_new_photo);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhoto.setCaption(caption.getText().toString());
                Intent intent = new Intent();

                Log.d(TAG,"Caption : "+mPhoto.getCaption().toString() );
                intent.putExtra(PHOTO_EXTRA, mPhoto);
                setResult(RESULT_OK, intent);
                finish();
            }
        });


        launchCamera();
    }


    private void launchCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        mPhoto = new Photo();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mPhoto.getFile()));
        startActivityForResult(intent, CAMERA_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAMERA_REQUEST) {
            if( resultCode == RESULT_OK) {
                Picasso.with(this).load(mPhoto.getFile()).into(mPreview);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_photo, menu);
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
