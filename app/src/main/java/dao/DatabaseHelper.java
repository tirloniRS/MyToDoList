package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alexsandro-rs on 25/03/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String BANCO_DADOS = "tasks";
    private static final int VERSAO = 2;
    public DatabaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tabela de usuários
        db.execSQL("create table usuarios(_id integer primary key autoincrement," +
                "nome text not null, login text not null, senha text not null, created_at text)");

        //Tabela de tarefas
        db.execSQL("create table tarefas(_id integer primary key autoincrement," +
                "tarefa text not null, dt_criacao text, dt_completado text)");

        //criar um usuario para popular o banco
        db.execSQL("insert into usuarios(nome, login, senha) values('Admin', 'admin', '123') ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static class Usuarios{
        public static final String TABELA = "usuarios";
        public static final String _ID = "_id";
        public static final String NOME = "nome";
        public static final String LOGIN = "login";
        public static final String SENHA = "senha";
        public static final String CREATED_AT = "created_at";

        public static final String[] COLUNAS = new String[]{
                _ID, NOME, LOGIN, SENHA, CREATED_AT
        };
    }

    public static class Tarefas{
        public static final String TABELA = "tarefas";
        public static final String _ID = "_id";
        public static final String TAREFA = "tarefa";
        public static final String DT_CRIACAO = "dt_criacao";
        public static final String DT_COMPLETADO = "dt_completado";

        public static final String[] COLUNAS = new String[]{
                _ID, TAREFA, DT_CRIACAO, DT_COMPLETADO
        };
    }
}
