package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class SourceActivity extends AppCompatActivity {
    private static final String CATEGORIA = "CicloVida";
    private static final int CODIGO = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(CATEGORIA,getClass()+".onCreate --> Created!");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(CATEGORIA,getClass()+".onStart --> Started!");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(CATEGORIA,getClassName()+".onDestroy --> Destroyed!");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i(CATEGORIA,getClassName()+".onResume --> Resumed!");

    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i(CATEGORIA, getClassName() + ".onPause --> Paused!");



    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i(CATEGORIA, getClassName() + ".onStop --> Stoped!");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i(CATEGORIA, getClassName() + ".onCreate --> Created!");
    }
    public void onClickBt1(View view){
        Intent tela = new Intent(this,TargetActivity.class);
        startActivity(tela);
    }
    public void startActivitImplicita(View v){
        Intent intent = new Intent();
        intent.setAction("br.ufc.dc.dspm.action.ACAO");
        intent.addCategory("br.ufc.dc.dspm.category.CATEGORIA");
        intent.setComponent(null);
        startActivity(intent);
    }

    public void comResultado(View v){
        Intent intent = new Intent(this,TargetActivity.class);
        startActivityForResult(intent,CODIGO);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Log.i(CATEGORIA,"FOIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
        if(requestCode == CODIGO){
            String texto="";
            if(resultCode == RESULT_OK)
                texto = "Ok"+data.getStringExtra("texto");
            if(resultCode == RESULT_CANCELED)
                texto = "Cancelado"+data.getStringExtra("texto");
            Toast.makeText(this,texto+"\n",Toast.LENGTH_SHORT).show();
        }
    }
    private String getClassName(){
        return SourceActivity.class.getName();
    }

}
