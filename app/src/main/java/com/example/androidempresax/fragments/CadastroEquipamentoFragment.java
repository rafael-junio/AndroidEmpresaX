package com.example.androidempresax.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.androidempresax.CadastroActivity;
import com.example.androidempresax.R;
import com.example.androidempresax.db.Equipamento;

import java.util.ArrayList;


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

    public void onResume(){
        super.onResume();
    }

}