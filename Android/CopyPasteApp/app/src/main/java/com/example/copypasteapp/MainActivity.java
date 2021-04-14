package com.example.copypasteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Activity activity = this;

        EditText copyPasteText = findViewById(R.id.copyPasteText);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("CopyPasteAppText");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String text = snapshot.getValue(String.class);
                text = text == null || text.equals("") ? "No Data" : text;
                copyPasteText.setText(text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Button copy = findViewById(R.id.copy);
        copy.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            clipboard.setText(copyPasteText.getText());
            Toast.makeText(activity, "Text Copied\n" + copyPasteText.getText().toString(), Toast.LENGTH_LONG).show();
        });

        Button send = findViewById(R.id.send);
        send.setOnClickListener(v -> {
            reference.setValue(copyPasteText.getText().toString());
            Toast.makeText(activity, "Data Sent", Toast.LENGTH_LONG).show();
        });
    }
}