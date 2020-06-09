package jvd.ir.cooker.LoginActivity;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import io.reactivex.Single;
import jvd.ir.cooker.Model.MessageModel;

public class LoginViewModel  {

    Context context;

    public LoginViewModel(Context context){
        repository=new LoginRepository(context);
    }

    LoginRepository repository;


    public Single<MessageModel> userLogin(String userName, String password){
        return repository.userLogin(userName, password);
    }

    public Single<MessageModel> userRegister(String userName,String password,String mobile){
        return repository.userRegister(userName, password, mobile);
    }

    public void saveUserName(String userName){
        repository.saveUserName(userName);
    }
}
