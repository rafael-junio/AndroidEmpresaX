package com.example.androidempresax.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelperEmprestimo extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "emprestimo.db";
    private static final String TABLE_NAME = "emprestimo";
    private static final String COLUMN_ID = "numEmpres";
    private static final String COLUMN_ID_EQUIP = "equipamentoId";
    private static final String COLUMN_NAME = "nomePessoa";
    private static final String COLUMN_PHONE = "telefone";
    private static final String COLUMN_DATE = "data";
    private static final String COLUMN_DEVOLVIDO = "devolvido";

    SQLiteDatabase db;

    private static final String TABLE_CREATE = "CREATE TABLE emprestimo" +
            "(numEmpres integer primary key autoincrement, equipamentoId integer not null, nomePessoa text not null," +
            "telefone text not null, data datetime default (datetime('now','localtime')), devolvido bool not null);";

    public DBHelperEmprestimo(@Nullable Context context) {
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

    public void inserirEmprestimo(Emprestimo e){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_EQUIP, e.getEquipamentoId());
        values.put(COLUMN_NAME, e.getNomePessoa());
        values.put(COLUMN_PHONE, e.getTelefone());
        values.put(COLUMN_DEVOLVIDO, false);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<Emprestimo> selectEmprestimosEfetuados() {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM emprestimo", null);
        ArrayList<Emprestimo> listaEmprestimo = new ArrayList<Emprestimo>();
        while(cursor.moveToNext()){
            Emprestimo e = new Emprestimo();
            e.setNumEmpres(cursor.getInt(0));
            e.setEquipamentoId(cursor.getInt(1));
            e.setNomePessoa(cursor.getString(2));
            e.setTelefone(cursor.getString(3));
            e.setData(cursor.getString(4));
            e.setDevolvido(Boolean.parseBoolean(cursor.getString(5)));

            listaEmprestimo.add(e);
        }
        return listaEmprestimo;
    }

    public void updateEmprestimo(Emprestimo e) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, e.getNomePessoa());
        values.put(COLUMN_PHONE, e.getTelefone());
        values.put(COLUMN_ID_EQUIP, e.getEquipamentoId());
        String[] args = {String.valueOf(e.getNumEmpres())};
        db.update(TABLE_NAME,values,"numEmpres=?", args);
        db.close();
    }

    public long deleteContato(Emprestimo e) {
        long retornoBD;
        db = this.getWritableDatabase();
        String[] args = {String.valueOf(e.getNumEmpres())};
        retornoBD = db.delete(TABLE_NAME, COLUMN_ID + "=?", args);
        return retornoBD;
    }

    public long devolverEmprestimo(Emprestimo e) {
        long retornDB;
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DEVOLVIDO, "true");
        String[] args = {String.valueOf(e.getNumEmpres())};
        retornDB = db.update(TABLE_NAME, values,"numEmpres=?", args);
        db.close();
        return retornDB;
    }
}
