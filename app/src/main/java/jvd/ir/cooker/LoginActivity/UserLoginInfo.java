package jvd.ir.cooker.LoginActivity;

import android.content.Context;
import android.content.SharedPreferences;

import io.reactivex.Single;

public class UserLoginInfo {

    SharedPreferences sharedPreferences;

    public UserLoginInfo(Context context){

        sharedPreferences=context.getSharedPreferences("login_data",Context.MODE_PRIVATE);
    }

    public void saveLoginInfo(String userName){
        SharedPreferences.Editor editor1=sharedPreferences.edit();
        editor1.putString("login_data",userName);
        editor1.apply();
    }

    public String getUserLoginInfo(){

        return sharedPreferences.getString("login_data","");
    }
}
