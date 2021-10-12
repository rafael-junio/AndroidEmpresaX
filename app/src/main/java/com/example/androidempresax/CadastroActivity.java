package com.example.androidempresax;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.androidempresax.db.DBHelperEmprestimo;
import com.example.androidempresax.db.DBHelperEquipamento;
import com.example.androidempresax.db.Emprestimo;
import com.example.androidempresax.db.Equipamento;
import com.example.androidempresax.fragments.CadastroEmprestimoFragment;
import com.example.androidempresax.fragments.EquipamentosFragment;
import com.example.androidempresax.ui.main.CadastroSectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidempresax.ui.main.SectionsPagerAdapter;
import com.example.androidempresax.databinding.ActivityCadastroBinding;

import java.util.ArrayList;

public class CadastroActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private ActivityCadastroBinding binding;
    private DBHelperEquipamento helperEquipamento = new DBHelperEquipamento(this);
    private DBHelperEmprestimo helperEmprestimo = new DBHelperEmprestimo(this);

    private EditText edtNome;
    private EditText edtMarca;
    private EditText edtPhone;
    private TextView edtID;
    private Button btnVariavel;

    private ListView listEquipamentos;

    ArrayList<Equipamento> arrayListEquipamento;
    ArrayAdapter<Equipamento> equipamentoArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        CadastroSectionsPagerAdapter sectionsPagerAdapter = new CadastroSectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);


    }

    public void onResume(){
        super.onResume();
    }


    public void efetuarEmprestimo(View view) {
        edtNome = findViewById(R.id.textPersonName);
        edtPhone = findViewById(R.id.textPhone);
        String nome = edtNome.getText().toString();
        String telefone = edtPhone.getText().toString();
        String selectedItem = CadastroEmprestimoFragment.getSelectedItem();
        CadastroEmprestimoFragment.setSelectedItemNull();
        btnVariavel = findViewById(R.id.buttonEmp);

        if (!nome.equals("") && !telefone.equals("") && selectedItem != null) {
            String equipamentoIDStr = selectedItem.substring(selectedItem.indexOf("ID: ") + 4);
            int equipamentoID = Integer.parseInt(equipamentoIDStr);

            Emprestimo e = new Emprestimo();
            e.setNomePessoa(nome);
            e.setTelefone(telefone);
            e.setEquipamentoId(equipamentoID);
            e.setDevolvido(false);

            if (btnVariavel.getText().toString().equals("Cadastrar Emprestimo!")) {

                helperEmprestimo.inserirEmprestimo(e);
                Snackbar.make(view, "Emprestímo realizado!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                reloadActivity(view);
            }
            else {
                edtID = (TextView) findViewById(R.id.textEmpID);
                int id = Integer.parseInt(edtID.getText().toString());

                e.setNumEmpres(id);
                helperEmprestimo.updateEmprestimo(e);
                Snackbar.make(view, "Emprestimo atualizado!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                reloadActivity(view);
            }
        }
        else {
            Snackbar.make(view, "Preencha os campos corretamente", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    public void cadastrarEquipamento(View view) {
        edtNome = findViewById(R.id.textEquipName);
        edtMarca = findViewById(R.id.textMarca);
        String nome = edtNome.getText().toString();
        String marca = edtMarca.getText().toString();
        btnVariavel = findViewById(R.id.buttonEquip);

        if (!nome.equals("") && !marca.equals("")) {
            Equipamento e = new Equipamento();
            e.setNomeEquip(nome);
            e.setMarca(marca);
            if (btnVariavel.getText().toString().equals("Cadastrar equipamento!")){
                helperEquipamento.inserirEquipamento(e);
                Snackbar.make(view, "Equipamento Cadastrado!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                reloadActivity(view);
            }
            else {
                edtID = (TextView) findViewById(R.id.textEquipID);
                int id = Integer.parseInt(edtID.getText().toString());

                e.setEquipamentoId(id);
                helperEquipamento.updateEquipamento(e);
                Snackbar.make(view, "Equipamento atualizado!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                reloadActivity(view);

            }
        }
        else {
            Snackbar.make(view, "Preencha os campos corretamente", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        helperEquipamento.close();

    }

    public void navigateFragment(int position){
        viewPager.setCurrentItem(position, true);
    }

    public void reloadActivity(View view) {
        Intent intent = new Intent(CadastroActivity.this, MainActivity.class);
        finish();
        startActivity(intent);
    }
}