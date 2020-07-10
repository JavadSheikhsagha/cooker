package jvd.ir.cooker.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jvd.ir.cooker.LoginActivity.Register.RegisterActivity;
import jvd.ir.cooker.MainActivity.MainActivity;
import jvd.ir.cooker.Model.MessageModel;
import jvd.ir.cooker.R;

public class LoginActivity extends AppCompatActivity {

    EditText edtUserName,edtPassword;

    ImageView imgBack;

    Button btnLogin;

    TextView txtRegister;

    LoginViewModel viewModel;

    CompositeDisposable compositeDisposable=new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupViews();
    }

    private void setupViews() {

        viewModel=new LoginViewModel(this);

        edtPassword = findViewById(R.id.edt_login_password);
        edtUserName = findViewById(R.id.edt_login_userName);

        imgBack = findViewById(R.id.img_login_back);

        btnLogin = findViewById(R.id.btn_login_login);

        txtRegister = findViewById(R.id.txt_login_register);

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtUserName.length()<3){
                    Toast.makeText(LoginActivity.this, "نام کاربری باید بیش از 3 کاراکتر باشد.", Toast.LENGTH_LONG).show();
                } else if (edtPassword.length()<3) {
                    Toast.makeText(LoginActivity.this, "رمز عبور باید بیش از 3 کاراکتر باشد.", Toast.LENGTH_LONG).show();
                }

                viewModel.userLogin(edtUserName.getText().toString(),edtPassword.getText().toString())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<MessageModel>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                compositeDisposable.add(d);
                            }

                            @Override
                            public void onSuccess(MessageModel messageModel) {
                                if (messageModel.getMessage().equals(edtUserName.getText().toString())){

                                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                    intent.putExtra("user_name",messageModel.getMessage());
                                    viewModel.saveUserName(messageModel.getMessage());
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Toast.makeText(LoginActivity.this, messageModel.getMessage(), Toast.LENGTH_SHORT).show();
                                    edtPassword.setText("");
                                    edtUserName.setText("");
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });
            }
        });
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
        super.onBackPressed();
    }
}
