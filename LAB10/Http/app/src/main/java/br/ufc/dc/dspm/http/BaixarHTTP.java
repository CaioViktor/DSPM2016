package br.ufc.dc.dspm.http;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 354042 on 24/05/16.
 */
public class BaixarHTTP extends AsyncTask<String,String,String> {
    private TextView mostrar;
    public BaixarHTTP(TextView mostrar){
        this.mostrar = mostrar;
    }

    @Override
    protected String doInBackground(String... params) {
        String conteudo = "";
        try {
            String url = params[0];
            URL link = new URL(url);
            try {
                HttpURLConnection connection = (HttpURLConnection)link.openConnection();
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(15000);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();
                InputStream is = connection.getInputStream();
                BufferedReader leitor = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                String linha = leitor.readLine();
                while(linha != null){
                    conteudo+=linha;
                    linha = leitor.readLine();
                }
                return conteudo;
            } catch (IOException e) {
                        e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return conteudo;
    }

    protected void onProgressUpdate(String result){

    }
    protected void onPostExecute(String result){
        mostrar.setText(result);
    }
}
