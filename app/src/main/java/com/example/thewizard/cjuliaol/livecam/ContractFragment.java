package com.example.thewizard.cjuliaol.livecam;

import android.app.Activity;
import android.app.Fragment;

/**
 * Created by cjuliaol on 01/08/2015.
 */
// Using Generics
public class ContractFragment<T> extends Fragment {

    private T mContract;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mContract = (T) getActivity();
        } catch (ClassCastException e) {
            throw new IllegalStateException("Activity does not implement interface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContract = null;
    }

    public T getContrat() {
        return mContract;
    }
}

