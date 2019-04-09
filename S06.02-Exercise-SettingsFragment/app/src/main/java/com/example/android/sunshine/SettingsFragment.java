package com.example.android.sunshine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

import com.example.android.sunshine.R;

public class SettingsFragment extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.pref_general);

        PreferenceScreen preferenceScreen = getPreferenceScreen();
        SharedPreferences sharedPreferences = preferenceScreen.getSharedPreferences();
        int prefCount = preferenceScreen.getPreferenceCount();

        for(int i=0; i<prefCount; i++){
            Preference pref = preferenceScreen.getPreference(i);
            if(!(pref instanceof CheckBoxPreference)){
                String value = sharedPreferences.getString(pref.getKey(), "");
                setPreferenceSummary(pref, value);
            }
        }
    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
//    }

    public void setPreferenceSummary(Preference preference, Object value){
        if(preference instanceof EditTextPreference){
            EditTextPreference editTextPreference = (EditTextPreference)preference;
            if(value instanceof String){
                String location = (String)value;
                editTextPreference.setSummary(location);
            }
        }
        else if(preference instanceof ListPreference){
            ListPreference listPreference = (ListPreference)preference;
            if(value instanceof String){
                String temperatureUnits = (String)value;
                int index = listPreference.findIndexOfValue(temperatureUnits);
                listPreference.setSummary(listPreference.getEntries()[index]);
            }
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        if(preference != null){
            if(!(preference instanceof CheckBoxPreference)){
                String value = sharedPreferences.getString(key, "");
                setPreferenceSummary(preference, value);
            }
        }
    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
//    }

    @Override
    public void onStart() {
        super.onStart();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
