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
            "telefone text not null, data text not null, devolvido bool not null);";

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
        values.put(COLUMN_DATE, "GENERATE_DATE_AUTO");
        values.put(COLUMN_DEVOLVIDO, false);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<Emprestimo> selectEmprestimosEfetuados() {
        String[] columns = {COLUMN_ID, COLUMN_ID_EQUIP, COLUMN_NAME, COLUMN_PHONE, COLUMN_DATE, COLUMN_DEVOLVIDO};

//        Cursor cursor = getReadableDatabase().query(TABLE_NAME, columns, null, null, null, null, "upper(numEmpres)", null);
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
}
