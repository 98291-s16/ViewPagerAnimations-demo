package edu.cmu.stuco.android.viewpageranimations;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends FragmentActivity {

    // The number of pages
    private static final int NUM_PAGES = 5;

    // The pager widget handles animation
    private ViewPager mPager;

    // Provides the pages to the view pager.
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setBackgroundColor(Color.BLACK);
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        // default page transformer is sliding between pages (comment out all code below to see it)

        // performs a scaling effect from and to 50%
        mPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                final float normalizedposition = Math.abs(Math.abs(position) - 1);
                page.setScaleX(normalizedposition / 2 + 0.5f);
                page.setScaleY(normalizedposition / 2 + 0.5f);
            }
        });


        // rotates the pages around their Z axis by 30 degrees
        /*
        mPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                page.setRotationY(position * -30);
            }
        });
        */

        /* Zoom out page transformer
            shrinks and fades pages when scrolling between adjacent pages. As a page gets closer to
            the center, it grows back to its normal size and fades in.
         */
        //mPager.setPageTransformer(true, new ZoomOutPageTransformer());

        /* Depth page transformer
            uses the default slide animation for sliding pages to the left,
            while using a "depth" animation for sliding pages to the right.
            The "depth" animation just fades the page out, and scales it down linearly.
         */
        //mPager.setPageTransformer(true, new DepthPageTransformer());

    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    // A simple pager adapter that represents 5 PageFragments in sequence
    private class PagerAdapter extends FragmentStatePagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new PageFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
