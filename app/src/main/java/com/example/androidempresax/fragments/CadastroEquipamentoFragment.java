package com.example.androidempresax.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidempresax.R;


public class CadastroEquipamentoFragment extends Fragment {


    public static CadastroEquipamentoFragment newInstance() {
        CadastroEquipamentoFragment fragment = new CadastroEquipamentoFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_cadastro_equipamento, container, false);
    }
}