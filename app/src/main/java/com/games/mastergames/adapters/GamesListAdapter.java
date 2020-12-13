package com.games.mastergames.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.games.mastergames.databinding.GameListBinding;
import com.games.mastergames.model.Game;
import com.games.mastergames.viewModels.GameViewModel;

import java.util.List;

public class GamesListAdapter extends RecyclerView.Adapter<GamesListAdapter.GamesViewHolder> {

    private List<Game> gamesList;
    private GameViewModel gameViewModel;
    Context context;

    public GamesListAdapter(Context context, List<Game> gamesList, GameViewModel gameViewModel) {
        this.context = context;
        this.gamesList = gamesList;
        this.gameViewModel = gameViewModel;
    }

    public void setGameList(List<Game> games) {
        this.gamesList = games;
    }

    @NonNull
    @Override
    public GamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater  = LayoutInflater.from(parent.getContext());
        final GameListBinding binding = GameListBinding.inflate(layoutInflater, parent, false);
        return new GamesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GamesViewHolder holder, int position) {
        Game game = gamesList.get(position);
        holder.setBinding(game, gameViewModel);
    }

    @Override
    public int getItemCount() {
        return gamesList.size();
    }

    class GamesViewHolder extends RecyclerView.ViewHolder {
        GameListBinding binding;

        public GamesViewHolder(@NonNull GameListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void setBinding(Game game, GameViewModel viewModel) {
            binding.setGame(game);
            Glide.with(context).asBitmap()
                    .load(game.getImageBackground())
                    .thumbnail(0.5f)
                    .into(binding.imageItem);
            binding.setViewModel(viewModel);
            binding.executePendingBindings();
        }
    }
}
