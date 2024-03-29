package com.cursoandroid.applistadetarefas.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_DB = "DB_TAREFAS";
    public static String TABELA_TAREFAS = "tarefas";

    public Dbhelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase ) {

        String sql = "CREATE TABLE IF NOT EXISTS tarefas" + TABELA_TAREFAS
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT," + "nome TEXT NOT NULL); ";


        try {
            sqLiteDatabase.execSQL(sql);
            Log.i("INFO", "sucesso ao cliar tabela");

        }catch (Exception e){
            Log.i("INFODB", "erro ao cliar tabela" + e.getMessage());

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //excluir tabela
        String sql = "DROP TABLE IF EXISTS " + TABELA_TAREFAS + " ;";
        //Alterar a tabela
        //String sql = "ALTER TABLE " + TABELA_TAREFAS + " ADD COLUMN status VARCHAR (2)";

        try {
            sqLiteDatabase.execSQL(sql);
            onCreate(sqLiteDatabase);
            Log.i("INFO", "sucesso ao atualizar tabela");

        }catch (Exception e){
            Log.i("INFODB", "erro ao cliar tabela" + e.getMessage());

        }

    }
}
