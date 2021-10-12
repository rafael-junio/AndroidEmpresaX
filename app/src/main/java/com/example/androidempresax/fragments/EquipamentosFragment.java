package com.example.androidempresax.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androidempresax.CadastroActivity;
import com.example.androidempresax.MainActivity;
import com.example.androidempresax.R;
import com.example.androidempresax.db.DBHelperEquipamento;
import com.example.androidempresax.db.Equipamento;

import java.io.Serializable;
import java.util.ArrayList;

public class EquipamentosFragment extends Fragment {

    private static String selectedItem;
    public ListView listEquipamentos;
    Equipamento equipamento;
    private int id1, id2;

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

    public void onResume() {
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
        listEquipamentos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                equipamento = equipamentoArrayAdapter.getItem(position);
                return false;
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem mDelete = menu.add(Menu.NONE, id1, 1, "Apague equipamento");
        MenuItem mSair = menu.add(Menu.NONE, id2, 2, "Cancela");
        mDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                long retornoBD;
                DBHelperEquipamento helperEquipamento = new DBHelperEquipamento(getActivity());
                retornoBD = helperEquipamento.deleteContato(equipamento);
                helperEquipamento.close();
                if (retornoBD == -1) {
                    alert("Erro de exclusão!");
                } else {
                    alert("Registro excluído com sucesso!");
//                    ((MainActivity) getActivity()).navigateFragment(1);
                    reloadActivity(v);
                }
                return false;
            }
        });
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    private void alert(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
    }

    public void reloadActivity(View view) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        getActivity().finish();
        startActivity(intent);
    }
}