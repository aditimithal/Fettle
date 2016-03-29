package fettle.iiitd.com.fettle.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.viewpagerindicator.CirclePageIndicator;

import fettle.iiitd.com.fettle.Fragments.CardIntakeFragment;
import fettle.iiitd.com.fettle.Fragments.CardIntakeNutrientFragment;
import fettle.iiitd.com.fettle.Fragments.CardTrackerFragment;
import fettle.iiitd.com.fettle.Fragments.CardTrackerWeekFragment;
import fettle.iiitd.com.fettle.R;

public class LandingActivity extends AppCompatActivity {

    private static final int NUM_PAGES1 = 2;
    private ViewPager mPager1;
    private PagerAdapter mPagerAdapter1;

    private static final int NUM_PAGES2 = 2;
    private ViewPager mPager2;
    private PagerAdapter mPagerAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        mPager1 = (ViewPager) findViewById(R.id.pager1);
        mPagerAdapter1 = new ScreenSlidePagerAdapter1(getSupportFragmentManager());
        mPager1.setAdapter(mPagerAdapter1);

        CirclePageIndicator indicator1 = (CirclePageIndicator) findViewById(R.id.indicator1);
        indicator1.setViewPager(mPager1);

        mPager2 = (ViewPager) findViewById(R.id.pager2);
        mPagerAdapter2 = new ScreenSlidePagerAdapter2(getSupportFragmentManager());
        mPager2.setAdapter(mPagerAdapter2);

        CirclePageIndicator indicator2 = (CirclePageIndicator) findViewById(R.id.indicator2);
        indicator2.setViewPager(mPager2);
    }

    private class ScreenSlidePagerAdapter1 extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter1(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return new CardTrackerFragment();
            else
                return new CardTrackerWeekFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES1;
        }
    }

    private class ScreenSlidePagerAdapter2 extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter2(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return new CardIntakeFragment();
            else
                return new CardIntakeNutrientFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES2;
        }
    }

}
