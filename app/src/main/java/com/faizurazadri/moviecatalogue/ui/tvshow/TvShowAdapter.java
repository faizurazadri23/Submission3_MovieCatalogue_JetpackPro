package com.faizurazadri.moviecatalogue.ui.tvshow;

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
import com.faizurazadri.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.faizurazadri.moviecatalogue.databinding.ItemTvshowBinding;
import com.faizurazadri.moviecatalogue.ui.detail.DetailTvShowActivity;

public class TvShowAdapter extends PagedListAdapter<TvShowEntity, TvShowAdapter.TvShowHolder> {

    TvShowAdapter() {
        super(DIFF_CALLBACK);
    }

    private static DiffUtil.ItemCallback<TvShowEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<TvShowEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull TvShowEntity oldItem, @NonNull TvShowEntity newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull TvShowEntity oldItem, @NonNull TvShowEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };

    @NonNull
    @Override
    public TvShowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTvshowBinding binding = ItemTvshowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TvShowHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowHolder holder, int position) {
        TvShowEntity tvShowEntity = getItem(position);
        if (tvShowEntity != null) {
            holder.bind(tvShowEntity);
        }

    }

    static class TvShowHolder extends RecyclerView.ViewHolder {

        private final ItemTvshowBinding binding;

        TvShowHolder(ItemTvshowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(TvShowEntity tvShowEntity) {
            binding.tvItemTitle.setText(tvShowEntity.getName());
            binding.tvItemRelase.setText(tvShowEntity.getFirst_air_date());
            binding.tvItemVoteAverage.setText(tvShowEntity.getVote_average());

            itemView.setOnClickListener(v -> {
                Intent detailTvShow = new Intent(itemView.getContext(), DetailTvShowActivity.class);
                detailTvShow.putExtra(DetailTvShowActivity.EXTRA_TVSHOW, tvShowEntity.getId());
                itemView.getContext().startActivity(detailTvShow);
            });

            Glide.with(itemView.getContext())
                    .load(BuildConfig.SERVER_IMG + tvShowEntity.getPoster_path())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(binding.imgPoster);
        }
    }
}
