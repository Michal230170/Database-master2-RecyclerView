package com.example.studia.projekt.ui.field;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.animation.AnimationUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.widget.TextView;

import com.example.studia.projekt.R;
import com.example.studia.projekt.db.AppDatabase;
import com.example.studia.projekt.db.model.FieldRecord;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        AppDatabase database = AppDatabase.getInstance(getApplicationContext());

        int fieldId = getIntent().getExtras().getInt("fieldId");

        final TextView substanceTextView = findViewById(R.id.textViewSubstance);
        final TextView dateTextView = findViewById(R.id.textViewDate);
        final TextView plantTextView = findViewById(R.id.textViewPlant);
        final TextView areaTextView = findViewById(R.id.textViewArea);
        final TextView usageReasonTextView = findViewById(R.id.textViewUsageReason);
        final TextView doseTextView = findViewById(R.id.textViewDose);


        database.fieldDao().findFieldRecordByFieldId(fieldId).observe(this,
                new Observer<FieldRecord>() {
                    @Override
                    public void onChanged(@Nullable FieldRecord record) {

                        areaTextView.setText(record.getArea());
                        plantTextView.setText(record.getPlant());
                        substanceTextView.setText(record.getSubstance());
                        dateTextView.setText(record.getDate());
                        usageReasonTextView.setText(record.getUsageReason());
                        doseTextView.setText(record.getDose());
                    }
                });
    }
}
