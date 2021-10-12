package com.example.androidempresax.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
    Emprestimo emprestimo;
    private int id1, id2, id3;

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
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Emprestimo emprestimoEnviado = (Emprestimo) emprestimosArrayAdapter.getItem(position);
                Intent it = new Intent(getActivity(), CadastroActivity.class);
                it.putExtra("chave_emprestimo", emprestimoEnviado);
                startActivity(it);

            }
        });

        listEmprestimos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                emprestimo = emprestimosArrayAdapter.getItem(position);
                return false;
            }
        });

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem mDevolver = menu.add(Menu.NONE, id1, 1, "Devolver emprestímo");
        MenuItem mDelete = menu.add(Menu.NONE, id2, 2, "Apague emprestímo");
        MenuItem mSair = menu.add(Menu.NONE, id3, 3, "Cancela");
        mDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                long retornoBD;
                DBHelperEmprestimo helperEmprestimo = new DBHelperEmprestimo(getActivity());
                retornoBD = helperEmprestimo.deleteEmprestimo(emprestimo);
                helperEmprestimo.close();
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
        mDevolver.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                long retornoBD;
                DBHelperEmprestimo helperEmprestimo = new DBHelperEmprestimo(getActivity());
                retornoBD = helperEmprestimo.devolverEmprestimo(emprestimo);
                helperEmprestimo.close();
                if (retornoBD == -1) {
                    alert("Erro de devolução!");
                } else {
                    alert("Emprestimo recebido!!");
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

    public static String getSelectedItem() {
        return selectedItem;
    }
}