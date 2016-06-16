package br.ufc.dc.dspm.mynotes;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MyNotes extends Activity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
        String data = ""+Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        data+="/"+Calendar.getInstance().get(Calendar.MONTH);
        data+="/"+Calendar.getInstance().get(Calendar.YEAR);
        EditText dataEdit = (EditText)findViewById(R.id.date);
        dataEdit.setText(data);
        listView = (ListView) findViewById(R.id.textViewNotes);
        listNotes(null);
    }

    public void addNote(View view) {
        EditText titleText = (EditText) findViewById(R.id.editTextTitle);
        EditText dataEdit = (EditText)findViewById(R.id.date);
        EditText contentText = (EditText)findViewById(R.id.editTextContent);

        Note note = new Note();
        note.setTitle(titleText.getText().toString());
        note.setContent(contentText.getText().toString());
        note.setDataFormatada(dataEdit.getText().toString());
        ContentValues values = new ContentValues();
        values.put(NotesProvider.TITLE, note.getTitle());
        values.put(NotesProvider.CONTENT, note.getContent());
        values.put(NotesProvider.DATA, note.getData());

        Uri uri = getContentResolver().insert(NotesProvider.CONTENT_URI, values);
        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
        listNotes(null);
    }

    public void listNotes(View view) {
//        StringBuffer buffer = new StringBuffer();
        ArrayList<Note> lista = new ArrayList<>();
        String URL = NotesProvider.URL;
        Uri notesURI = Uri.parse(URL);
        Cursor cursor = getContentResolver().query(notesURI, null, null, null, NotesProvider.ID);
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndex(NotesProvider.ID)));
                note.setTitle(cursor.getString(cursor.getColumnIndex(NotesProvider.TITLE)));
                note.setContent(cursor.getString(cursor.getColumnIndex(NotesProvider.CONTENT)));
                note.setData(cursor.getString(cursor.getColumnIndex(NotesProvider.DATA)));
                lista.add(note);
//                buffer.append(note.toString());
//                buffer.append("\n\n");
            } while (cursor.moveToNext());
        }
//        TextView list = (TextView) findViewById(R.id.textViewNotes);
//        list.setText(buffer.toString());
        updateList(lista);
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
    public void filtrar(View v){
        EditText dataINI = (EditText)findViewById(R.id.dataIni);
        EditText dataFIM = (EditText)findViewById(R.id.dataFim);


        String URL = NotesProvider.URL;
        Uri notesURI = Uri.parse(URL);
        String args[] = {dataINI.getText().toString(),dataFIM.getText().toString()};
        Cursor resultado = getContentResolver().query(notesURI, null,"data BETWEEN ? AND ?" ,args , NotesProvider.ID);
        List<Note> notes = new ArrayList<>();
        if(resultado != null && resultado.getCount() > 0){
            resultado.moveToFirst();
            while(!resultado.isAfterLast()){

                Note note = new Note();
                note.setId(resultado.getInt(0));
                note.setTitle(resultado.getString(1));
                note.setData(resultado.getString(2));
                note.setContent(resultado.getString(3));
                notes.add(note);
                resultado.moveToNext() ;
            }
        }

        updateList(notes);
    }
    protected void onActivityResult(int codReq,int codRes,Intent dados){
        if(codReq == 0){
            if(codRes == RESULT_OK){
                listNotes(null);
            }
        }
    }
}
