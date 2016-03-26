package fettle.iiitd.com.fettle.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.viewpagerindicator.CirclePageIndicator;

import fettle.iiitd.com.fettle.Fragments.CardTrackerFragment;
import fettle.iiitd.com.fettle.R;

public class LandingActivity extends AppCompatActivity {

    private static final int NUM_PAGES1 = 2;
    private ViewPager mPager1;
    private PagerAdapter mPagerAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        mPager1 = (ViewPager) findViewById(R.id.pager1);
        mPagerAdapter1 = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager1.setAdapter(mPagerAdapter1);

        CirclePageIndicator indicator1 = (CirclePageIndicator) findViewById(R.id.indicator1);
        indicator1.setViewPager(mPager1);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new CardTrackerFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES1;
        }
    }
}
