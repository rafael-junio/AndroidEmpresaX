package com.example.androidempresax.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidempresax.CadastroActivity;
import com.example.androidempresax.R;
import com.example.androidempresax.db.DBHelperEquipamento;
import com.example.androidempresax.db.Equipamento;

import java.util.ArrayList;


public class CadastroEquipamentoFragment extends Fragment {

    Equipamento equipamento, altEquipamento;
    private EditText edtNome, edtMarca;
    private Button btnVariavel;
    private TextView edtID;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Intent it = getActivity().getIntent();

        altEquipamento = (Equipamento) it.getSerializableExtra("chave_equipamento");

        equipamento = new Equipamento();
        DBHelperEquipamento helperEquipamento = new DBHelperEquipamento(getActivity());

        edtNome = getView().findViewById(R.id.textEquipName);
        edtMarca = getView().findViewById(R.id.textMarca);
        edtID = (TextView) getView().findViewById(R.id.textEquipID);

        btnVariavel = getView().findViewById(R.id.buttonEquip);

        if (altEquipamento != null) {
            ((CadastroActivity)getActivity()).navigateFragment(1);
            btnVariavel.setText("Atualizar equipamento");
            edtNome.setText(altEquipamento.getNomeEquip());
            edtMarca.setText(altEquipamento.getMarca());
            int id = altEquipamento.getEquipamentoId();
            edtID.setText(String.valueOf(id));
            equipamento.setEquipamentoId(altEquipamento.getEquipamentoId());
        }
        else {
            btnVariavel.setText("Cadastrar equipamento!");
        }
    }



    public void onResume(){
        super.onResume();
    }

}