package br.com.escolaarcadia.listview_volley.adaptador;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.escolaarcadia.listview_volley.R;
import br.com.escolaarcadia.listview_volley.app.AppControle;
import br.com.escolaarcadia.listview_volley.modelo.Filme;
import br.com.escolaarcadia.listview_volley.util.LruImagemCache;

/**
 * Created by Martin on 25/08/2015.
 */
public class CustomListAdaptador extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Filme> filmeItems;
    private int x = 0;


    public CustomListAdaptador(Activity activity, List<Filme> filmeItems) {
        this.activity = activity;
        this.filmeItems = filmeItems;
    }


    @Override
    public int getCount() {
        return filmeItems.size();
    }

    @Override
    public Object getItem(int location) {
        return filmeItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("getVIEW", x++ + "Posição:" +position);

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.lista_linha, null);

            TextView tvTitulo = (TextView) convertView.findViewById(R.id.titulo);
            TextView tvNota = (TextView) convertView.findViewById(R.id.nota);
            TextView tvGenero = (TextView) convertView.findViewById(R.id.genero);
            TextView tvAno = (TextView) convertView.findViewById(R.id.ano);

            // Pega o filme para uma linha
            Filme m = filmeItems.get(position);

            // Pega a imagem pelo carregadorImagem
            //NetworkImageView imagemNET = (NetworkImageView) convertView.findViewById(R.id.imagem);
            //imagemNET.setImageUrl(m.getImagemUrl(), AppControle.getInstance().getImageLoader());

            Picasso.with(activity.getApplicationContext())
                    .load(m.getImagemUrl())
                    .into((ImageView) convertView.findViewById(R.id.imagem));

            // tvTitulo
            tvTitulo.setText(m.getTitulo());

            // rating
            tvNota.setText("Nota: " + String.valueOf(m.getNota()));

            // genre
            String generoStr = "";
            for (String str : m.getGenero()) {
                generoStr += str + ", ";
            }
            generoStr = generoStr.length() > 0 ? generoStr.substring(0,
                    generoStr.length() - 2) : generoStr;
            tvGenero.setText(generoStr);

            // release year
            tvAno.setText(String.valueOf(m.getAno()));

        return convertView;
    }

}