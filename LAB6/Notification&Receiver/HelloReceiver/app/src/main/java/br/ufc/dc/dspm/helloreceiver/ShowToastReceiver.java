package br.ufc.dc.dspm.helloreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by 354042 on 26/04/16.
 */
public class ShowToastReceiver extends BroadcastReceiver {
    public void onReceive(Context contexto,Intent intent){
        Toast.makeText(contexto,"Broadcast receiveid!",Toast.LENGTH_LONG).show();
    }
}
