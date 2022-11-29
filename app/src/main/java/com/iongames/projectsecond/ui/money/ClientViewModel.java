package com.iongames.projectsecond.ui.money;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ClientViewModel extends ViewModel {

        private final MutableLiveData<String> mText;

        public ClientViewModel() {
            mText = new MutableLiveData<>();
            mText.setValue("This is slideshow fragment");
        }

        public LiveData<String> getText() {
            return mText;
        }

}
