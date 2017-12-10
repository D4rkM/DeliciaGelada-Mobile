package br.com.deliciagelada.deliciagelada.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
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

import br.com.deliciagelada.deliciagelada.R;

public class Detalhes extends AppCompatActivity {

    TextView nome, preco, descricao;
    ImageView foto;
    RatingBar ratingBar;
    String tel;

    //Salva a avaliação da pessoa
    private static final double STAR_RATING = 0.0;

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

        Intent intent = getIntent();
        nome.setText(intent.getStringExtra("nome"));
        descricao.setText(intent.getStringExtra("descricao"));
        preco.setText(intent.getStringExtra("preco"));
        tel = intent.getStringExtra("telefone");
        // Inicia o numero de estrelas de acordo com o que estiver salvo
//        ratingBar.setBackgroundResource(R.color.preRate);
        ratingBar.setRating((float) intent.getDoubleExtra("estrela", 0.0));
        Picasso.with(this)
//                .load("http://10.0.2.2/API/"+item.getFoto())
//                .load("http://10.0.2.2/inf3m/TurmaB/PDG/cms/"+intent.getStringExtra("foto"))
                .load("http://192.168.0.109/inf3m/TurmaB/PDG/cms/"+intent.getStringExtra("foto"))
                .into(foto);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Double e = Double.valueOf(ratingBar.getRating());
                Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_SHORT).show();
                ratingBar.setEnabled(false);

                SharedPreferences sharedPreferences = getSharedPreferences(String.valueOf(STAR_RATING), 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putFloat("star", ratingBar.getRating());
                editor.commit();
                Toast.makeText(Detalhes.this, "Avaliação salva", Toast.LENGTH_SHORT).show();
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

        recuperarVoto();
    }

    private void recuperarVoto(){
        SharedPreferences sharedPreferences = getSharedPreferences(String.valueOf(STAR_RATING), 0);
        if(sharedPreferences.contains("star")){
            Float rate = sharedPreferences.getFloat("star", 0.0f);
            ratingBar.setRating(rate);
            ratingBar.setEnabled(false);
        }else{
            ratingBar.setEnabled(true);
        }

    }

}
