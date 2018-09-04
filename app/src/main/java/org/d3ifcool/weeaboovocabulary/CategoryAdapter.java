package org.d3ifcool.weeaboovocabulary;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by AKU CINTA DIA on 3/13/2018.
 */

/**
 * Control Fragment Item
 */

public class CategoryAdapter extends FragmentPagerAdapter {
    /**
     * Deklarasi Variable
     */

    private Context mContext;

    /**
     * Construct
     */

    public CategoryAdapter(Context c,FragmentManager fm) {
        super(fm);
        mContext = c;
    }

    /**
     * GET and SET
     */

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MainFragment();
            default:
                return new TimelineFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.category_schedule);
            default:
                return mContext.getString(R.string.category_timeline);
        }
    }
}
