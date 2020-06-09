package jvd.ir.cooker.Comments;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.cooker.Model.ApiProvider;
import jvd.ir.cooker.Model.ApiService;
import jvd.ir.cooker.Model.CommentsModel;
import jvd.ir.cooker.Model.MessageModel;

public class CommentsApiService {

    ApiService apiService= ApiProvider.apiProvider();

    public Single<List<CommentsModel>> getComments(String foodId){
        return apiService.getComments(foodId);
    }

    public Single<MessageModel> addComment(String foodId,String comment,String userName){
        return apiService.addComment(foodId, comment, userName);
    }

    public Single<MessageModel> likeComment(String commentId,String userName){
        return apiService.likeComment(commentId,userName);
    }

    public Single<String > getIfLiked(String commentId, String userName){
        return apiService.getIfCommentLiked(commentId,userName);
    }

    public Single<MessageModel> reportComment(String userName,String commentId,String reason){
        return apiService.reportComment(userName, commentId, reason);
    }
}
