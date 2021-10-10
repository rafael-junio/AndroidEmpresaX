package com.example.androidempresax.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.androidempresax.R;

import com.example.androidempresax.db.DBHelperEmprestimo;
import com.example.androidempresax.db.DBHelperEquipamento;
import com.example.androidempresax.db.Emprestimo;
import com.example.androidempresax.db.Equipamento;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class EmprestimoFragment extends Fragment {
    private static String selectedItem;
    public ListView listEmprestimos;
    View fragmentoEmprestimo;

    ArrayList<Emprestimo> arrayListEmprestimos;
    ArrayAdapter<Emprestimo> emprestimosArrayAdapter;


    public EmprestimoFragment() {
        // Required empty public constructor
    }

    public static EmprestimoFragment newInstance() {
        EmprestimoFragment fragment = new EmprestimoFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_emprestimo, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        listEmprestimos = (ListView) getView().findViewById(R.id.listEmprestimos);
        registerForContextMenu(listEmprestimos);
        DBHelperEmprestimo helperEmprestimo = new DBHelperEmprestimo(getActivity());
        arrayListEmprestimos = helperEmprestimo.selectEmprestimosEfetuados();
        helperEmprestimo.close();
        if (listEmprestimos != null) {
            emprestimosArrayAdapter = new ArrayAdapter<Emprestimo>(getActivity(), android.R.layout.simple_list_item_1, arrayListEmprestimos);
            listEmprestimos.setAdapter(emprestimosArrayAdapter);
        }
        listEmprestimos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                selectedItem = listEmprestimos.getItemAtPosition(i).toString();

            }
        });

    }

    public static String getSelectedItem() {
        return selectedItem;
    }
}