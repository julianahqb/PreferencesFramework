package com.example.preferencesframework;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;

public class ConfigFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    private SwitchPreference prefMapas;
    private ListPreference prefMedidas;
    private CheckBoxPreference prefSatelite;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.config);

        prefMapas = (SwitchPreference) findPreference("mapas");
        prefMedidas = (ListPreference) findPreference("medida");
        prefSatelite = (CheckBoxPreference) findPreference("satelite");

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        prefs.registerOnSharedPreferenceChangeListener(this);

        boolean defValue = prefs.getBoolean("mapas", false);

        setEnabled(defValue);

    }

    private void setEnabled(boolean enabled){
        prefMedidas.setEnabled(enabled);
        prefSatelite.setEnabled(enabled);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

        if (s.equals(prefMapas.getKey())){
            boolean defValue = sharedPreferences.getBoolean(s, false);
            setEnabled(defValue);
        }

    }


}
