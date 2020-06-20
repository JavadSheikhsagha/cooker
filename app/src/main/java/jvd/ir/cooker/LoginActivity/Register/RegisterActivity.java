package jvd.ir.cooker.LoginActivity.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.regex.Pattern;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jvd.ir.cooker.LoginActivity.LoginActivity;
import jvd.ir.cooker.LoginActivity.LoginViewModel;
import jvd.ir.cooker.MainActivity.MainActivity;
import jvd.ir.cooker.Model.MessageModel;
import jvd.ir.cooker.R;

public class RegisterActivity extends AppCompatActivity {

    EditText edtUserName,edtPassword,edtNumber;

    ImageView imgBack;

    Button btnRegister;

    LoginViewModel viewModel;

    String PHONE_NUMBER;

    CompositeDisposable compositeDisposable=new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setupViews();
    }

    private void setupViews() {

        viewModel=new LoginViewModel(this);

        edtNumber = findViewById(R.id.edt_register_phone);
        edtPassword = findViewById(R.id.edt_register_password);
        edtUserName = findViewById(R.id.edt_register_userName);


        btnRegister = findViewById(R.id.btn_register_register);

        imgBack=findViewById(R.id.img_register_back);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isValidMail(edtNumber.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "فرمت شماره وارد شده صحیح نمی باشد.", Toast.LENGTH_SHORT).show();

                } else if (edtUserName.length()<3){
                    Toast.makeText(RegisterActivity.this, "یوزرنیم وارد شده باید بیشتر از 3 کاراکتر باشد.", Toast.LENGTH_SHORT).show();
                } else if (edtPassword.length()<3){
                    Toast.makeText(RegisterActivity.this, "رمز عبور وارد شده باید بیش از 3 کاراکتر باشد.", Toast.LENGTH_SHORT).show();
                } else {

                    viewModel.userRegister(edtUserName.getText().toString(),edtPassword.getText().toString(),edtNumber.getText().toString())
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new SingleObserver<MessageModel>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    compositeDisposable.add(d);
                                }

                                @Override
                                public void onSuccess(MessageModel messageModel) {

                                    Toast.makeText(RegisterActivity.this, "ثبت نام با موفقیت انجام شد.", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                                    viewModel.saveUserName(messageModel.getMessage());
                                    startActivity(intent);
                                    finish();

                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            });
                }

            }
        });
    }

    private boolean isValidMail(String number) {

        PHONE_NUMBER = "[0-9]{10}";

        return Pattern.compile(PHONE_NUMBER).matcher(number).matches();

    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
        finish();
        super.onBackPressed();
    }

}
