package br.ufc.dc.dspm.helloreceiver;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class BroadcastSender extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_sender);
    }
    public void enviar(View v){
        Intent intent = new Intent();
        intent.setAction("br.ufc.dc.dspm.receiver.START_ACTION");
        sendBroadcast(intent);
//        Toast.makeText(this,"foi",Toast.LENGTH_SHORT).show();
    }
}
