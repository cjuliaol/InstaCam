package com.example.thewizard.cjuliaol.livecam;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewPhotoFragment extends ContractFragment<NewPhotoFragment.Contract> {

    private static final String TAG = "NewPhotoFragmentLog";
    private static final String PHOTO_STATE_EXTRA = "PHOTO_STATE";
    private Photo mPhoto;
    private static final int CAMERA_REQUEST = 10;
    public static final String PHOTO_EXTRA = "PHOTO_EXTRA";
    private ImageView mPreview;


    public NewPhotoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_photo, container, false);
       setRetainInstance(true);

        mPreview = (ImageView) view.findViewById(R.id.photo_preview);
        final EditText caption = (EditText) view.findViewById(R.id.new_photo_caption);
        Button saveButton = (Button) view.findViewById(R.id.save_new_photo);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhoto.setCaption(caption.getText().toString());
                getContrat().finishedPhoto(mPhoto);

            }
        });

      if (mPhoto == null) {
           getContrat().launchCamera();
        } else {
            loadThumbnail(mPhoto);
        }
        return view;
    }

    public void updatePhoto(Photo photo) {
        mPhoto = photo;
        loadThumbnail(mPhoto);
    }
    private void loadThumbnail(Photo photo) {
        Picasso.with(getActivity()).load(photo.getFile()).into(mPreview);
    }

    public interface Contract {
        public void launchCamera();

        public void finishedPhoto(Photo photo);
    }

}
