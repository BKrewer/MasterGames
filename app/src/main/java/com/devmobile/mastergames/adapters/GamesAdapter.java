//package com.devmobile.mastergames.adapters;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.devmobile.mastergames.model.Game;
//
//import java.util.List;
//
//
//public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.GameViewHolder> {
//    private List<Game> games;
//
//    public GamesAdapter(List<Game> games) {
//        this.games = games;
//    }
//
//    public GamesAdapter.GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        LayoutGameBinding itemBinding = LayoutGameBinding.inflate(layoutInflater, parent, false);
//        return new GameViewHolder(itemBinding);
//    }
//
//    @Override
//    public int getItemCount() {
//        return games.size();
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
//        //holder.bind(games.get(position));
//    }
//
//    public static class GameViewHolder extends RecyclerView.ViewHolder {
//        LayoutGameBinding binding;
//        public View view;
//        public GameViewHolder(LayoutGameBinding binding) {
//            super(binding.getRoot());
//            this.binding = binding;
//        }
//        public void bind(Game game) {
//            binding.setGame(game);
//            binding.executePendingBindings();
//        }
//    }
//}
