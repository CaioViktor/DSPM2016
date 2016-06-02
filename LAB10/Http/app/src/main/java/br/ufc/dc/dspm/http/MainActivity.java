package br.ufc.dc.dspm.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textUrl;
    private TextView textConteudo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textUrl = (TextView)findViewById(R.id.url);
        textConteudo = (TextView)findViewById(R.id.conteudo);
        if(isConnected()){
            Toast.makeText(this,"Você está conectado à rede.",Toast.LENGTH_LONG );
        }else{
            Toast.makeText(this,"Você não está conectado à rede.",Toast.LENGTH_LONG );
        }

    }
    private boolean isConnected(){
        ConnectivityManager conection = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = conection.getActiveNetworkInfo();
        if(info !=null && info.isConnected())
            return true;
        else
            return false;
    }

    public void baixar(View v){
        BaixarHTTP baixa = new BaixarHTTP(textConteudo);

        baixa.execute(textUrl.getText().toString());
    }
}
