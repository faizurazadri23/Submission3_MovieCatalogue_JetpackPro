package com.faizurazadri.moviecatalogue.ui.favorite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.faizurazadri.moviecatalogue.BuildConfig;
import com.faizurazadri.moviecatalogue.R;
import com.faizurazadri.moviecatalogue.data.source.local.entity.MoviesEntity;
import com.faizurazadri.moviecatalogue.databinding.ItemsMoviesBinding;
import com.faizurazadri.moviecatalogue.ui.detail.DetailMovieActivity;

public class FavoriteMovieAdapter extends PagedListAdapter<MoviesEntity, FavoriteMovieAdapter.MoviewViewHolder> {


    FavoriteMovieAdapter() {
        super(DIFF_CALLBACK);
    }

    private static DiffUtil.ItemCallback<MoviesEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MoviesEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull MoviesEntity oldItem, @NonNull MoviesEntity newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull MoviesEntity oldItem, @NonNull MoviesEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };


    @NonNull
    @Override
    public MoviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsMoviesBinding binding = ItemsMoviesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MoviewViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviewViewHolder holder, int position) {
        MoviesEntity movies = getItem(position);
        if (movies != null) {
            holder.bind(movies);
        }
    }

    class MoviewViewHolder extends RecyclerView.ViewHolder {
        final ItemsMoviesBinding binding;

        MoviewViewHolder(ItemsMoviesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(MoviesEntity movies) {
            binding.tvItemTitle.setText(movies.getTitle());
            binding.tvItemRelase.setText(movies.getRelease_date());
            binding.tvItemVoteAverage.setText(movies.getVote_average());

            itemView.setOnClickListener(v -> {
                Intent detailMovie = new Intent(itemView.getContext(), DetailMovieActivity.class);
                detailMovie.putExtra(DetailMovieActivity.EXTRA_MOVIE, movies.getId());
                itemView.getContext().startActivity(detailMovie);
            });

            Glide.with(itemView.getContext())
                    .load(BuildConfig.SERVER_IMG + movies.getPoster_path())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(binding.imgPoster);
        }
    }
}
