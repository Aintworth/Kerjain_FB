package com.example.kerjain;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class HomeFragment extends Fragment {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseUser user;
    private TextView lblNama, lblStatus, lblProyek, lblPt, lblGrup, lblTime, lblJlh;
    private ImageView imageView, fotoProfil;
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        inflater.inflate(R.layout.fragment_home,container,false);
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        lblNama = view.findViewById(R.id.tulisannama);
        lblStatus = view.findViewById(R.id.tulisanstatus1);
        lblProyek = view.findViewById(R.id.tulisanproyekpipa);
        lblPt = view.findViewById(R.id.namapt);
        lblGrup = view.findViewById(R.id.tulisanperkumpulan);
        lblTime = view.findViewById(R.id.bataswaktu);
        lblJlh = view.findViewById(R.id.tulisanjumlahorang);
        imageView = view.findViewById(R.id.dayempat);
        fotoProfil = view.findViewById(R.id.fotoprofil);
        user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference mConditionRef = mRootRef.child("users").child("pekerja").child(user.getUid());

        mConditionRef.child("nama").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                lblNama.setText(text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mConditionRef.child("status").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                lblStatus.setText(text);
                if(text.equals("Tidak Sedang Bekerja"))
                {
                    imageView.setVisibility(View.INVISIBLE);
                    lblProyek.setText("-");
                    lblTime.setText("-");
                    lblPt.setText("-");
                    lblJlh.setText("-");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mConditionRef.child("join_grup").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                if(text.equals("false"))
                {
                    lblGrup.setText("-");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        final StorageReference ref = storageReference.child("images").child("pekerja").child(user.getUid());
        //GlideApp.with(this /* context */)
        //      .load(ref)
        //    .into(imageView);
        final long ONE_MEGABYTE = Long.MAX_VALUE;
        ref.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imageView.setImageBitmap(bmp);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(getActivity().getApplicationContext(), "No Such file or Path found!!", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}
