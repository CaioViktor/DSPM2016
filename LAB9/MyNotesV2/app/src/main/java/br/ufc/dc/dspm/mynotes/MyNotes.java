package br.ufc.dc.dspm.mynotes;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MyNotes extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
    }

    public void addNote(View view) {
        EditText titleText = (EditText) findViewById(R.id.editTextTitle);
        EditText contentText = (EditText) findViewById(R.id.editTextContent);
        ContentValues values = new ContentValues();
        values.put(NotesProvider.TITLE, titleText.getText().toString());
        values.put(NotesProvider.CONTENT, contentText.getText().toString());

        Uri uri = getContentResolver().insert(NotesProvider.CONTENT_URI, values);
        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void listNotes(View view) {
        StringBuffer buffer = new StringBuffer();
        String URL = NotesProvider.URL;
        Uri notesURI = Uri.parse(URL);
        Cursor cursor = getContentResolver().query(notesURI, null, null, null, NotesProvider.ID);
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndex(NotesProvider.ID)));
                note.setTitle(cursor.getString(cursor.getColumnIndex(NotesProvider.TITLE)));
                note.setContent(cursor.getString(cursor.getColumnIndex(NotesProvider.CONTENT)));
                buffer.append(note.toString());
                buffer.append("\n\n");
            } while (cursor.moveToNext());
        }
        TextView list = (TextView) findViewById(R.id.textViewNotes);
        list.setText(buffer.toString());
    }

}
