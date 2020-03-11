package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.onurkaganaldemir.ktoastlib.KToast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class NoteActivity extends AppCompatActivity {
    ArrayList<Note> arrayList ;
    Note mynote;
    sql SQL ;
    int idd ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        SQL = new sql(this);

        final TextView title = findViewById(R.id.Title_note_show);
        final TextView note = findViewById(R.id.note_show);
        final TextView date = findViewById(R.id.new_time_text_view);


        Bundle b = getIntent().getExtras();

        final String Title = b.getString("title_key");
        final String mozkra = b.getString("note_key");
         idd = b.getInt("id_key");
        //final String Date = b.getString("date_key");

        title.setText(Title);
        note.setText(mozkra);
        //date.setText(Date);

        final AlertDialog.Builder Alert = new AlertDialog.Builder(this);

        final ImageButton edit = findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.update_note,null);
                final TextInputEditText editeTitle = view.findViewById(R.id.update_title);
                final TextInputEditText editNote = view.findViewById(R.id.update_note);
                editeTitle.setText(Title);
                editNote.setText(mozkra);

                Alert.setView(view);
                final AlertDialog build = Alert.create();
                build.show();

                Button edite = view.findViewById(R.id.update_btn);
                edite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String Editetitle = editeTitle.getText().toString();
                        final String Editenote = editNote.getText().toString();


                        //final String time = CurrentDate();
                        if (Editetitle.isEmpty() && Editenote.isEmpty()){
                            KToast.errorToast(NoteActivity.this, "Can't be Empty ", Gravity.BOTTOM, KToast.LENGTH_AUTO);
                            editeTitle.setError("empty");
                            editNote.setError("empty");
                        }
                        else {
                            SQL.update_data(Editetitle,idd,Editenote);
                            build.dismiss();
                        }

                    }
                });

            }
        });
        final ImageButton delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQL.delete_data(idd);
                finish();
            }
        });





    }
    public String CurrentDate() {
        Date calendar = Calendar.getInstance().getTime();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        return s.format(calendar);
    }
}
