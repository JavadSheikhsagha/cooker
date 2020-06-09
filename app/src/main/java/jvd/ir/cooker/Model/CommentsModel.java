
package jvd.ir.cooker.Model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class CommentsModel {

    @SerializedName("comment")
    private String mComment;
    @SerializedName("food_id")
    private String mFoodId;
    @SerializedName("id")
    private String mId;
    @SerializedName("likes")
    private String mLikes;
    @SerializedName("user_name")
    private String mUserName;
    private String Liked;
    @SerializedName("confirm")
    private String Confirm;
    @SerializedName("reason")
    private String Reason;
    @SerializedName("status")
    private String Status;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getConfirm() {
        return Confirm;
    }

    public void setConfirm(String confirm) {
        Confirm = confirm;
    }

    public String getLiked() {
        return Liked;
    }

    public void setLiked(String liked) {
        Liked = liked;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    public String getFoodId() {
        return mFoodId;
    }

    public void setFoodId(String foodId) {
        mFoodId = foodId;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getLikes() {
        return mLikes;
    }

    public void setLikes(String likes) {
        mLikes = likes;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

}
