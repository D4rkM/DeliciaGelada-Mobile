package br.com.deliciagelada.deliciagelada;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 16254861 on 29/11/2017.
 */

public class ProdutoAdapter extends ArrayAdapter<Produto> {

    public ProdutoAdapter(Context context, ArrayList<Produto> produtos){
        super(context, 0, produtos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.list_view,null);
        }

        Produto item = getItem(position);

        TextView txt_nome = v.findViewById(R.id.nomeID);
        TextView txt_preco = v.findViewById(R.id.precoID);
        ImageView img_foto = v.findViewById(R.id.imagemID);
//        RatingBar stars = v.findViewById(R.id.starID);

        Picasso.with(getContext())
//                .load("http://10.0.2.2/API/"+item.getFoto())
                .load("http://10.0.2.2/inf3m/TurmaB/PDG/cms/"+item.getFoto())
                .into(img_foto);

        txt_nome.setText(item.getNome());
        txt_preco.setText(item.getPreco()+"");


        return v;
    }
}
