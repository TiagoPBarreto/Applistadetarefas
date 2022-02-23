package com.cursoandroid.applistadetarefas.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cursoandroid.applistadetarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements ITarefaDAO {
    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public TarefaDAO(Context context) {
        Dbhelper db = new Dbhelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Tarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa());

        try {
            escreve.insert(Dbhelper.TABELA_TAREFAS, null, cv );
            Log.i("INFO", "Tarefa salva com sucesso");
        }catch (Exception e){
            Log.e("INFO", "Erro ao salvar tarefa" + e.getMessage() );
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa());
        try {
            String[] args = {tarefa.getId().toString()};
            escreve.update(Dbhelper.TABELA_TAREFAS, cv,"id=?", args);
            Log.i("INFO", "Tarefa atualizada com sucesso");
        }catch (Exception e){
            Log.e("INFO", "Erro ao atualizar tarefa" + e.getMessage() );
            return false;
        }
        return true;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {
        try {
            String[] args = {tarefa.getId().toString()};
            escreve.delete(Dbhelper.TABELA_TAREFAS, "id=?", args);
            Log.i("INFO", "Tarefa removida com sucesso");
        }catch (Exception e){
            Log.e("INFO", "Erro ao remover tarefa" + e.getMessage() );
            return false;
        }

        return true;
    }

    @Override
    public List<Tarefa> listar() {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM " + Dbhelper.TABELA_TAREFAS + " ; ";
        Cursor c = le.rawQuery(sql, null);

     while ( c.moveToNext()){
         Tarefa tarefa = new Tarefa();

         Long id = c.getLong(c.getColumnIndex("id"));
         String nomeTarefa  = c.getString (c.getColumnIndex("nomeTarefa"));

         tarefa.setId(id);
         tarefa.setNomeTarefa(nomeTarefa);

     }

        return tarefas;
    }
}
