package jvd.ir.cooker.LoginActivity;

import android.content.Context;

import io.reactivex.Single;
import jvd.ir.cooker.Model.MessageModel;

public class LoginRepository {

    LoginApiService apiService=new LoginApiService();

    UserLoginInfo userLoginInfo;


    public LoginRepository(Context context){
        userLoginInfo=new UserLoginInfo(context);
    }

    public Single<MessageModel> userLogin(String userName, String password){
        return apiService.userLogin(userName, password);
    }

    public Single<MessageModel> userRegister(String userName,String password,String mobile){
        return apiService.userRegister(userName, password, mobile);
    }

    public String getUserName(){
        return userLoginInfo.getUserLoginInfo();

    }

    public void saveUserName(String userName){
        userLoginInfo.saveLoginInfo(userName);
    }
}
