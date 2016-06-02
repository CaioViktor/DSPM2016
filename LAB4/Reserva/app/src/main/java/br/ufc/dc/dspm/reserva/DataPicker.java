package br.ufc.dc.dspm.reserva;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by 354042 on 19/04/16.
 */
public class DataPicker extends AppCompatDialogFragment implements DatePickerDialog.OnDateSetListener{
    private int ano,mes,dia;
    @Override
    public Dialog onCreateDialog(Bundle savedInstance){
        final Calendar calendario = Calendar.getInstance();
        ano = calendario.get(Calendar.YEAR);
        mes = calendario.get(Calendar.MONTH);
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),this,ano,mes,dia);
    }

    @Override
    public void onDateSet(DatePicker picker,int ano,int mes,int dia){
        MainActivity pai = (MainActivity)getActivity();
        pai.setDate(ano,mes,dia);
    }


}
