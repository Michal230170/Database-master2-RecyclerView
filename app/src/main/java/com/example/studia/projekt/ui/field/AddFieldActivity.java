package com.example.studia.projekt.ui.field;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studia.projekt.R;
import com.example.studia.projekt.db.AppDatabase;
import com.example.studia.projekt.db.model.FieldRecord;
import com.example.studia.projekt.ui.section.MainActivity;
import com.example.studia.projekt.utils.AppExecutors;

import java.util.Calendar;

public class AddFieldActivity extends AppCompatActivity {

    private AppDatabase database;

    private TextView editTextDate;
    private TextView editTextArea;
    private TextView editTextPlant;
    private TextView editTextSubstance;
    private TextView editTextUsage;
    private TextView editTextDose;

    private EditText getEditTextArea;
    private EditText getEditSubstance;
    private EditText getEditUsage;
    private EditText getEditDose;
    private int sectionId;
    private EditText addPlant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodawanie);

        database = AppDatabase.getInstance(getApplicationContext());

        sectionId = getIntent().getIntExtra("sectionId", 0);

        editTextArea = findViewById(R.id.editTextNumber);
        editTextArea.setOnClickListener(new View.OnClickListener() { //budowanie dialogu
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddFieldActivity.this);

                builder.setTitle("Wpisz obszar [ha]");

                // nowy edit text do dialog
                getEditTextArea = new EditText(AddFieldActivity.this);
                getEditTextArea.setInputType(InputType.TYPE_CLASS_NUMBER); //klawiatura numeryczna
                builder.setView(getEditTextArea);

                /// przycisk pozytywny

                builder.setPositiveButton("Zatwierdź", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //String txt = getEditTextArea.getText().toString();

                        editTextArea.setText(getEditTextArea.getText().toString() + "ha");

                    }
                });

                /// przycisk negatywny

                builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.rounded_alert_dialog));
                dialog.show();
            }
        });

        editTextSubstance = findViewById(R.id.editTextName);
        editTextSubstance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddFieldActivity.this);
                builder.setTitle("Wpisz nazwę środka");

                //edit text do alertDialog

                getEditSubstance = new EditText(AddFieldActivity.this);
                builder.setView(getEditSubstance);

                builder.setPositiveButton("Zatwierdź", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //String txt = getEditTextArea.getText().toString();

                        editTextSubstance.setText(getEditSubstance.getText().toString());
                    }
                });

                /// przycisk negatywny

                builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.rounded_alert_dialog));
                dialog.show();
            }
        });

        editTextUsage = findViewById(R.id.editTextUsage);
        editTextUsage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddFieldActivity.this);
                builder.setTitle("Wpisz powód użycia środka");

                getEditUsage = new EditText(AddFieldActivity.this);
                builder.setView(getEditUsage);

                builder.setPositiveButton("Zatwierdź", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editTextUsage.setText(getEditUsage.getText().toString());
                    }
                });

                builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.rounded_alert_dialog));
                dialog.show();
            }
        });

        editTextDose = findViewById(R.id.editTextDose);
        editTextDose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddFieldActivity.this);
                builder.setTitle("Podaj dawkę [l/ha]");

                getEditDose = new EditText(AddFieldActivity.this);
                getEditTextArea.setInputType(InputType.TYPE_CLASS_NUMBER); //klawiatura numeryczna
                builder.setView(getEditDose);

                builder.setPositiveButton("Zatwierdź", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editTextDose.setText(getEditDose.getText().toString() + " l/ha");
                    }
                });

                builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.rounded_alert_dialog));
                dialog.show();
            }
        });

        /////////////// KALENDARZ DO USTAWIANIA DATY W DODAWANIU REKORDÓW

        Calendar calendar = Calendar.getInstance();
        final int Day = calendar.get(Calendar.DAY_OF_MONTH);
        final int Month = calendar.get(Calendar.MONTH);
        final int Year = calendar.get(Calendar.YEAR);

        editTextDate = findViewById(R.id.editTextDate);
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final com.wdullaer.materialdatetimepicker.date.DatePickerDialog dialog = com.wdullaer.materialdatetimepicker.date.DatePickerDialog
                        .newInstance(
                                new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(
                                            com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year,
                                            int monthOfYear, int dayOfMonth) {

                                        String strDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                        editTextDate.setText(strDate);
                                    }

                                }, Year, Month, Day);

                dialog.show(getFragmentManager(), "DatePickerDialog");



            }
        });
        ////////////////////////////KONIEC KALENDARZA
    }

    public void roslina(View view) {
        final CharSequence[] kategorie = {"pszenica", "jęczmień", "żyto", "rzepak", "kukurydza"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Wybierz roślinę");
        editTextPlant = findViewById(R.id.editTextArea);
        builder.setItems(kategorie, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strDate;
                strDate = kategorie[which].toString();
                editTextPlant.setText(strDate);
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.rounded_alert_dialog));
        dialog.show();
    }

    public void addFieldRecord(View view) {

        if (editTextDate.getText().toString().equals("") || editTextArea.getText().toString().equals("")
                || editTextPlant.getText().toString().equals("") || editTextSubstance.getText().toString()
                .equals("") ||
                editTextUsage.getText().toString().equals("") || editTextDose.getText().toString()
                .equals("")) {

            Toast.makeText(this, "Uzupełnij pola",
                    Toast.LENGTH_SHORT).show();

        } else {

            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {

                    FieldRecord fieldRecord = new FieldRecord(editTextDate.getText().toString(),
                            editTextArea.getText().toString(),
                            editTextPlant.getText().toString(), editTextSubstance.getText().toString(),
                            editTextUsage.getText().toString(),
                            editTextDose.getText().toString(), sectionId);

                    database.fieldDao().insert(fieldRecord);
                }
            });

            Toast.makeText(this, "Zapisano",
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("sectionId", sectionId);
            startActivity(intent);
            finish();

        }
    }
}
