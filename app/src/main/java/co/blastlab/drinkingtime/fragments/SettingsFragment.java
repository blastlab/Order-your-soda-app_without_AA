package co.blastlab.drinkingtime.fragments;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import co.blastlab.drinkingtime.R;

public class SettingsFragment extends PreferenceFragmentCompat {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Load the preferences from an XML resource
		addPreferencesFromResource(R.xml.app_preferences);
	}


	@Override
	public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
		// this is done using AndroidAnnotations
		// but method needs to be implemented
	}
}
