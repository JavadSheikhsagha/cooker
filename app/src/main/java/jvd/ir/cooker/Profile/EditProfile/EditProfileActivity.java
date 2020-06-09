package jvd.ir.cooker.Profile.EditProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jvd.ir.cooker.Model.MessageModel;
import jvd.ir.cooker.Model.ProfileModel;
import jvd.ir.cooker.Profile.ProfileViewModel;
import jvd.ir.cooker.R;

public class EditProfileActivity extends AppCompatActivity {

    EditText edtUserName,edtPassword,edtNumber;

    ImageView imgBack;

    Button btnSubmit;

    ProfileViewModel viewModel;

    String USER_NAME;

    CompositeDisposable compositeDisposable=new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        setupViews();

        USER_NAME = viewModel.getUserName();

        getProfileInfo();

        updateProfileInfo();
    }

    private void updateProfileInfo() {

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewModel.updateProfile(USER_NAME,edtNumber.getText().toString())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<MessageModel>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                compositeDisposable.add(d);
                            }

                            @Override
                            public void onSuccess(MessageModel messageModel) {
                                Toast.makeText(EditProfileActivity.this, "اطلاعات با موفقیت ثبت شد.", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("LOG11", "onError: "+e.toString());
                            }
                        });
            }
        });
    }

    private void getProfileInfo() {

        viewModel.getProfile(USER_NAME)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ProfileModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(ProfileModel profileModel) {
                        edtNumber.setText(profileModel.getPhone());
                        edtPassword.setText(profileModel.getPassword());
                        edtUserName.setText(profileModel.getUserName());
                        edtUserName.setEnabled(false);
                        edtPassword.setEnabled(false);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("LOG10", "onError: "+e.toString() );
                    }
                });
    }

    private void setupViews() {

        viewModel = new ProfileViewModel(this);

        edtNumber = findViewById(R.id.edt_editProfile_phone);
        edtUserName = findViewById(R.id.edt_editProfile_userName);
        edtPassword = findViewById(R.id.edt_editProfile_password);

        imgBack = findViewById(R.id.img_editProfile_back);
        btnSubmit = findViewById(R.id.btn_editProfile_submit);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}
