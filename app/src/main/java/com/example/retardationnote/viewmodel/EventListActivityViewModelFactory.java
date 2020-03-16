package com.example.retardationnote.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class EventListActivityViewModelFactory implements ViewModelProvider.Factory {

    private Application application;
    private String chosenPersonNickname;

    public EventListActivityViewModelFactory(Application application, String chosenPersonNickname) {
        this.application = application;
        this.chosenPersonNickname = chosenPersonNickname;
    }

    @Override
    @NonNull
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new EventListActivityViewModel(application, chosenPersonNickname);
    }
}
