package com.example.studia.projekt.ui.section;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studia.projekt.R;
import com.example.studia.projekt.db.AppDatabase;
import com.example.studia.projekt.db.model.Section;
import com.example.studia.projekt.ui.InfoActivity;
import com.example.studia.projekt.ui.SettingsActivity;
import com.example.studia.projekt.ui.base.ViewModelProviderFactory;
import com.example.studia.projekt.utils.AppExecutors;

import java.util.ArrayList;
import java.util.List;


public class SectionsActivity extends AppCompatActivity implements SectionAdapter.OnItemClickListener {

    private ActionBarDrawerToggle mToggle;
    private int userId;
    private RecyclerView mRecyclerView;
    private SectionAdapter mAdapter;
    private EditText editText;

    private List<Section> sections = new ArrayList<>();

    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fields);

        getSupportActionBar().setTitle("lista działek");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = AppDatabase.getInstance(getApplicationContext());

        mRecyclerView = findViewById(R.id.fieldRecordsList);

        DrawerLayout mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        userId = getIntent().getIntExtra("userId", 0); // odczytujemy id uzytkownika

        ViewModelProvider.Factory factory = new ViewModelProviderFactory<>(new SectionActivityViewModel(getApplication()));
        SectionActivityViewModel viewModel = ViewModelProviders.of(this, factory).get(SectionActivityViewModel.class);

        viewModel.getSectionByUserId(userId).observe(this, //getsectionbyuserid w klasie sectionViewModel
                new Observer<List<Section>>() {
                    @Override
                    public void onChanged(@Nullable List<Section> sections) {
                        mAdapter.swapData(sections);
                    }

                }
        );


        mAdapter = new SectionAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.setOnClickListener(this);
        mRecyclerView.setAdapter(mAdapter);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            database.sectionDao().delete(mAdapter.getSectionAt(viewHolder.getPosition()));
            }
        }).attachToRecyclerView(mRecyclerView);

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

                intent = new Intent(this, AddSectionActivity.class);
                intent.putExtra("userId", userId);
                break;
        }

        startActivity(intent);
        //finish();

    }

    public void onclick(MenuItem item) {
        Intent intent = new Intent(SectionsActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    public void onclickInfo(MenuItem item) {
        Intent intent = new Intent(SectionsActivity.this, InfoActivity.class);
        startActivity(intent);
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, MainActivity.class);
        Section section = mAdapter.getSection(position);
        intent.putExtra("sectionId", section.getSectionId());
        startActivity(intent);

    }

    @Override
    public void OnItemLongClick(final int adapterPosition) {


        final CharSequence[] kategorie = {"Edytuj", "Usuń"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Wybierz opcję:");
        builder.setItems(kategorie, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strDate;
                strDate = kategorie[which].toString();

                if (strDate == "Edytuj"){

                    Intent intent = new Intent(SectionsActivity.this, EditSectionActivity.class);
                    //Intent intent = new Intent(this, EditSectionActivity.class);
                    Section section = mAdapter.getSection(adapterPosition);

                    intent.putExtra("sectionId", section.getSectionId());

                    intent.putExtra("number", section.getNumber());
                    intent.putExtra("area", section.getArea());
                    intent.putExtra("name", section.getName());
                    startActivity(intent);

                }

                else if (strDate == "Usuń"){

                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            Section section = mAdapter.getSection(adapterPosition);
                            database.sectionDao().delete(mAdapter.getSection(adapterPosition));
                        }
                    });
                    //mAdapter.notifyDataSetChanged();
                   // mAdapter.notifyItemRemoved(adapterPosition);


                }


            }
        });

        builder.create();
        builder.show();

       /* AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                Section section = mAdapter.getSection(adapterPosition);
                database.sectionDao().delete(section);
            }
        });

        Toast.makeText(this, "Usunięto",
                Toast.LENGTH_SHORT).show();*/


        /*Intent intent = new Intent(this, EditSectionActivity.class);
        Section section = mAdapter.getSection(adapterPosition);
        intent.putExtra("sectionId", section.getSectionId());

        intent.putExtra("number", section.getNumber());
        intent.putExtra("area", section.getArea());
        intent.putExtra("name", section.getName());
        startActivity(intent);*/


    }



}
