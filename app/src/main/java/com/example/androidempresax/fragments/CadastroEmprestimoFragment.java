package com.example.androidempresax.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidempresax.CadastroActivity;
import com.example.androidempresax.R;
import com.example.androidempresax.db.DBHelperEmprestimo;
import com.example.androidempresax.db.DBHelperEquipamento;
import com.example.androidempresax.db.Emprestimo;
import com.example.androidempresax.db.Equipamento;

import java.util.ArrayList;


public class CadastroEmprestimoFragment extends Fragment {

    private static String selectedItem;
    public ListView listEquipamentos;
    private EditText edtNome, edtTelefone;
    private TextView edtID;
    private Button btnVariavel;
    View fragmentoEmprestimo;

    Emprestimo emprestimo, altEmprestimo;

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
        Intent it = getActivity().getIntent();
        altEmprestimo = (Emprestimo) it.getSerializableExtra("chave_emprestimo");
        emprestimo = new Emprestimo();
        DBHelperEmprestimo helperEmprestimo = new DBHelperEmprestimo(getActivity());

        edtNome = getView().findViewById(R.id.textPersonName);
        edtTelefone = getView().findViewById(R.id.textPhone);
        edtID = (TextView) getView().findViewById(R.id.textEmpID);
        btnVariavel = getView().findViewById(R.id.buttonEmp);

        listEquipamentos = (ListView) getView().findViewById(R.id.listEquipamentosCad);
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

        if (altEmprestimo != null) {
            ((CadastroActivity)getActivity()).navigateFragment(0);
            btnVariavel.setText("Atualizar emprestimo");
            edtNome.setText(altEmprestimo.getNomePessoa());
            edtTelefone.setText(altEmprestimo.getTelefone());
            int id = altEmprestimo.getNumEmpres();
            edtID.setText(String.valueOf(id));
            emprestimo.setNumEmpres(altEmprestimo.getNumEmpres());
        }
        else {
            btnVariavel.setText("Cadastrar Emprestimo!");
        }
    }

    public static String getSelectedItem() {
        return selectedItem;
    }

    public static void setSelectedItemNull(){
        selectedItem = null;
    }

}