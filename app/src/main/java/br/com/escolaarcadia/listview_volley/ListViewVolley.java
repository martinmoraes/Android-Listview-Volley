package br.com.escolaarcadia.listview_volley;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.escolaarcadia.listview_volley.adaptador.CustomListAdaptador;
import br.com.escolaarcadia.listview_volley.app.AppControle;
import br.com.escolaarcadia.listview_volley.modelo.Filme;

public class ListViewVolley extends Activity {
    // Log tag
    private static final String TAG = ListViewVolley.class.getSimpleName();

    // Movies json url
    private static final String url = "http://api.androidhive.info/json/movies.json";
    private ProgressDialog pDialog;
    private List<Filme> lista_de_filmes = new ArrayList<Filme>();
    private ListView listView;
    private CustomListAdaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_volley);

        listView = (ListView) findViewById(R.id.list);
        adaptador = new CustomListAdaptador(this, lista_de_filmes);
        listView.setAdapter(adaptador);

        pDialog = new ProgressDialog(this);
        // Mostra o progressBar depois da chamada
        pDialog.setMessage("Loading...");
        pDialog.show();


        // Criando um objeto de requisição vollei
        JsonArrayRequest filmesSolicitados = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Percorrendo o JSon
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Filme filme = new Filme();
                                filme.setTitulo(obj.getString("title"));
                                filme.setImagemUrl(obj.getString("image"));

                                filme.setNota(((Number) obj.get("rating")).doubleValue());
                                filme.setAno(obj.getInt("releaseYear"));

                                // Trata os generos do filme
                                JSONArray genreArry = obj.getJSONArray("genre");
                                ArrayList<String> genre = new ArrayList<String>();
                                for (int j = 0; j < genreArry.length(); j++) {
                                    genre.add((String) genreArry.get(j));
                                }
                                filme.setGenero(genre);

                                // Adiciona filme na lista
                                lista_de_filmes.add(filme);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // Notifica o adaptador de novos dados e randeriza o listView
                        adaptador.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adiciona mais uma requisição na fila
        AppControle.getInstance().addToRequestQueue(filmesSolicitados);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }


}
