package br.ufc.dc.dspm.hellonotification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NotificationSender extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_sender);
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void enviar(View v){
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setTicker("Não sei");
        builder.setContentTitle("Título especial");
        builder.setContentText("Um texto qualquer de conteúdo");
        builder.setSmallIcon(R.drawable.images);

        Intent intent = new Intent(this,NotificationHandler.class);
        PendingIntent ped = PendingIntent.getActivity(this,0,intent,0);
        builder.setContentIntent(ped);
        Notification not = builder.build();
        manager.notify(R.string.app_name,not);

    }
}
