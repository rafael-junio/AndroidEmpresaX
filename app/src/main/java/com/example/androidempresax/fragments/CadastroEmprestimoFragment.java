package com.example.androidempresax.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.androidempresax.CadastroActivity;
import com.example.androidempresax.R;
import com.example.androidempresax.db.DBHelperEmprestimo;
import com.example.androidempresax.db.DBHelperEquipamento;
import com.example.androidempresax.db.Equipamento;

import java.io.ObjectInputStream;
import java.util.ArrayList;


public class CadastroEmprestimoFragment extends Fragment {

    private static String selectedItem;
    public ListView listEquipamentos;
    View fragmentoEmprestimo;

    ArrayList<Equipamento> arrayListEquipamento;
    ArrayAdapter<Equipamento> equipamentoArrayAdapter;

    public CadastroEmprestimoFragment() {
        // Required empty public constructor
    }


    public static CadastroEmprestimoFragment newInstance() {
        CadastroEmprestimoFragment fragment = new CadastroEmprestimoFragment();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        listEquipamentos = getActivity().findViewById(R.id.listEquipamentos);
//        registerForContextMenu(listEquipamentos);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentoEmprestimo = inflater.inflate(R.layout.fragment_cadastro_emprestimo, container, false);

        return fragmentoEmprestimo;
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
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                selectedItem = listEquipamentos.getItemAtPosition(i).toString();

            }
        });

    }

    public static String getSelectedItem() {
        return selectedItem;
    }


}