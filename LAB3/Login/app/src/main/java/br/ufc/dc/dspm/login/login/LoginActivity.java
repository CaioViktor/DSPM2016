package br.ufc.dc.dspm.login.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText usuario,senha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usuario = (EditText)findViewById(R.id.usuario);
        senha = (EditText)findViewById(R.id.senha);
        setContentView(R.layout.activity_login);
    }
    public void ok(View v){
        Intent intent = new Intent();
        intent.setAction("br.ufc.dc.dspm.login.action.LOGIN");
        intent.addCategory("br.ufc.dc.dspm.login.category.LOGIN");
        intent.setComponent(null);
        String usu = (String)usuario.getText().toString();
        String sen = (String)senha.getText().toString();
        Log.i("CAT", usu + "\n" + sen);
        intent.putExtra("usuario", usu);
        intent.putExtra("senha",sen);
        startActivity(intent);
    }
    public void cancel(View v){
        finish();
    }
}
