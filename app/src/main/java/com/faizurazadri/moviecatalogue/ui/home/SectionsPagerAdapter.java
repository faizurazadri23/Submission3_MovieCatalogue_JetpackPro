package com.faizurazadri.moviecatalogue.ui.home;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.faizurazadri.moviecatalogue.R;
import com.faizurazadri.moviecatalogue.ui.favorite.FavoriteFragment;
import com.faizurazadri.moviecatalogue.ui.movie.MovieFragment;
import com.faizurazadri.moviecatalogue.ui.tvshow.TvShowFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.movie, R.string.tvshow, R.string.favorite};
    private final Context mcontext;

    SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mcontext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MovieFragment();

            case 1:
                return new TvShowFragment();

            case 2:

                return new FavoriteFragment();

            default:
                return new Fragment();
        }
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mcontext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
