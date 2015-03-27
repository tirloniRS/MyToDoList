package com.example.tirloni.mytodolist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import dao.UsuarioDAO;
import util.Mensagem;

/**
 * Created by alexsandro-rs on 26/03/2015.
 */
public class LoginActivity extends ActionBarActivity {
    private EditText edtUsuario, edtSenha;
    private CheckBox ckbConectado;
    private UsuarioDAO helper;
    private static final String MANTER_CONECTADO = "manter conectado";
    private static final String PREFERENCE_NAME = "LoginActivityPreferences";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        edtUsuario = (EditText) findViewById(R.id.edtUsuario);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        ckbConectado = (CheckBox) findViewById(R.id.ckbConectado);
        helper = new UsuarioDAO(this);

        //Arquivo de preferências
        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        boolean conectado             = preferences.getBoolean(MANTER_CONECTADO,false);

        if (conectado){
            chamarMainActivity();
        }
    }


        public void logar(View view){
        String usuario = edtUsuario.getText().toString();
        String senha = edtSenha.getText().toString();

        boolean validacao = true;

        if (usuario == null || usuario.equals("")){
            validacao = false;
            edtUsuario.setError(getString(R.string.login_valUsuario));
        }
        if (senha == null || senha.equals("")){
            validacao = false;
            edtSenha.setError(getString(R.string.login_valSenha));
        }
        //validação ok então
        if (validacao){
            if (helper.logar(usuario,senha)){
                if (ckbConectado.isChecked()){
                    SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME,MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putBoolean(MANTER_CONECTADO, true);
                    editor.commit();
                }

                chamarMainActivity();
            }
            else {
                Mensagem.Msg(this, getString(R.string.msg_loginIncorreto));
            }
        }
    }

    private void chamarMainActivity(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }



}
