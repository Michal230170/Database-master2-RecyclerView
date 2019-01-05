package com.example.studia.projekt.ui.section;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.studia.projekt.R;
import com.example.studia.projekt.db.model.FieldRecord;
import com.example.studia.projekt.ui.InfoActivity;
import com.example.studia.projekt.ui.SettingsActivity;
import com.example.studia.projekt.ui.base.ViewModelProviderFactory;
import com.example.studia.projekt.ui.field.AddFieldActivity;
import com.example.studia.projekt.ui.field.DetailsActivity;
import com.example.studia.projekt.ui.field.FieldAdapter;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FieldAdapter.OnItemClickListener {

    //public ListView listView;            //przewijana lista to wszysko
    private ActionBarDrawerToggle mToggle;
   // private SimpleArrayAdapter adapter;
    private int sectionId;


    //private ActionBarDrawerToggle mToggle;
    private RecyclerView mRecyclerView;
    private FieldAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        getSupportActionBar().setTitle("Lista spisów");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = findViewById(R.id.fieldRecordsList);

        DrawerLayout mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewModelProvider.Factory factory = new ViewModelProviderFactory<>(new MainActivityViewModel(getApplication()));
        MainActivityViewModel viewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);

        //listView = findViewById(R.id.fieldRecordsList); //field records list to nazwa list view

        sectionId = getIntent().getIntExtra("sectionId", 0);

        viewModel.findFieldRecordBySectionId(sectionId).observe(this,
                new Observer<List<FieldRecord>>() {
                    @Override
                    public void onChanged(@Nullable List<FieldRecord> fieldRecords) {
                        mAdapter.swapData(fieldRecords);
                    }
                }
        );

        mAdapter = new FieldAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.setOnClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

       /* listView.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final FieldRecord item = (FieldRecord) parent.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Bundle b = new Bundle(); // odczytywanie id klikniętego rekordu ?
                b.putInt("fieldId", item.getFieldId());
                intent.putExtras(b);
                //finish();
                try {
                    startActivity(intent);
                } catch (Exception ex) {
                    String str = "aaa";
                }
            }
        }));*/



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void click(View view) {
        Intent intent = null;

        switch (view.getId()) {
            case R.id.DodawanieButton:

                intent = new Intent(this, AddFieldActivity.class);
                intent.putExtra("sectionId", sectionId);
                break;
        }

        startActivity(intent);
        //finish();
    }

    public void onclick(MenuItem item) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void onclickInfo(MenuItem item) {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, DetailsActivity.class);
        FieldRecord field = mAdapter.getField(position);
        intent.putExtra("fieldId", field.getFieldId());
        startActivity(intent);
    }
}



