package it.cdpaf.computerfamily;

import it.cdpaf.computerfamily.fragment.AdvancedSearch;
import it.cdpaf.computerfamily.fragment.FantaSearch;
import it.cdpaf.computerfamily.fragment.Basket;

import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			switch (position) {
				case 0 :
					Fragment fragmentFS = new FantaSearch();
					Bundle argsFS = new Bundle();
					argsFS.putInt(FantaSearch.ARG_SECTION_NUMBER, position + 1);
					fragmentFS.setArguments(argsFS);
					return fragmentFS;
				case 1 :
					Fragment fragmentAS = new AdvancedSearch();
					Bundle argsAS = new Bundle();
					argsAS.putInt(AdvancedSearch.ARG_SECTION_NUMBER, position + 1);
					fragmentAS.setArguments(argsAS);
					return fragmentAS;
				case 2 :
					Fragment fragmentB = new Basket();
					Bundle argsB = new Bundle();
					argsB.putInt(Basket.ARG_SECTION_NUMBER, position + 1);
					fragmentB.setArguments(argsB);
					return fragmentB;
				default: 
					return null;
			}
			
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
				case 0 :
					return getString(R.string.title_section1).toUpperCase(l);
				case 1 :
					return getString(R.string.title_section2).toUpperCase(l);
				case 2 :
					return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	

}
