package com.example.androidempresax.ui.main;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.androidempresax.R;

import com.example.androidempresax.fragments.EmprestimoFragment;
import com.example.androidempresax.fragments.EquipamentosFragment;
import com.example.androidempresax.fragments.ListagemFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        String param1 = "Param1";
        String param2 = "Param2";
        switch (position) {
            case 0:
                fragment = EmprestimoFragment.newInstance();
                break;
            case 1:
                fragment = EquipamentosFragment.newInstance(param1, param2);
                break;
            case 2:
                fragment = ListagemFragment.newInstance(param1, param2);
        }
        assert fragment != null;
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 3;
    }
}