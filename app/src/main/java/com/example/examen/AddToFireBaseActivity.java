package com.example.examen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class AddToFireBaseActivity extends AppCompatActivity {

    EditText name, date, grade;
    Button addButton;
    ConstraintLayout main;
    ArrayList<String> idUsed;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_firebase);

        name = findViewById(R.id.name);
        date = findViewById(R.id.date);
        grade = findViewById(R.id.grade);

        addButton = findViewById(R.id.addButton);
        main = findViewById(R.id.constrainLayoutID);

        idUsed = new ArrayList<String>();


        db.collection("examenes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot documents : task.getResult()) {
                        idUsed.add(documents.getId());
                    }
                }
            }
        });


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, String> userInfo = new HashMap<String, String>();
                userInfo.put("nombre", name.getText().toString());
                userInfo.put("fecha", date.getText().toString());
                userInfo.put("nota", grade.getText().toString());

                db.collection("examenes").document("examen" + name.getText().toString()).set(userInfo);
                Toast.makeText(AddToFireBaseActivity.this, "Examen añadido correctamente", Toast.LENGTH_SHORT).show();
                name.setText("");
                date.setText("");
                grade.setText("");

            }
        });
    }
}