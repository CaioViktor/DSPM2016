package br.ufc.dc.dspm.mynotes;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MyNotes extends Activity {
    private NoteDAO noteDAO;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
        noteDAO = new NoteDAO(this);
        String data = ""+Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        data+="/"+Calendar.getInstance().get(Calendar.MONTH);
        data+="/"+Calendar.getInstance().get(Calendar.YEAR);
        EditText dataEdit = (EditText)findViewById(R.id.date);
        dataEdit.setText(data);
        listView = (ListView) findViewById(R.id.textViewNotes);
        listAll();
    }

    public void addNote(View view) {
        EditText titleText = (EditText) findViewById(R.id.editTextTitle);
        EditText dataEdit = (EditText)findViewById(R.id.date);
        EditText contentText = (EditText)findViewById(R.id.editTextContent);

        Note note = new Note();
        note.setTitle(titleText.getText().toString());
        note.setContent(contentText.getText().toString());
        note.setDataFormatada(dataEdit.getText().toString());
        noteDAO.create(note);
        listAll();


    }
    private void listAll(){
        List<Note> notes = noteDAO.list();
        updateList(notes);

    }
    public void filtrar(View v){
        EditText dataINI = (EditText)findViewById(R.id.dataIni);
        EditText dataFIM = (EditText)findViewById(R.id.dataFim);

        List<Note> notes = noteDAO.listByIterval(dataINI.getText().toString(), dataFIM.getText().toString());
        updateList(notes);
    }
    private void updateList(List<Note> notas){
        if(notas != null && notas.size() > 0){
            AdapterNote adapterNote = new AdapterNote(this,notas);
            listView.setAdapter(adapterNote);
            listView.setOnItemClickListener(adapterNote);
            listView.setVisibility(View.VISIBLE);
        }
        else {
            listView.setVisibility(View.INVISIBLE);
        }

    }
    protected void onActivityResult(int codReq,int codRes,Intent dados){
        if(codReq == 0){
            if(codRes == RESULT_OK){
                listAll();
            }
        }
    }
}
