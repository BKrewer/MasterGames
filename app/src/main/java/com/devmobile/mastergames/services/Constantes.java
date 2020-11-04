/*
Classe com as constantes e objetos de uso compartilhado da aplicação
 */


package com.devmobile.mastergames.services;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.HurlStack;

import java.util.HashMap;
import java.util.Map;

public class Constantes {

    public static final String URLAPI = "https://api.rawg.io/api/";
    public static final String APIKEY = "51b3048fe0a44b4b88c274f98fb6ecb0";

    // Set up the network to use HttpURLConnection as the HTTP client.
    public static Network network = new BasicNetwork(new HurlStack());

    public static Cache cache = new Cache() {
        private Map<String, Entry> cache;
        @Override
        public Entry get(String key) {
            return cache.get(key);
        }

        @Override
        public void put(String key, Entry entry) {
            cache.put(key, entry);

        }

        @Override
        public void initialize() {
            cache = new HashMap<>();
        }

        @Override
        public void invalidate(String key, boolean fullExpire) {

        }

        @Override
        public void remove(String key) {
            cache.remove(key);

        }

        @Override
        public void clear() {
            cache.clear();
        }
    };

    public static final RequestQueue requestQueue = new RequestQueue(Constantes.cache, Constantes.network);

}
