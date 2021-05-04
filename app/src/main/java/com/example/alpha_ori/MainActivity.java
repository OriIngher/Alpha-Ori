package com.example.alpha_ori;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

import static com.example.alpha_ori.FBref.refAuth;
import static com.example.alpha_ori.FBref.refUsers;

public class MainActivity extends AppCompatActivity {

    EditText Email1;
    EditText Password1;
    EditText Name1;
    Button Button2;
    Button Button1;
    String name, email , password, age , weight , height , workout;
    Trainee traineedb;
    String uid;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        Password1 = findViewById(R.id.Password1);
        Email1 = findViewById(R.id.Email1);
        Name1 = findViewById(R.id.Name1);
        Button Button1 = findViewById(R.id.button1);
        Button Button2 = findViewById(R.id.button2);
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);}

    public void login(View view) {
        String st1 = Email1.getText().toString();
        String st2 = Password1.getText().toString();
        mAuth.signInWithEmailAndPassword(st1, st2)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            if (mAuth.getCurrentUser().isEmailVerified())
                            {
                                Toast.makeText(MainActivity.this,"welcome back", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this,"user did not accept the mail", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this,task.getException()+"",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });

    }

    public void signup(View view) {
        String st1 = Email1.getText().toString();
        String st2 = Password1.getText().toString();

        mAuth.createUserWithEmailAndPassword(st1, st2)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this,"welcome",Toast.LENGTH_SHORT).show();
                            mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(MainActivity.this,"verification email was sent",Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(MainActivity.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(MainActivity.this, task.getException()+"",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
        name=Name1.getText().toString();
        email= Email1.getText().toString();
        password=Password1.getText().toString();


        final ProgressDialog pd= ProgressDialog.show(this,"Register","Registering...",true);
        refAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        pd.dismiss();
                        if (task.isSuccessful()) {
                            Log.d("MainActivity", "createUserWithEmail:success");
                            FirebaseUser user = refAuth.getCurrentUser();
                            uid = user.getUid();
                            traineedb=new Trainee(name,email,uid);
                            refUsers.child(uid).setValue(traineedb);
                            Toast.makeText(MainActivity.this, "Successful registration", Toast.LENGTH_SHORT).show();
                            Intent si = new Intent(MainActivity.this,Login.class);
                            si.putExtra("newuser",true);
                            startActivity(si);
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException)
                                Toast.makeText(MainActivity.this, "User with e-mail already exist!", Toast.LENGTH_SHORT).show();
                            else {
                                Log.w("MainActivity", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "User create failed.",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });

    }


    private void updateUI(FirebaseUser currentUser) {

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    /**
     * switch activity
     * @param menu
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem menu) {
        String st = menu.getTitle().toString();

        if ((st.equals("storage"))) {
            Intent si = new Intent(this, Storage.class);
            startActivity(si);
        }
        if ((st.equals("authentication"))) {
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
        }
        if ((st.equals("NFC"))) {
            Intent si = new Intent(this, NFC.class);
            startActivity(si);
        }



        return true;
    }




}




