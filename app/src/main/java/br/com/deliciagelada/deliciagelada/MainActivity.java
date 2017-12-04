package br.com.deliciagelada.deliciagelada;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.com.deliciagelada.deliciagelada.API.Http;

public class MainActivity extends AppCompatActivity {

    ProdutoAdapter produtoAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listaId);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this, Detalhes.class);
                Produto p = produtoAdapter.getItem(i);
                intent.putExtra("nome", p.getNome());
                intent.putExtra("descricao", p.getDescricao());
                intent.putExtra("preco", p.getPreco().toString());
                intent.putExtra("foto", p.getFoto());
                startActivity(intent);


            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("tel:" + "40028922");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });


        produtoAdapter = new ProdutoAdapter(this, new ArrayList<Produto>());

        listView.setAdapter(produtoAdapter);

        //------------------------------------------------------------------------------------------------------
        //Preencher Lista
        //Tarefa assincrona (conexão json_

        new AsyncTask<Void, Void, Void>(){
            //Inicia a lista que vai receber os itens
            ArrayList<Produto> lstProdutos = new ArrayList<Produto>();

            @Override
            protected Void doInBackground(Void... voids) {
                //Usando ip local do celular -- Não funciona quando for usar em servidores de hospedagem
                String retornoJson = Http.get("http://10.0.2.2/inf3m/TurmaB/PDG/selecionar.php"); //==== OUTRA SALA
//                String retornoJson = Http.get("http://10.107.144.29/inf3m/TurmaB/PDG/selecionar.php");

                try{

                    JSONArray jsonArray = new JSONArray(retornoJson);

                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject item = jsonArray.getJSONObject(i);
                        Produto p = Produto.create(item.getString("nome"),item.getString("descricao"),item.getDouble("preco"),item.getString("foto"));
                        lstProdutos.add(p);
                    }

                }catch(JSONException e){
                    Log.e("ERRO", e.getMessage());
                }
                Log.d("LISTA", lstProdutos.size()+"");

                return null;
            }
            @Override
            //Executa ações para a tela após executar ações em segundo plano
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                produtoAdapter.addAll(lstProdutos);
            }

        }.execute();//Executa tarefa assincrona
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.sobre) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
