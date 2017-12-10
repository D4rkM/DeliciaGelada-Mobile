package br.com.deliciagelada.deliciagelada.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.com.deliciagelada.deliciagelada.Database.DatabaseHelper;

/**
 * Created by Magno on 10/12/2017.
 */

public class AvaliacaoDAO {

    private static AvaliacaoDAO instance;

    public static AvaliacaoDAO getInstance(){
        if(instance == null){
            instance = new AvaliacaoDAO();
        }
        return instance;
    }

    public boolean inserirAvaliacao(Context context, int idProduto, double avaliacao){
        SQLiteDatabase db = new DatabaseHelper(context).getWritableDatabase();

        boolean verificador = true;

        try{
            ContentValues valores = new ContentValues();

            valores.put("idProduto", idProduto);
            valores.put("avaliacao", avaliacao);

            db.insert("tbl_avaliacao", null, valores);

        }catch (Exception e){
            verificador = false;
        }

        db.close();
        return verificador;
    }

}
