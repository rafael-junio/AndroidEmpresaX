package com.example.androidempresax.ui.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.androidempresax.R;
import com.example.androidempresax.fragments.CadastroEmprestimoFragment;
import com.example.androidempresax.fragments.CadastroEquipamentoFragment;
import com.example.androidempresax.fragments.EmprestimoFragment;
import com.example.androidempresax.fragments.EquipamentosFragment;
import com.example.androidempresax.fragments.ListagemFragment;


public class CadastroSectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.cadastro_tab_text_1, R.string.cadastro_tab_text_2};
    private final Context mContext;

    public CadastroSectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = CadastroEmprestimoFragment.newInstance();
                break;
            case 1:
                fragment = CadastroEquipamentoFragment.newInstance();
                break;
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
        return 2;
    }
}