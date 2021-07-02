package com.faizurazadri.moviecatalogue.ui.detail;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.faizurazadri.moviecatalogue.BuildConfig;
import com.faizurazadri.moviecatalogue.R;
import com.faizurazadri.moviecatalogue.data.source.local.entity.MoviesEntity;
import com.faizurazadri.moviecatalogue.databinding.ActivityDetailMovieBinding;
import com.faizurazadri.moviecatalogue.databinding.ContentDetailMovieBinding;
import com.faizurazadri.moviecatalogue.viewmodel.ViewModelFactoryMovie;

public class DetailMovieActivity extends AppCompatActivity {

    ContentDetailMovieBinding contentDetailMovieBinding;
    public static final String EXTRA_MOVIE = "extra_movie";
    DetailMovieViewModel viewModel;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailMovieBinding activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(getLayoutInflater());
        contentDetailMovieBinding = activityDetailMovieBinding.detailContentMovie;

        setContentView(activityDetailMovieBinding.getRoot());


        viewModel = obtainViewModel(this);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String entityId = bundle.getString(EXTRA_MOVIE);

            if (entityId != null) {
                viewModel.setId(entityId);
            } else {
                finish();
            }
        }


        viewModel.detail.observe(this, moviesEntity -> {
            if (moviesEntity != null) {

                switch (moviesEntity.status) {
                    case LOADING:
                        contentDetailMovieBinding.progressDetailMovies.setVisibility(View.VISIBLE);
                        break;

                    case SUCCESS:
                        if (moviesEntity.data != null) {
                            contentDetailMovieBinding.progressDetailMovies.setVisibility(View.GONE);
                            populateMovie(moviesEntity.data);
                        }

                        break;

                    case ERROR:
                        contentDetailMovieBinding.progressDetailMovies.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_message), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void populateMovie(MoviesEntity moviesEntity) {

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detail " + moviesEntity.getTitle());
        }

        contentDetailMovieBinding.tvItemTitle.setText(moviesEntity.getTitle());
        contentDetailMovieBinding.tvItemImdb.setText(moviesEntity.getVote_average());
        contentDetailMovieBinding.tvCountry.setText(moviesEntity.getOriginal_language());
        contentDetailMovieBinding.tvItemRelase.setText(moviesEntity.getRelease_date());
        contentDetailMovieBinding.tvDescription.setText(moviesEntity.getOverview());
        contentDetailMovieBinding.tvPopularity.setText(moviesEntity.getPopularity());
        contentDetailMovieBinding.tvVoteCount.setText(moviesEntity.getVote_count());
        Glide.with(this)
                .load(BuildConfig.SERVER_IMG + moviesEntity.getBackdrop_path())
                .transform(new RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(contentDetailMovieBinding.imgPoster);
    }

    @NonNull
    private static DetailMovieViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactoryMovie viewModelFactoryMovie = ViewModelFactoryMovie.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, viewModelFactoryMovie).get(DetailMovieViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        this.menu = menu;

        viewModel.detail.observe(this, moviesEntity -> {
            if (moviesEntity != null) {

                switch (moviesEntity.status) {
                    case LOADING:
                        contentDetailMovieBinding.progressDetailMovies.setVisibility(View.VISIBLE);
                        break;

                    case SUCCESS:
                        if (moviesEntity.data != null) {
                            contentDetailMovieBinding.progressDetailMovies.setVisibility(View.GONE);
                            boolean state = moviesEntity.data.isFavorite();
                            setFavoriteState(state);
                        }

                        break;

                    case ERROR:
                        contentDetailMovieBinding.progressDetailMovies.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_message), Toast.LENGTH_LONG).show();
                }

            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_favorite) {
            viewModel.setFavorite();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contentDetailMovieBinding = null;
    }

    private void setFavoriteState(boolean state) {
        if (menu == null) return;

        MenuItem menuItem = menu.findItem(R.id.add_favorite);

        if (state) {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite));
        } else {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border));
        }
    }
}