package com.ufc.caio.sensoresescuta;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private boolean listening;
    private TextView[] medicoes;
    private Button botao;

    private SensorManager manager;
    private Sensor[] sensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listening = false;

        medicoes = new TextView[4];
        medicoes[0] = (TextView)findViewById(R.id.medicao0);//temperatura
        medicoes[1] = (TextView)findViewById(R.id.medicao1);//luminosidade
        medicoes[2] = (TextView)findViewById(R.id.medicao2);//pressão
        medicoes[3] = (TextView)findViewById(R.id.medicao3);//umidade

        botao = (Button)findViewById(R.id.start_stop);

        manager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor = new Sensor[4];
        sensor[0] = manager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        sensor[1] = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensor[2] = manager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        sensor[3] = manager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);


    }

    protected void onResume(){
        super.onResume();
        if(listening)
            registrar();
    }

    private void registrar(){
        for(int i = 0 ; i < 4;i++)
            manager.registerListener(this,sensor[i],SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause(){
        super.onPause();
        if(listening)
            manager.unregisterListener(this);
    }
    public void monitorar(View v){
        if(!listening){
            listening = true;
            botao.setText("Pausar");
            registrar();
        }else{
            listening = false;
            botao.setText("Monitorar");
            manager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(sensor[0] == event.sensor){
            medicoes[0].setText(event.values[0]+"");
        }else if(sensor[1] == event.sensor){
            medicoes[1].setText(event.values[0] + "");
        }else if(sensor[2] == event.sensor){
            medicoes[2].setText(event.values[0] + "");
        }else if(sensor[3] == event.sensor){
            medicoes[3].setText(event.values[0] + "");
        }else{
            Log.i("LOGP","Sensor não encontrado");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
