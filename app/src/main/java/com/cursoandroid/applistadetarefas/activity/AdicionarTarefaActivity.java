package com.cursoandroid.applistadetarefas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cursoandroid.applistadetarefas.R;
import com.cursoandroid.applistadetarefas.helper.TarefaDAO;
import com.cursoandroid.applistadetarefas.model.Tarefa;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private TextInputEditText textTarefas;
    private Tarefa tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        textTarefas = findViewById(R.id.textTarefas);
        //recuperar tarefa  Atual
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");
        //Configurar tarefa na caixa de texto
        if(tarefaAtual!= null ){
            textTarefas.setText(tarefaAtual.getNomeTarefa());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_tarefa,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


            switch (item.getItemId()){
                case  R.id.itemSalvar:
                    // execulta a ação para salvar

                  TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

                  if(tarefaAtual!=null){ //edição
                      String nomeTarefa = textTarefas.getText().toString();
                      if (!nomeTarefa.isEmpty()){
                          Tarefa tarefa = new Tarefa();
                          tarefa.setNomeTarefa(nomeTarefa);
                          tarefa.setId(tarefaAtual.getId());

                          //atualizar o banco de dados
                          if (tarefaDAO.atualizar(tarefa)){
                              finish();
                              Toast.makeText(getApplicationContext(),
                                      "Sucesso ao atualizar tarefa",
                                      Toast.LENGTH_SHORT).show();
                          } else{
                              finish();
                              Toast.makeText(getApplicationContext(),
                                      "Erro ao atualizar tarefa",
                                      Toast.LENGTH_SHORT).show();
                          }

                      }

                  }else{ // salvar
                      String nomeTarefa = textTarefas.getText().toString();
                      if (!nomeTarefa.isEmpty()) {
                          Tarefa tarefa = new Tarefa();
                          tarefa.setNomeTarefa(nomeTarefa);

                          if ( tarefaDAO.salvar(tarefa)){

                              finish();
                              Toast.makeText(getApplicationContext(),
                              "Sucesso ao salvar tarefa",
                              Toast.LENGTH_SHORT).show();
                          }else{
                              Toast.makeText(getApplicationContext(),
                                      "Erro ao salvar tarefa",
                                      Toast.LENGTH_SHORT).show();
                          }


                  }
                    }
                    break;
            }
        return super.onOptionsItemSelected(item);
    }
}