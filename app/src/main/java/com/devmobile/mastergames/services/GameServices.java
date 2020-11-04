package com.devmobile.mastergames.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.io.InputStream;

public class GameServices {

    public static void carregaImagem(ImageView imageView, String urlImagem) {
        new DownloadImageTask(imageView).execute(urlImagem);
    }
    public static void buscaImagemJogo(String url, Response.Listener<Bitmap> listener) {
        // Instantiate the RequestQueue with the cache and network.

        Constantes.requestQueue.start();
        Log.v("request","vou criar o request");

        ImageRequest imgReq = new ImageRequest(url, listener, 0, 0, null,
                null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("request",error.getMessage());
            }
        });
        Constantes.requestQueue.add(imgReq);

    }
    public static void buscaJogoPorId(int id, Response.Listener<JSONObject> listener) {

        Constantes.requestQueue.start();

        String url = Constantes.URLAPI+"games/"+id;

        // Formulate the request and handle the response.
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url,
                null,listener,null);

        // Add the request to the RequestQueue.
        Constantes.requestQueue.add(jor);

        // ...

    }

    public static void buscaJogos(Response.Listener<JSONObject> listener) {
        Constantes.requestQueue.start();

        String url = Constantes.URLAPI+"games/";

        // Formulate the request and handle the response.
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url,
                null,listener,null);

        // Add the request to the RequestQueue.
        Constantes.requestQueue.add(jor);
    }

    private static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}


