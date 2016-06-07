package br.ufc.dc.dspm.mynotes;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MyNotes extends Activity {
    private NoteDAO noteDAO;

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
        listAll();
    }

    public void addNote(View view) {
        EditText titleText = (EditText) findViewById(R.id.editTextTitle);
        EditText contentText = (EditText) findViewById(R.id.editTextContent);
        EditText dataEdit = (EditText)findViewById(R.id.date);


        Note note = new Note();
        note.setTitle(titleText.getText().toString());
        note.setContent(contentText.getText().toString());
        note.setDataFormatada(dataEdit.getText().toString());
        noteDAO.create(note);
        listAll();


    }
    private void listAll(){
        List<Note> notes = noteDAO.list();
        if(notes != null){

            Iterator<Note> it = notes.iterator();
            StringBuffer buffer = new StringBuffer();
            Note note;
            while (it.hasNext()) {
                note = it.next();
                buffer.append(note.toString());
                buffer.append("\n");
            }
            TextView list = (TextView) findViewById(R.id.textViewNotes);
            list.setText(buffer.toString());
        }
    }
    public void filtrar(View v){
        EditText dataINI = (EditText)findViewById(R.id.dataIni);
        EditText dataFIM = (EditText)findViewById(R.id.dataFim);

        List<Note> notes = noteDAO.listByIterval(dataINI.getText().toString(), dataFIM.getText().toString());
        if(notes != null){

            Iterator<Note> it = notes.iterator();
            StringBuffer buffer = new StringBuffer();
            Note note;
            while (it.hasNext()) {
                note = it.next();
                buffer.append(note.toString());
                buffer.append("\n");
            }
            TextView list = (TextView) findViewById(R.id.textViewNotes);
            list.setText(buffer.toString());
        }
    }
}
