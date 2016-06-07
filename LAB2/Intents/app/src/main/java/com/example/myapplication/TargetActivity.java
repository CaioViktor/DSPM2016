package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class TargetActivity extends AppCompatActivity {
    private static final String CATEGORIA = "CicloVida";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i(CATEGORIA, getClass() + ".onStart --> Started!");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(CATEGORIA, getClassName() + ".onDestroy --> Destroyed!");
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
    private String getClassName(){
        return SourceActivity.class.getName();
    }
    public void voltar(View v){
        finish();
    }
    public void confirma(View v){
        Intent intent = new Intent();
        intent.putExtra("texto","O  usuário  clicou em CONFIRMA");
        setResult(RESULT_OK, intent);
        finish();
    }
    public void cancela(View v){
        Intent intent = new Intent();
        intent.putExtra("texto","O  usuário  clicou em CANCELA");
        setResult(RESULT_CANCELED,intent);
        finish();
    }
}
