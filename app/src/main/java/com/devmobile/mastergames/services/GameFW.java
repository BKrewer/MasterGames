package com.devmobile.mastergames.services;

import com.devmobile.mastergames.model.Game;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GameFW {
        private static com.devmobile.mastergames.services.GameFW instancia;
        private Map<Integer, Game> games;
        private GameFW() {
            this.games = new HashMap();
        }
        public static com.devmobile.mastergames.services.GameFW getInstance() {
            if (instancia == null) {
                instancia = new com.devmobile.mastergames.services.GameFW();
            }
            return instancia;
        }
        public void addGame(Game game) {
            games.put(game.getId(),game);

        }
        public Collection<Game> getGames() {
            return games.values();
        }
}
