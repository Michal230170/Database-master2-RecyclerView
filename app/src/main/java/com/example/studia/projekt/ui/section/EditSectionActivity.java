package com.example.studia.projekt.ui.section;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studia.projekt.R;
import com.example.studia.projekt.db.AppDatabase;
import com.example.studia.projekt.db.model.Section;
import com.example.studia.projekt.ui.base.ViewModelProviderFactory;
import com.example.studia.projekt.utils.AppExecutors;

import java.util.ArrayList;
import java.util.List;

public class EditSectionActivity extends AppCompatActivity {

    private AppDatabase database;

    private TextView editTextNumber;
    private TextView editTextArea;
    private TextView editTextName;
    private TextView editTextId;

    private EditText getEditTextNumber;
    private EditText getEditTextArea;
    private EditText getEditTextName;
    private EditText getEditTextId;
    private int userId;

    private int sectionId;
    private String number;
    private String area;
    private String name;
    private List<Section> sections = new ArrayList<>();

    String section;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_section);



//////////////////////////////// ????
      /*  ViewModelProvider.Factory factory = new ViewModelProviderFactory<>(new SectionActivityViewModel(getApplication()));
        SectionActivityViewModel viewModel = ViewModelProviders.of(this, factory).get(SectionActivityViewModel.class);

        viewModel.getSectionById(sectionId).observe(this, //getsectionbyuserid w klasie sectionViewModel
                new Observer<List<Section>>() {
                    @Override
                    public void onChanged(@Nullable List<Section> sections) {
                        Section currentItem = sections.set(sectionId);
                    }

                }
        );*/

/////////////////////////////////////////


        database = AppDatabase.getInstance(getApplicationContext());
        userId = getIntent().getIntExtra("userId", 0);

        sectionId = getIntent().getIntExtra("sectionId", 0);
        //number = getIntent().getStringExtra("number");
        //area = getIntent().getStringExtra("area");
        //name = getIntent().getStringExtra("name");





        //editTextNumber.setText(currentItem.getNumber());
        //editTextArea.setText(currentItem.getArea());
        //editTextName.setText(currentItem.getName());




        //editTextNumber.setText(number);
       // editTextArea.setText(area);
        //editTextName.setText(name);

        editTextNumber = findViewById(R.id.editTextNumber);
        editTextNumber.setOnClickListener(new View.OnClickListener() { //budowanie dialogu
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditSectionActivity.this);

                builder.setTitle("Wpisz nr działki");

                // nowy edit text do dialog
                getEditTextNumber = new EditText(EditSectionActivity.this);
                getEditTextNumber.setInputType(InputType.TYPE_CLASS_NUMBER); //klawiatura numeryczna
                builder.setView(getEditTextNumber);

                /// przycisk pozytywny

                builder.setPositiveButton("Zatwierdź", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //String txt = getEditTextArea.getText().toString();

                        editTextNumber.setText(getEditTextNumber.getText().toString());
                    }
                });

                /// przycisk negatywny

                builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        editTextName = findViewById(R.id.editTextName);
        editTextName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditSectionActivity.this);
                builder.setTitle("Wpisz swoją nazwę");

                //edit text do alertDialog

                getEditTextName = new EditText(EditSectionActivity.this);
                builder.setView(getEditTextName);

                builder.setPositiveButton("Zatwierdź", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //String txt = getEditTextArea.getText().toString();

                        editTextName.setText(getEditTextName.getText().toString());
                    }
                });

                /// przycisk negatywny

                builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        editTextArea = findViewById(R.id.editTextArea);
        editTextArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditSectionActivity.this);
                builder.setTitle("Wpisz powierzchnię działki [ha]");

                getEditTextArea = new EditText(EditSectionActivity.this);
                builder.setView(getEditTextArea);

                builder.setPositiveButton("Zatwierdź", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editTextArea.setText(getEditTextArea.getText().toString());
                    }
                });

                builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });



/////////////// tutaj id
        /*editTextId = findViewById(R.id.editTextId);
        editTextId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditSectionActivity.this);
                builder.setTitle("Wpisz Id");

                //edit text do alertDialog

                getEditTextId = new EditText(EditSectionActivity.this);
                builder.setView(getEditTextId);

                builder.setPositiveButton("Zatwierdź", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //String txt = getEditTextArea.getText().toString();

                        editTextId.setText(getEditTextId.getText().toString());
                    }
                });

                /// przycisk negatywny

                builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });*/



    }

    public void addFieldRecord(View view) {

        if (editTextNumber.getText().toString().equals("") || editTextArea.getText().toString().equals("")
                || editTextName.getText().toString().equals("")) {

            Toast.makeText(this, "Uzupełnij pola",
                    Toast.LENGTH_SHORT).show();

        } else {

            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {

                    Section section = new Section(sectionId,
                            editTextNumber.getText().toString(),
                            editTextArea.getText().toString(),
                            editTextName.getText().toString(),
                            userId);



                    database.sectionDao().updateSection(section);
                }
            });

            Toast.makeText(this, "Zapisano",
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, SectionsActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
            finish();

        }
    }
}
