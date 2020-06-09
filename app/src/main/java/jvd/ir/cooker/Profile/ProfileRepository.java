package jvd.ir.cooker.Profile;

import android.content.Context;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.cooker.LoginActivity.UserLoginInfo;
import jvd.ir.cooker.Model.CommentsModel;
import jvd.ir.cooker.Model.FoodModel;
import jvd.ir.cooker.Model.MessageModel;
import jvd.ir.cooker.Model.ProfileModel;

public class ProfileRepository {

    UserLoginInfo userLoginInfo;

    ProfileApiService apiService=new ProfileApiService();

    public ProfileRepository(Context context){
        userLoginInfo=new UserLoginInfo(context);
    }

    public void userLogOut(String userName){
        userLoginInfo.saveLoginInfo(userName);
    }

    public String getUserName(){
        return userLoginInfo.getUserLoginInfo();
    }

    public Single<List<CommentsModel>> getMyComments(String userName){
        return apiService.getMyComments(userName);
    }

    public Single<List<CommentsModel>> getLikedComments(String userName){
        return apiService.getLikedComments(userName);
    }

    public Single<List<FoodModel>> getLikedFoods(String userName){
        return apiService.getLikedFoods(userName);
    }

    public Single<List<CommentsModel>> getReportedComments(String userName){
        return apiService.getReportedComments(userName);
    }

    public Single<ProfileModel> getProfile(String userName){
        return apiService.getProfile(userName);
    }

    public Single<MessageModel> updateProfile(String userName, String mobile){
        return apiService.updateProfile(userName, mobile);
    }
}
