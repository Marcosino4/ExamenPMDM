package com.example.examen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShowFireBaseDataActivity extends AppCompatActivity {
    EditText search;
    Button searchButton;
    ConstraintLayout main;
    LinearLayout mostrarExamen;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_fire_base_data);

        search = findViewById(R.id.search);

        searchButton = findViewById(R.id.showButton);
        main = findViewById(R.id.constrainLayoutID);
        mostrarExamen = findViewById(R.id.mostrarExamen);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchDB(search.toString());
            }
        });
    }


    public void searchDB(String search){
        db.collection("examenes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    mostrarExamen.removeAllViews();

                    List<DocumentSnapshot> listOfDocuments = task.getResult().getDocuments();
                    for(DocumentSnapshot docs : listOfDocuments){
                        String name = docs.get("nombre").toString();
                        if(name.contains(search)){
                            TextView tSearch = new TextView(ShowFireBaseDataActivity.this);
                            tSearch.setText("Alumno: " + docs.get("nombre").toString() + "; Nota examen: " + docs.get("nota").toString() + "; Fecha examen: " + docs.get("fecha").toString());
                            mostrarExamen.addView(tSearch);
                        }
                    }
                }
            }
        });
    }
}