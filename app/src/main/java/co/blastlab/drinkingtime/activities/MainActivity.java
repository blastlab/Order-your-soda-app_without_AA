package co.blastlab.drinkingtime.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import co.blastlab.drinkingtime.R;
import co.blastlab.drinkingtime.fragments.HistoryFragment;
import co.blastlab.drinkingtime.fragments.OrderFragment;
import co.blastlab.drinkingtime.fragments.SettingsFragment;
import co.blastlab.drinkingtime.widget.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

	private static final int POS_ORDER = 0;
	private static final int POS_STATS = 1;
	private static final int POS_SETTINGS = 2;

	private boolean doubleBackToExitPressedOnce;

	TabLayout tabLayout;

	ViewPager viewPager;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_main);

		tabLayout = (TabLayout) this.findViewById(R.id.tabs);
		viewPager = (ViewPager) this.findViewById(R.id.viewpager);

		setup();
	}

	protected void setup() {
		ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
		adapter.addFrag(new OrderFragment(), getString(R.string.tab_order));
		adapter.addFrag(new HistoryFragment(), getString(R.string.tab_history));
		adapter.addFrag(new SettingsFragment(), getString(R.string.tab_settings));

		viewPager.setAdapter(adapter);

		tabLayout.setupWithViewPager(viewPager);

		for (int i = 0; i < tabLayout.getTabCount(); i++) {
			switch (i) {
				case POS_ORDER:
					tabLayout.getTabAt(i).setIcon(R.mipmap.cup);
					break;
				case POS_STATS:
					tabLayout.getTabAt(i).setIcon(R.mipmap.history);
					break;
				case POS_SETTINGS:
					tabLayout.getTabAt(i).setIcon(R.mipmap.settings);
					break;
			}
		}
	}

	@Override
	public void onBackPressed() {
		if (doubleBackToExitPressedOnce) {
			finish();
			return;
		}

		doubleBackToExitPressedOnce = true;
		Toast.makeText(this, getString(R.string.exit_info), Toast.LENGTH_SHORT).show();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				doubleBackToExitPressedOnce = false;
			}
		}, 2000);
	}
}
