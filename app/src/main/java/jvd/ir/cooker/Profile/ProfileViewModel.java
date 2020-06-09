package jvd.ir.cooker.Profile;

import android.content.Context;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.cooker.Model.CommentsModel;
import jvd.ir.cooker.Model.FoodModel;
import jvd.ir.cooker.Model.MessageModel;
import jvd.ir.cooker.Model.ProfileModel;

public class ProfileViewModel {

    ProfileRepository repository;

    public ProfileViewModel(Context context){
        repository = new ProfileRepository(context);
    }

    public void userLogOut(String userName){
         repository.userLogOut(userName);
    }

    public Single<List<CommentsModel>> getMyComments(String userName){
        return repository.getMyComments(userName);
    }

    public String getUserName(){
        return repository.getUserName();
    }

    public Single<List<CommentsModel>> getLikedComments(String userName){
        return repository.getLikedComments(userName);
    }

    public Single<List<FoodModel>> getLikedFoods(String userName){
        return repository.getLikedFoods(userName);
    }


    public Single<List<CommentsModel>> getReportedComments(String userName){
        return repository.getReportedComments(userName);
    }

    public Single<ProfileModel> getProfile(String userName){
        return repository.getProfile(userName);
    }

    public Single<MessageModel> updateProfile(String userName, String mobile){
        return repository.updateProfile(userName, mobile);
    }
}
