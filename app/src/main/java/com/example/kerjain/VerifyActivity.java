package com.example.kerjain;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class VerifyActivity extends AppCompatActivity {

    private Button tombolSkip;


    //These are the objects needed
    //It is the verification id that will be sent to the user
    private String mVerificationId;

    //The edittext to input the code
    private EditText otp;

    //firebase auth object
    private FirebaseAuth mAuth;

    private DatabaseReference mDatabase;
    private String name, alamat, email, pos, ktp, provinsi, kotakabupaten, kecamatan, mobile, func;
    private String namaPer, namaPen, emailPer, npwp, siup;
    private TextView reset;

    private Uri filePath;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    //Timer
    private long START_TIME = 60000;
    private TextView mTextViewCountDown;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning; //utk cek apakah timer sedang berjalan
    private long mTimeLeft = START_TIME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        tombolSkip = findViewById(R.id.skip);
        tombolSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipMasuk();
            }
        });


        //Init Timer
        mTextViewCountDown = findViewById(R.id.timer);


        //initializing objects
        mAuth = FirebaseAuth.getInstance();
        otp = findViewById(R.id.otp);
        reset = findViewById(R.id.reset);



        //getting mobile number from the previous activity
        //and sending the verification code to the number
        Intent intent = getIntent();
        mobile = intent.getStringExtra("mobile");
        func = intent.getStringExtra("func");

        //declare storeage reference before using
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        if(mTimerRunning == false){
            startTimer();
        }

        if(func.equals("registerPk"))
        {
            name = intent.getStringExtra("name");
            email = intent.getStringExtra("email");
            alamat = intent.getStringExtra("alamat");
            filePath = Uri.parse(intent.getStringExtra("filePath"));
            intent.putExtra("filePath", filePath.toString());
        }else if(func.equals("registerPr"))
        {
            namaPen = intent.getStringExtra("namaPen");
            namaPer = intent.getStringExtra("namaPer");
            emailPer = intent.getStringExtra("emailPer");
            provinsi = intent.getStringExtra("provinsi1");
            kotakabupaten = intent.getStringExtra("kotaKabupaten1");
            kecamatan = intent.getStringExtra("kecamatan1");
            ktp = intent.getStringExtra("ktp");
            npwp = intent.getStringExtra("npwp");
            siup = intent.getStringExtra("siup");
            pos = intent.getStringExtra("pos");
            alamat = intent.getStringExtra("alamat");
        }

        sendVerificationCode(mobile);
        //if the automatic sms detection did not work, user can also enter the code manually
        //so adding a click listener to the button
        findViewById(R.id.buttonSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = otp.getText().toString().trim();
                if (code.isEmpty() || code.length() < 6) {
                    otp.setError("Enter valid code");
                    otp.requestFocus();
                    return;
                }

                //verifying the code entered manually
                verifyVerificationCode(code);
            }
        });
    }
    //the method is sending verification code
    //the country id is concatenated
    //you can take the country id as user input as well
    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+62" + mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }

    //the callback to detect the verification status
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                otp.setText(code);
                //verifying the code
                verifyVerificationCode(code);
            }
        }
        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(VerifyActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            //storing the verification id that is sent to the user
            mVerificationId = s;
        }
    };
    private void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);

        //signing the user
        signInWithPhoneAuthCredential(credential);
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(VerifyActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    if(func.equals("registerPk"))
                    {
                        registerPk();
                    }else if(func.equals("registerPr"))
                    {
                        registerPr();
                    }
                    else
                    {
                        next();
                    }
                    //verification successful we will start the profile activity


                } else {

                    //verification unsuccessful.. display an error message

                    String message = "Somthing is wrong, we will fix it soon...";

                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                        message = "Invalid code entered...";
                    }

                    Snackbar snackbar = Snackbar.make(findViewById(R.id.parent), message, Snackbar.LENGTH_LONG);
                    snackbar.setAction("Dismiss", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    snackbar.show();
                }
            }
        });
    }

    public void registerPk (){
        final FirebaseUser user = mAuth.getInstance().getCurrentUser();
        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference mConditionRef = mRootRef.child("users").child("pekerja").child(user.getUid());
        DatabaseReference mRegistered = mRootRef.child("registered").child("pekerja");
        mConditionRef.child("nama").setValue(name);
        mConditionRef.child("status").setValue("Tidak Sedang Bekerja");
        mConditionRef.child("join_grup").setValue("false");
        mConditionRef.child("nomor_telepon").setValue(mobile);
        mConditionRef.child("email").setValue(email);
        mConditionRef.child("alamat").setValue(alamat);
        mRegistered.child(mobile).setValue(mobile);
        uploadImage();
    }
    public void registerPr(){
        final FirebaseUser user = mAuth.getInstance().getCurrentUser();
        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference mConditionRef = mRootRef.child("users").child("perusahaan").child(user.getUid());
        DatabaseReference mRegistered = mRootRef.child("registered").child("perusahaan");
        mConditionRef.child("nama_perusahaan").setValue(namaPer);
        mConditionRef.child("nomor_telepon").setValue(mobile);
        mConditionRef.child("email").setValue(emailPer);
        mConditionRef.child("nama_penanggungjawab").setValue(namaPen);
        mConditionRef.child("nomor_npwp").setValue(npwp);
        mConditionRef.child("nomor_siup").setValue(siup);
        mConditionRef.child("alamat").setValue(alamat);
        mRegistered.child(mobile).setValue(mobile);
    }
    private void startTimer(){
        reset.setOnClickListener(null);
        //syntax CountDownTimer(long milisecInFuture, longCountDownInterval) dimana millisecInFuture is the time you set in millisecond when you want CountDownTimer to stop and countDownInterval is the interval time in millisecond you set after which number will increment in CountDownTimer.
        mCountDownTimer = new CountDownTimer(mTimeLeft, 1000) { //countDownInterval itu fungsinya utk: tiap brp milisecondkah, method onTick akan dijalankan
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeft = millisUntilFinished; // utk save nilainya jd kita bs continue dari waktu yg sblmnya
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                //this code will run when time is up
                mTimerRunning = false;
                waktuHabis();
            }
        }.start();

        mTimerRunning = true;
    }
    private void updateCountDownText(){
        int minutes = (int) (mTimeLeft / 1000) / 60; //di-cast ke int karna type_defaultnya long, dibagi seribu biar dari miliseconds jd seconds, dibagi 60 biar jd menit
        int seconds = (int) (mTimeLeft / 1000) % 60; //modulus is to return what is left after calculating the minutes

        //format this value to be like clock
        String timeLeftFormat = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormat);
    }

    public void waktuHabis(){
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                START_TIME = 60000;
                mTimeLeft = START_TIME;
                startTimer();
                sendVerificationCode(mobile);

            }
        });
    }
    public void skipMasuk(){
        Intent intent = new Intent(this, BottomNavigationView.class);
        startActivity(intent);
    }
    public void next(){
        Intent intent = new Intent(VerifyActivity.this, BottomNavigationView.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    private void uploadImage() {

        if(filePath != null)
        {
            final FirebaseUser user = mAuth.getInstance().getCurrentUser();
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref;
            ref = storageReference.child("images").child("pekerja").child(user.getUid());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(VerifyActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                            next();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(VerifyActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());

                        }
                    });
        }
    }


}
