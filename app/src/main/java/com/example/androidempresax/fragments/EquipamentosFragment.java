package com.example.androidempresax.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.androidempresax.CadastroActivity;
import com.example.androidempresax.R;
import com.example.androidempresax.db.DBHelperEquipamento;
import com.example.androidempresax.db.Equipamento;

import java.io.Serializable;
import java.util.ArrayList;

public class EquipamentosFragment extends Fragment {

    private static String selectedItem;
    public ListView listEquipamentos;
    View fragmentoEmprestimo;

    ArrayList<Equipamento> arrayListEquipamento;
    ArrayAdapter<Equipamento> equipamentoArrayAdapter;

    public EquipamentosFragment() {
        // Required empty public constructor
    }

    public static EquipamentosFragment newInstance() {
        EquipamentosFragment fragment = new EquipamentosFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_equipamentos, container, false);
    }

    public void onResume(){
        super.onResume();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        listEquipamentos = (ListView) getView().findViewById(R.id.listEquipamentos);
        registerForContextMenu(listEquipamentos);
        DBHelperEquipamento helperEquipamento = new DBHelperEquipamento(getActivity());
        arrayListEquipamento = helperEquipamento.selectAllEquipamentos();
        helperEquipamento.close();
        if (listEquipamentos != null) {
            equipamentoArrayAdapter = new ArrayAdapter<Equipamento>(getActivity(), android.R.layout.simple_list_item_1, arrayListEquipamento);
            listEquipamentos.setAdapter(equipamentoArrayAdapter);
        }

        listEquipamentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Equipamento equipamentoEnviado = (Equipamento) equipamentoArrayAdapter.getItem(position);
                Intent it = new Intent(getActivity(), CadastroActivity.class);
                it.putExtra("chave_equipamento", equipamentoEnviado);
                startActivity(it);
            }
        });

    }

}