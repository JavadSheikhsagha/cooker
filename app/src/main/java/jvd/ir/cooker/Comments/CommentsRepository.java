package jvd.ir.cooker.Comments;

import android.content.Context;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.cooker.CategoryActivity.CategoryRepository;
import jvd.ir.cooker.LoginActivity.UserLoginInfo;
import jvd.ir.cooker.Model.CommentsModel;
import jvd.ir.cooker.Model.MessageModel;

public class CommentsRepository {

    CommentsApiService apiService=new CommentsApiService();

    UserLoginInfo userLoginInfo;

    public CommentsRepository(Context context){
        userLoginInfo=new UserLoginInfo(context);
    }

    public Single<List<CommentsModel>> getComments(String foodId){
        return apiService.getComments(foodId);
    }

    public Single<MessageModel> addComment(String foodId, String comment, String userName){
        return apiService.addComment(foodId, comment, userName);
    }

    public Single<MessageModel> likeComment(String commentId,String userName){
        return apiService.likeComment(commentId,userName);
    }

    public Single<String > getIfLiked(String commentId, String userName){
        return apiService.getIfLiked(commentId,userName);
    }

    public String getUserName(){
        return userLoginInfo.getUserLoginInfo();
    }

    public Single<MessageModel> reportComment(String userName,String commentId,String reason){
        return apiService.reportComment(userName, commentId, reason);
    }
}
