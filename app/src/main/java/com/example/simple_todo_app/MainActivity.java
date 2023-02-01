package com.example.simple_todo_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editNote;
    Button addButton;
    ListView noteList;

    //On cree une liste dynamique de chaines de caracteres
    ArrayList<String> noteArrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNote = findViewById(R.id.editNote);
        addButton = findViewById(R.id.addButton);
        noteList = findViewById(R.id.noteList);

        noteArrayList = FileHelper.readDate(this);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, noteArrayList);

        noteList.setAdapter(arrayAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String note = editNote.getText().toString().trim();
                noteArrayList.add(note);
                editNote.setText("");
                FileHelper.writeData(noteArrayList, getApplicationContext());

                arrayAdapter.notifyDataSetChanged();
            }
        });

        noteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Supprimer");
                alert.setMessage("Voulez-vous supprimer cette note ?");
                alert.setCancelable(false);
                alert.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alert.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        noteArrayList.remove(position);
                        arrayAdapter.notifyDataSetChanged();
                        FileHelper.writeData(noteArrayList, getApplicationContext());
                    }
                });

                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        });

    }
}