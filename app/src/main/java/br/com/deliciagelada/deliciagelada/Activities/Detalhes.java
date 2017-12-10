package br.com.deliciagelada.deliciagelada.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import br.com.deliciagelada.deliciagelada.API.Http;
import br.com.deliciagelada.deliciagelada.DAO.AvaliacaoDAO;
import br.com.deliciagelada.deliciagelada.R;

public class Detalhes extends AppCompatActivity {

    AvaliacaoDAO dao = AvaliacaoDAO.getInstance();
    TextView nome, preco, descricao;
    ImageView foto;
    RatingBar ratingBar;
    String tel;
    Integer idProduto;
    Intent intent;
    Double avaliacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nome = (TextView) findViewById(R.id.nomeDetalhe);
        preco = (TextView) findViewById(R.id.precoDetalhe);
        descricao = (TextView) findViewById(R.id.descDetalhe);
        foto = (ImageView) findViewById(R.id.imgProdDetail);
        ratingBar = (RatingBar) findViewById(R.id.ratingBarId);

        intent = getIntent();
        nome.setText(intent.getStringExtra("nome"));
        descricao.setText(intent.getStringExtra("descricao"));
        preco.setText(intent.getStringExtra("preco"));
        tel = intent.getStringExtra("telefone");
        idProduto = intent.getIntExtra("codigo",0);

        verEstrela();

        // Inicia o numero de estrelas de acordo com o que estiver salvo
//        ratingBar.setBackgroundResource(R.color.preRate);

        Picasso.with(this)
//                .load("http://10.0.2.2/API/"+item.getFoto())
//                .load("http://10.0.2.2/inf3m/TurmaB/PDG/cms/"+intent.getStringExtra("foto"))
                .load("http://192.168.0.109/inf3m/TurmaB/PDG/cms/"+intent.getStringExtra("foto"))
                .into(foto);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                avaliacao = Double.valueOf(ratingBar.getRating());
                //Toast.makeText(getApplicationContext(), ""+avaliacao, Toast.LENGTH_SHORT).show();

                if(dao.inserirAvaliacao(getApplicationContext(), idProduto, avaliacao)){
                    ratingBar.setEnabled(false);
                    inserirAvaliacao();
                    Toast.makeText(Detalhes.this, "Avaliação salva", Toast.LENGTH_SHORT).show();
                }
            }
        });


        FloatingActionButton fabDetail = (FloatingActionButton) findViewById(R.id.fabD);
        fabDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("tel:" + tel);
                Intent intent = new Intent(Intent.ACTION_CALL, uri);
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    ActivityCompat.requestPermissions(Detalhes.this, new String[]{Manifest.permission.CALL_PHONE},10);

                    return;
                }else{
                    try{
                        startActivity(intent);
                    }catch (android.content.ActivityNotFoundException ex){
                        Toast.makeText(Detalhes.this, "Não foi encontrado.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    //Verifica se o usuario já votou nesse produto
    private void verEstrela(){
        Double av = dao.obterPorId(this, idProduto);

        if (av > 0){
            ratingBar.setRating(Float.parseFloat(String.valueOf(av)));
            ratingBar.setEnabled(false);
        }else{
            ratingBar.setRating((float) intent.getDoubleExtra("estrela", 0.0));
            ratingBar.setEnabled(true);
        }
    }

    private void inserirAvaliacao(){

        final String url = "http://192.168.0.109/inf3m/TurmaB/PDG/inserirAvaliacao.php";

        final HashMap<String, String> valores = new HashMap<>();
        valores.put("avaliacao", avaliacao.toString());
        valores.put("idProduto", String.valueOf(idProduto));

        new AsyncTask<Void, Void, Void>(){

            Boolean sucesso = false;
            String mensagem = "";

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... voids) {

                String resultado = Http.post(url, valores);

                try{
                    JSONObject jsonObject = new JSONObject(resultado);
                    sucesso = jsonObject.getBoolean("sucesso");
                    mensagem = jsonObject.getString("mensagem");
                }catch (JSONException e){
                    e.printStackTrace();
                    sucesso = false;
                    mensagem = "Erro ao inserir";
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if(sucesso){
                    Toast.makeText(Detalhes.this, "Inserido com sucesso.", Toast.LENGTH_SHORT).show();
                }

            }
        }.execute();

    }

}
