package com.example.lab_5;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class LocalOption {

    // Настройки приложения
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor optionEditor;

    //  Конструктор
    public LocalOption(PhotoGallery context) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.optionEditor = sharedPreferences.edit();
    }

    // Передает flickr_api_key, доступный в хранилище настроек
    public String getFlickr_api_key() {
        return sharedPreferences.getString("flickr_api_key", "");
    }

    // Задает отдельные опции в хранилище
    public void setOption(String key, String value) {
        optionEditor.putString(key, value);
        optionEditor.commit();
    }
}
