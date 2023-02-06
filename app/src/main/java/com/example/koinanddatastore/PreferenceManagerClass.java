package com.example.koinanddatastore;

import static java.util.Collections.emptySet;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PreferenceManagerClass {

    private final SharedPreferences preferences;

    public PreferenceManagerClass(Context context) {
        preferences = context.getSharedPreferences("AppConstants", Context.MODE_PRIVATE);
    }

    public void putBoolean(String key, Boolean value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public Boolean getBoolean(String key, Boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key, String defValue) {
        return preferences.getString(key, defValue);
    }

    public void putInteger(String key, int value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getInteger(String key, int defValue) {
        return preferences.getInt(key, defValue);
    }

    public void putStringArrayList(String key, ArrayList<String> mStringList) {
        Set<String> set = new HashSet<String>(mStringList);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(key, set);
        editor.apply();
    }

    public ArrayList getStringList(String key){
        return new ArrayList(preferences.getStringSet(key, emptySet()));
    }

    public void clear() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }


}
