package com.ufc.caio.boundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText N1,N2;
    private TextView Re;
    private Spinner OP;
    private CalculadoraService calculadora;
    private boolean isBinded = false;
    private Handler handler = new Handler();
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            CalculadoraService.BinderLocal binder = (CalculadoraService.BinderLocal) service;
            calculadora = binder.getService();
            isBinded = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    protected void onDestroy(){
        super.onDestroy();
        unbindService(connection);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        N1 = (EditText)findViewById(R.id.n1);
        N2 = (EditText)findViewById(R.id.n2);
        Re = (TextView)findViewById(R.id.resultado);
        OP = (Spinner)findViewById(R.id.operacao);

        String[] ops = getResources().getStringArray(R.array.operacoes);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ops);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        OP.setAdapter(adapter);

        Intent intent = new Intent(this,CalculadoraService.class);
        bindService(intent,connection, Context.BIND_AUTO_CREATE);
    }
    public void calcular(View v){
        if(isBinded){
            calculadora.calcular(this,Double.parseDouble(N1.getText().toString()),Double.parseDouble(N2.getText().toString()),OP.getSelectedItem().toString());
        }
    }
    public void atualizar(String resultado){
        final String r = resultado;
        handler.post(new Runnable(){
            public void run(){
                Re.setText(r);
            }
        });
    }
}
