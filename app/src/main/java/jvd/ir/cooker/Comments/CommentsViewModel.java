package jvd.ir.cooker.Comments;

import android.content.Context;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.cooker.Model.CommentsModel;
import jvd.ir.cooker.Model.MessageModel;

public class CommentsViewModel {

    CommentsRepository repository;

    public CommentsViewModel(Context context){
        repository=new CommentsRepository(context);
    }

    public Single<List<CommentsModel>> getComments(String foodId){
        return repository.getComments(foodId);
    }

    public Single<MessageModel> addComment(String foodId, String comment, String userName){
        return repository.addComment(foodId, comment, userName);
    }

    public Single<MessageModel> likeComment(String commentId,String userName){
        return repository.likeComment(commentId,userName);
    }

    public Single<String > getIfLiked(String commentId, String userName){
        return repository.getIfLiked(commentId,userName);
    }

    public String getUserName(){
        return repository.getUserName();
    }

    public Single<MessageModel> reportComment(String userName,String commentId,String reason){
        return repository.reportComment(userName, commentId, reason);
    }
}
