package br.ufc.dc.dspm.reserva;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private int hora,minuto;
    private int ano,mes,dia;
    private Spinner laboratoriosS;
    private Button datab,horab;
    private EditText professorE,sipeE,emailE,observacoesE;
    private CheckBox androidC,java,linux,windows;
    private RadioGroup grupo;
    private ToggleButton prioridade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datab = (Button)findViewById(R.id.data);
        horab = (Button)findViewById(R.id.hora);

        laboratoriosS = (Spinner)findViewById(R.id.spinnerLocal);
        String[] labs = getResources().getStringArray(R.array.laboratorios);
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,labs);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        laboratoriosS.setAdapter(adaptador);
        professorE = (EditText)findViewById(R.id.nome);
        sipeE = (EditText)findViewById(R.id.sipe);
        emailE = (EditText)findViewById(R.id.email);
        observacoesE = (EditText)findViewById(R.id.observacao);

        grupo = (RadioGroup)findViewById(R.id.grupo);

        androidC = (CheckBox)findViewById(R.id.checkAndroid);
        java = (CheckBox)findViewById(R.id.checkSdk);
        linux = (CheckBox)findViewById(R.id.checkLinux);
        windows = (CheckBox)findViewById(R.id.checkWindows);

        prioridade = (ToggleButton)findViewById(R.id.tooglePrioridade);

    }
    public void setHora(int hora){
        this.hora = hora;
    }
    public void setMinuto(int minuto){
        this.minuto = minuto;
    }
    public void escolherHora(View v){
        AppCompatDialogFragment fragment = new TimePicker();
        ((TimePicker)fragment).setParent(this);
        fragment.show(getSupportFragmentManager(), "Escolha a hora");
    }
    public void setTime(int hora,int minuto){
        this.hora = hora;
        this.minuto = minuto;
        horab.setText(hora + ":" + minuto);
    }
    public void setDate(int ano,int mes,int dia){
        this.ano = ano;
        this.mes = mes;
        this.dia = dia;
        datab.setText(dia + "/" + mes + "/" + ano);
    }
    public void escolherData(View v){
        AppCompatDialogFragment fragment = new DataPicker();
        fragment.show(getSupportFragmentManager(), "Escolher a data");
    }
    public void validar(View v){
        String mensagem = "========================================================\n";
        mensagem+="Identificação\n";
        mensagem+="========================================================\n";
        mensagem+="Nome do professor: "+professorE.getText()+"\n";
        mensagem+="SIAPE: "+sipeE.getText()+"\n";
        mensagem+="Email: "+emailE.getText()+"\n";
        mensagem+="========================================================\n";
        mensagem+="Dados da reserva\n";
        mensagem+="========================================================\n";
        mensagem+="Data da reserva:"+dia+"/"+mes+"/"+ano+"\n";
        mensagem+="Horário: "+hora+":"+minuto+"H\n";
        mensagem+="Laboratório selecionado: "+laboratoriosS.getSelectedItem().toString()+"\n";

        String precisaDatashow = "";
        switch(grupo.getCheckedRadioButtonId()){
            case R.id.radioNao:
                precisaDatashow = "Não";
                break;
            case R.id.radioSim:
                precisaDatashow = "Sim";
                break;
            case R.id.radioTalvez:
                precisaDatashow = "Talvez";
                break;
        }
        mensagem+="Vai precisar de Datashow: "+precisaDatashow+" \n";
        mensagem+="Configurações desejadas\n";
        if(androidC.isChecked())
            mensagem+=androidC.getText()+"\n";
        if(java.isChecked())
            mensagem+=java.getText()+"\n";
        if(linux.isChecked())
            mensagem+=linux.getText()+"\n";
        if(windows.isChecked())
            mensagem+=windows.getText()+"\n";
        mensagem+="Reserva prioritária?\t"+prioridade.getText()+"\n";
        mensagem+="Observações:\n";
        mensagem+=observacoesE.getText()+"\n";
        mensagem+="========================================================\n";
        Intent intentEmail = new Intent();
        intentEmail.setAction(Intent.ACTION_SEND);
        intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Reserva de laboratório");
        intentEmail.putExtra(Intent.EXTRA_EMAIL,new String[]{"arlaass@gmail.com"});
        intentEmail.putExtra(Intent.EXTRA_TEXT,mensagem);
        intentEmail.setType("plain/text");
        startActivity(Intent.createChooser(intentEmail,"Enviar mensagem"));
    }
}
