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
import com.faizurazadri.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.faizurazadri.moviecatalogue.databinding.ActivityDetailTvShowBinding;
import com.faizurazadri.moviecatalogue.databinding.ContentDetailtvshowBinding;
import com.faizurazadri.moviecatalogue.viewmodel.ViewModelFactoryTvShow;

public class DetailTvShowActivity extends AppCompatActivity {

    ContentDetailtvshowBinding ContentDetailtvshowBinding;
    public static final String EXTRA_TVSHOW = "extra_tvshow";
    private Menu menu;
    DetailTvShowViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailTvShowBinding activityDetailTvShowBinding = ActivityDetailTvShowBinding.inflate(getLayoutInflater());
        ContentDetailtvshowBinding = activityDetailTvShowBinding.detailContentTvshow;
        setContentView(activityDetailTvShowBinding.getRoot());


        viewModel = obtainViewModel(this);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String entityId = bundle.getString(EXTRA_TVSHOW);

            if (entityId != null) {
                viewModel.setId(entityId);
            } else {
                finish();
            }
        }

        viewModel.detail.observe(this, tvShowEntity -> {
            if (tvShowEntity != null) {
                switch (tvShowEntity.status) {
                    case LOADING:
                        ContentDetailtvshowBinding.progressDetailTvShow.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        ContentDetailtvshowBinding.progressDetailTvShow.setVisibility(View.GONE);
                        pupulateTvShow(tvShowEntity.data);
                        break;
                    case ERROR:
                        ContentDetailtvshowBinding.progressDetailTvShow.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_message), Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    private void pupulateTvShow(TvShowEntity tvShowEntity) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detail " + tvShowEntity.getOriginal_name());
        }

        ContentDetailtvshowBinding.tvItemTitle.setText(tvShowEntity.getName());
        ContentDetailtvshowBinding.tvItemRelase.setText(tvShowEntity.getFirst_air_date());
        ContentDetailtvshowBinding.tvLanguage.setText(tvShowEntity.getOriginal_language());
        ContentDetailtvshowBinding.tvItemShowstar.setText(tvShowEntity.getVote_average());
        ContentDetailtvshowBinding.tvPopularity.setText(tvShowEntity.getPopularity());
        ContentDetailtvshowBinding.tvDescription.setText(tvShowEntity.getOverview());

        Glide.with(this)
                .load(BuildConfig.SERVER_IMG + tvShowEntity.getBackdrop_path())
                .transform(new RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(ContentDetailtvshowBinding.imgPoster);
    }

    @NonNull
    private static DetailTvShowViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactoryTvShow viewModelFactoryTvShow = ViewModelFactoryTvShow.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, viewModelFactoryTvShow).get(DetailTvShowViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        this.menu = menu;

        viewModel.detail.observe(this, tvShowEntityResource -> {
            if (tvShowEntityResource != null) {
                switch (tvShowEntityResource.status) {
                    case LOADING:
                        ContentDetailtvshowBinding.progressDetailTvShow.setVisibility(View.VISIBLE);
                        break;

                    case SUCCESS:
                        if (tvShowEntityResource.data != null) {
                            ContentDetailtvshowBinding.progressDetailTvShow.setVisibility(View.GONE);
                            boolean state = tvShowEntityResource.data.isFavorite();
                            setFavoriteState(state);
                        }
                        break;

                    case ERROR:
                        ContentDetailtvshowBinding.progressDetailTvShow.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_message), Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_favorite) {
            viewModel.setFavorite();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ContentDetailtvshowBinding = null;
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