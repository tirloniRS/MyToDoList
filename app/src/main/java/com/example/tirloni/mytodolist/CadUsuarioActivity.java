package com.example.tirloni.mytodolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import dao.UsuarioDAO;
import model.Usuario;
import util.Mensagem;


public class CadUsuarioActivity extends ActionBarActivity {

    private EditText edtNome, edtLogin, edtSenha;
    private UsuarioDAO usuarioDAO;
    private Usuario usuario;
    private int idusuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_usuario);

        usuarioDAO = new UsuarioDAO(this);

        edtNome  = (EditText)findViewById(R.id.usuario_edtNome);
        edtLogin = (EditText)findViewById(R.id.usuario_edtLogin);
        edtSenha = (EditText)findViewById(R.id.usuario_edtSenha);
    }

    @Override
    protected void onDestroy() {
        usuarioDAO.fechar();
        super.onDestroy();
    }

    private void cadastrar(){
        boolean validacao = true;

        String nome  = edtNome.getText().toString();
        String login = edtLogin.getText().toString();
        String senha = edtSenha.getText().toString();

        if (nome == null || nome.equals("")){
            validacao = false;
            edtNome.setError(getString(R.string.campo_obrigatorio));

        }
        if (login == null || login.equals("")){
            validacao = false;
            edtLogin.setError(getString(R.string.campo_obrigatorio));

        }
        if (senha == null || senha.equals("")){
            validacao = false;
            edtSenha.setError(getString(R.string.campo_obrigatorio));

        }

        if (validacao = true){
            usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setLogin(login);
            usuario.setSenha(senha);
            //se for atualizar
            if (idusuario > 0){
                usuario.set_id(idusuario);
            }

            long resultado = usuarioDAO.salvarUsuario(usuario);
            //-1 significa que salvou sem erro
            if (resultado != -1){
                if (idusuario > 0){
                    Mensagem.Msg(this, getString(R.string.mensagem_atualizar));
                }else {
                    Mensagem.Msg(this,getString(R.string.mensagem_sucesso));
                }
                finish();
                startActivity(new Intent(this, MainActivity.class));
            }else{
                Mensagem.Msg(this,getString(R.string.mensagem_erro));
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cadastros, menu);
        //exibe o excluir somente se for edição
        if (idusuario > 0){
            menu.findItem(R.id.action_menu_excluir).setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_menu_salvar:
                this.cadastrar();
                break;
            case R.id.action_menu_sair:
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
