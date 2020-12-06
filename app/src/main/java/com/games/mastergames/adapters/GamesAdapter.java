//package com.games.mastergames.adapters;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.games.mastergames.model.Game;
//
//import java.util.List;
//
//public class GamesAdapter extends RecyclerView.Adapter<com.games.mastergames.adapters.GamesAdapter.GameViewHolder> {
//
//        private List<Game> games;
//
//        public GamesAdapter(List<Game> games) {
//            this.games = games;
//        }
//
//        public GamesAdapter.GamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//            LayoutGamesBinding itemBinding = LayoutGameBinding.inflate(layoutInflater, parent, false);
//            return new com.games.mastergames.adapters.CategoriesAdapter.CategoryViewHolder(itemBinding);
//        }
//
//        @Override
//        public int getItemCount() {
//            return games.size();
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull com.games.mastergames.adapters.GamesAdapter.GameViewHolder holder, int position) {
//            holder.bind(games.get(position));
//        }
//
//        public static class GameViewHolder extends RecyclerView.ViewHolder {
//            LayoutGamesBinding binding;
//            public View view;
//            public GameViewHolder(LayoutGamesBinding binding) {
//                super(binding.getRoot());
//                this.binding = binding;
//            }
//            public void bind(Game game) {
//                binding.setGame(game);
//                binding.executePendingBindings();
//            }
//        }
//
//
//}
