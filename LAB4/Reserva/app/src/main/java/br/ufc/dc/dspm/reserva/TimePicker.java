package br.ufc.dc.dspm.reserva;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.Toast;

import java.util.Calendar;

public class TimePicker extends AppCompatDialogFragment implements TimePickerDialog.OnTimeSetListener{
    private MainActivity parent;
    @Override
    public Dialog onCreateDialog(Bundle instanceSaved){
        final Calendar calendario = Calendar.getInstance();


        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        int minuto = calendario.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(),this,hora,minuto,true);
    }

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hora, int minuto) {
        parent.setTime(hora,minuto);
    }

    public void setParent(MainActivity parent){
        this.parent = parent;
    }
}
