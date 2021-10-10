package com.example.androidempresax.db;

public class Equipamento {

    private int equipamentoId;
    private String nomeEquip, marca;

    public Equipamento(){}

    public int getEquipamentoId() {
        return equipamentoId;
    }

    public void setEquipamentoId(int equipamentoId) {
        this.equipamentoId = equipamentoId;
    }

    public String getNomeEquip() {
        return nomeEquip;
    }

    public void setNomeEquip(String nomeEquip) {
        this.nomeEquip = nomeEquip;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String toString() {
        return "Nome: " + nomeEquip.toString() + " - Marca: " + marca.toString() + " - ID: " + equipamentoId;
    }
}
