package com.example.servicesandproductmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class snpl5 extends AppCompatActivity {
    Button btn_booking5, btn_c5;
    EditText enter_c5;
    FirebaseDatabase database;
    DatabaseReference reference;
    Nailtreatment nailtreatment;
    int i = 0;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snpl5);

        viewPager = (ViewPager) findViewById(R.id.viewPager5);
        ViewPagerAdapter5 viewPagerAdapter5 = new ViewPagerAdapter5(this);
        viewPager.setAdapter(viewPagerAdapter5);

        nailtreatment = new Nailtreatment();
        btn_booking5 = findViewById(R.id.btn_booking5);
        btn_c5 = findViewById(R.id.btn_c5);
        enter_c5 = findViewById(R.id.enter_c5);

        reference =database.getInstance().getReference().child("NailTreatment");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()){
                    i = (int)datasnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //////
            }
        });

        btn_c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Comment = enter_c5.getText().toString();
                nailtreatment.setComment(enter_c5.getText().toString());
                reference.child(String.valueOf(i + 1)).setValue(nailtreatment);
            }
        });
    }
}