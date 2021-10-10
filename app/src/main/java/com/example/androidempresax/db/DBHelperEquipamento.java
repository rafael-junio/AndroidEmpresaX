package com.example.androidempresax.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelperEquipamento extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "androidEmpresa.db";
    private static final String TABLE_NAME = "equipamento";
    private static final String COLUMN_ID = "equipamentoId";
    private static final String COLUMN_NAME = "nomeEquip";
    private static final String COLUMN_MARCA = "marca";


    SQLiteDatabase db;

    private static final String TABLE_CREATE = "CREATE TABLE equipamento" +
            "(equipamentoId integer primary key autoincrement, nomeEquip text not null," +
            "marca text not null);";

    public DBHelperEquipamento(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public void inserirEquipamento(Equipamento e){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, e.getNomeEquip());
        values.put(COLUMN_MARCA, e.getMarca());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<Equipamento> selectAllEquipamentos() {
        db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_NAME, COLUMN_MARCA};

        Cursor cursor = getReadableDatabase().query(TABLE_NAME, columns, null, null, null, null, "upper(nomeEquip)", null);

        ArrayList<Equipamento> listaEquipamento = new ArrayList<Equipamento>();
        while(cursor.moveToNext()){
            Equipamento e = new Equipamento();
            e.setEquipamentoId(cursor.getInt(0));
            e.setNomeEquip(cursor.getString(1));
            e.setMarca(cursor.getString(2));
            listaEquipamento.add(e);
        }
        return listaEquipamento;
    }
}
