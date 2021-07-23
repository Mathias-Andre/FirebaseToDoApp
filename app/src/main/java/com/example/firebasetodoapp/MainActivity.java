package com.example.firebasetodoapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity {

    private TextView tvDate;
    private EditText etDate;
    private TextView tvTitle;
    private EditText etTitle;
    private Button btnUpdate;

    private FirebaseFirestore db;
    private CollectionReference colRef;
    private DocumentReference docRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDate = findViewById(R.id.textViewDate);
        etDate = findViewById(R.id.editTextMessage);
        tvTitle = findViewById(R.id.textViewTitle);
        etTitle = findViewById(R.id.editTextPriority);

        db = FirebaseFirestore.getInstance();

        colRef = db.collection("firebasetodoapp");
        docRef = colRef.document("text");
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    toDoItem msg = snapshot.toObject(toDoItem.class);  //use POJO
                    tvDate.setText(msg.getDate());
                    tvTitle.setText(msg.getTitle());

                }
            }
        });

        btnUpdate = findViewById(R.id.buttonUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnUpdateOnClick(v);
            }
        });


    }

    private void btnUpdateOnClick(View v) {
        String text = etDate.getText().toString();
        String priority = etTitle.getText().toString();
        toDoItem msg = new toDoItem(text, priority);
        docRef.set(msg);
    }
}