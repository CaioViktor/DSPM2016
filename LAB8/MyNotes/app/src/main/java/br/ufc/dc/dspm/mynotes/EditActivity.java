package br.ufc.dc.dspm.mynotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    private EditText titulo;
    private EditText conteudo;
    private EditText data;
    private NoteDAO dao;
    private Note note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        titulo = (EditText)findViewById(R.id.TextTitle);
        conteudo = (EditText)findViewById(R.id.TextContent);
        data = (EditText)findViewById(R.id.editDate);
        Intent intent = getIntent();
        titulo.setText(intent.getStringExtra("titulo"));
        conteudo.setText(intent.getStringExtra("conteudo"));
        data.setText(intent.getStringExtra("data"));
        note = new Note();
        dao = new NoteDAO(this);
        note.setId(intent.getIntExtra("id",0));
        note.setTitle(intent.getStringExtra("titulo"));
        note.setContent(intent.getStringExtra("conteudo"));
        note.setData(intent.getStringExtra("data"));
    }
    public void atualizar(View v){
        note.setTitle(titulo.getText().toString());
        note.setContent(conteudo.getText().toString());
        note.setDataFormatada(data.getText().toString());
        dao.update(note);
        setResult(RESULT_OK, null);
        finish();
    }
    public void apagar(View v){
        dao.delete(note.getId());
        setResult(RESULT_OK,null);
        finish();
    }
}
