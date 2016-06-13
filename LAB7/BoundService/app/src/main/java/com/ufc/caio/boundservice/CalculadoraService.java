package com.ufc.caio.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class CalculadoraService extends Service {

    private final IBinder binder = new BinderLocal();
    public class BinderLocal extends Binder {
        public CalculadoraService getService(){
            return CalculadoraService.this;
        }
    }
    public CalculadoraService() {
    }

    public void calcular(MainActivity m,double n1,double n2,String op){
        String r = "";
        if(op.equals("+")){
            r = ""+(n1 + n2);
        }else if(op.equals("-")){
            r = ""+(n1 - n2);
        }else if(op.equals("*")){
            r = ""+(n1 * n2);
        }else if(op.equals("/")){
            if(n2 != 0 )
                r = ""+(n1 / n2);
            else
                r = "N2 deve ser diferente de 0";
        }
        m.atualizar(r);
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }
}
