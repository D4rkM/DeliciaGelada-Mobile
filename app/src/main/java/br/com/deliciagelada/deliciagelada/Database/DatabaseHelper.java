package br.com.deliciagelada.deliciagelada.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Magno on 10/12/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //Estrutura de criação do banco de dados no android
    private static final String NOME_BANCO = "avaliacao_suco.db";
    private static final int VERSAO = 1;

    public DatabaseHelper(Context context){
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //Criar o Banco e utilização (CREATE TABLE, etc...)
        String sql = "CREATE TABLE tbl_avaliacao(" +
                "_id INTEGER PRIMARY KEY," +
                " idProduto INT," +
                " avaliacao REAL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//Atualizando o Banco
        String sql = "INSERT INTO tbl_avaliacao" ;
    }
}
