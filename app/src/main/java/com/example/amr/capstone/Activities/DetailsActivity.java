package com.example.amr.capstone.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.amr.capstone.Fragments.DetailsFragment;
import com.example.amr.capstone.R;
import com.google.firebase.crash.FirebaseCrash;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FirebaseCrash.log(getString(R.string.activityCreated));

        if (savedInstanceState == null) {
            //Receive the sent Bundle
            Intent sentIntent = getIntent();
            Bundle sentBundle = sentIntent.getExtras();

            //Inflate Details Fragment & Send the Bundle to it
            DetailsFragment mDetailsFragment = new DetailsFragment();
            mDetailsFragment.setArguments(sentBundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.flDetails, mDetailsFragment, "").commit();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
