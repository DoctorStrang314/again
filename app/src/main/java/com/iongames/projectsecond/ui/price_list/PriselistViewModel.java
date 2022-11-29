package com.iongames.projectsecond.ui.price_list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PriselistViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PriselistViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
