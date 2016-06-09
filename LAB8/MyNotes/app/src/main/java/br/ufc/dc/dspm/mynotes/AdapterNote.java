package br.ufc.dc.dspm.mynotes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by caio on 07/06/16.
 */
public class AdapterNote extends BaseAdapter implements ListView.OnItemClickListener {

    private List<Note> notas;
    private LayoutInflater layout;
    private Context context;
    public AdapterNote(Context context,List<Note> notas){
        this.notas = notas;
        layout = LayoutInflater.from(context);
        this.context = context;
    }


    @Override
    public int getCount() {
        return notas.size();
    }

    @Override
    public Object getItem(int position) {
        return notas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NotaSuporte suporte;
        if(convertView == null){
            convertView = layout.inflate(R.layout.item_nota,null);
            suporte = new NotaSuporte();
            suporte.titulo = (TextView)convertView.findViewById(R.id.nota_nome);
            suporte.data = (TextView)convertView.findViewById(R.id.nota_data);
            suporte.conteudo = (TextView)convertView.findViewById(R.id.nota_conteudo);

            convertView.setTag(suporte);
        }else
            suporte = (NotaSuporte)convertView.getTag();
        Note nota = notas.get(position);
        suporte.titulo.setText("("+nota.getId()+")"+nota.getTitle()+":");
        suporte.data.setText("Data: "+nota.getDataFormatada());
        suporte.conteudo.setText("Conteúdo:\n"+nota.getContent());
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(context,EditActivity.class);
        Note note = notas.get(position);
        intent.putExtra("id",note.getId());
        intent.putExtra("titulo",note.getTitle());
        intent.putExtra("conteudo",note.getContent());
        intent.putExtra("data",note.getDataFormatada());
        ((Activity)context).startActivityForResult(intent,0);
    }

    public class NotaSuporte{
        TextView titulo;
        TextView data;
        TextView conteudo;
    }
}
