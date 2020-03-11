package com.example.note;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.onurkaganaldemir.ktoastlib.KToast;
import com.tomlonghurst.expandablehinttext.ExpandableHintText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ArrayList<Note>arrayList ;
    Note mynote;
    ListView list;
    FloatingActionButton add;
    AlertDialog.Builder alert;
    sql SQL ;
    BaseAdapter adapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         add = findViewById(R.id.add_action);
         alert = new AlertDialog.Builder(this);
        arrayList= new ArrayList<>();
        list = findViewById(R.id.list);
        SQL = new sql(this);

        run();



    }

    @Override
    protected void onStart() {
        super.onStart();
        showlist();
    }

    public void showlist(){
        adapter = new BaseAdapter(getApplicationContext(),SQL.read_data());
        list.setAdapter(adapter);

    }

    public void run(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.add_note,null);
                alert.setView(view);
                final AlertDialog Build = alert.create();
                Build.show();

                final TextInputEditText title = view.findViewById(R.id.enter_title);
                final TextInputEditText note = view.findViewById(R.id.enter_note);
                final MaterialButton A = view.findViewById(R.id.add_note);

                A.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String x = title.getText().toString();
                        String y = note.getText().toString();
                        if (x.isEmpty()&&y.isEmpty()){
                            KToast.errorToast(MainActivity.this, "Can't be Empty ", Gravity.BOTTOM, KToast.LENGTH_AUTO);
                            title.setError("empty");
                            note.setError("empty");
                        }
                        else {
                            SQL.write_data(x,y);
                            showlist();
                            Build.dismiss();
                        }
                    }
                });
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Note n = SQL.read_data().get(position);
                Intent intent = new Intent(getApplicationContext(),NoteActivity.class);
                intent.putExtra("title_key",n.getTitle());
                intent.putExtra("note_key",n.getNote());
                intent.putExtra("id_key",n.getId());
                //intent.putExtra("date_key",n.getDate());
                startActivity(intent);
            }
        });

    }

    public String CurrentDate() {
        Date calendar = Calendar.getInstance().getTime();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        return s.format(calendar);
    }
}
