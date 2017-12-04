package br.com.deliciagelada.deliciagelada;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Detalhes extends AppCompatActivity {

    TextView nome, preco, descricao;
    ImageView foto;
    RatingBar ratingBar;

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
        Picasso.with(this)
//                .load("http://10.0.2.2/API/"+item.getFoto())
                .load("http://10.0.2.2/inf3m/TurmaB/PDG/cms/"+intent.getStringExtra("foto"))
                .into(foto);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

            }
        });

        FloatingActionButton fabDetail = (FloatingActionButton) findViewById(R.id.fabD);
        fabDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("tel:" + "40028922");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });
    }
}
