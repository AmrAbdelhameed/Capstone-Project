package com.example.amr.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class FavouriteActivity extends AppCompatActivity implements TabletMoodFavourite {
    boolean mIsTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Favourite Books");

        FavouriteFragment favouriteFragment = new FavouriteFragment();
        favouriteFragment.setNameListener(this);
        getSupportFragmentManager().beginTransaction().add(R.id.flMain2, favouriteFragment, "").commit();
        if (null != findViewById(R.id.flDetails2)) {
            mIsTwoPane = true;
        }
    }

    @Override
    public void setSelectedName(String ID, String Title, String SubTitle, String Year, Double Rate, String Overview, String Image1, String Image2) {

        if (!mIsTwoPane) {
            Intent i = new Intent(this, DetailsActivity.class);

            Bundle b = new Bundle();
            b.putBoolean("Favourite", true);
            b.putString("ID", ID);
            b.putBoolean("Toolbar", true);
            b.putString("Title", Title);
            b.putString("SubTitle", SubTitle);
            b.putString("Year", Year);
            b.putDouble("Rate", Rate);
            b.putString("Overview", Overview);
            b.putString("Image1", Image1);
            b.putString("Image2", Image2);
            b.putBoolean("WithDelete", false);
            i.putExtras(b);

            startActivityForResult(i, 1);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        } else {
            DetailsFragment mDetailsFragment = new DetailsFragment();

            Bundle b = new Bundle();
            b.putBoolean("Favourite", true);
            b.putString("ID", ID);
            b.putBoolean("Toolbar", false);
            b.putString("Title", Title);
            b.putString("SubTitle", SubTitle);
            b.putString("Year", Year);
            b.putDouble("Rate", Rate);
            b.putString("Overview", Overview);
            b.putString("Image1", Image1);
            b.putString("Image2", Image2);
            mDetailsFragment.setArguments(b);

            getSupportFragmentManager().beginTransaction().replace(R.id.flDetails2, mDetailsFragment, "").commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                finish();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}