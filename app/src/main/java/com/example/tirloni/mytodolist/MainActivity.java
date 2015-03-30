package com.example.tirloni.mytodolist;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_cadastro_usuarios) {
            startActivity(new Intent(this, CadUsuarioActivity.class));
        }

        if (id == R.id.action_lista_usuarios) {
            startActivity(new Intent(this, ListUsuariosActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
