package com.example.androidempresax.db;

import java.io.Serializable;

public class Emprestimo implements Serializable {
    private int numEmpres;
    private int equipamentoId;
    private String nomePessoa, telefone, data;
    private boolean devolvido;


    public Emprestimo(){ }
    public int getNumEmpres() {
        return numEmpres;
    }

    public void setNumEmpres(int numEmpres) {
        this.numEmpres = numEmpres;
    }

    public int getEquipamentoId() {
        return equipamentoId;
    }

    public void setEquipamentoId(int equipamentoId) {
        this.equipamentoId = equipamentoId;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    public String toString() {
        String emprestimoDevolvido = "Não";
        if (devolvido){
            emprestimoDevolvido = "Sim";
        }
        return "Nome: " + nomePessoa.toString() + " - Equipamento ID: " + equipamentoId + " - Devolvido: "+ emprestimoDevolvido + " - Data: " + data;
    }
}
