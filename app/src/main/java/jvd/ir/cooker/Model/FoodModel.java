
package jvd.ir.cooker.Model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class FoodModel {

    @SerializedName("cat_from")
    private String mCatFrom;
    @SerializedName("category")
    private String mCategory;
    @SerializedName("cheef_id")
    private String mCheefId;
    @SerializedName("likes")
    private String mLikes;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("id")
    private String id;
    @SerializedName("image")
    private String image;
    @SerializedName("new")
    private String mNew;
    @SerializedName("time")
    private String mTime;
    @SerializedName("difficulty")
    private String mDifficulty;
    @SerializedName("confirm")
    private String mConfirm;

    public String getmConfirm() {
        return mConfirm;
    }

    public void setmConfirm(String mConfirm) {
        this.mConfirm = mConfirm;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getmDifficulty() {
        return mDifficulty;
    }

    public void setmDifficulty(String mDifficulty) {
        this.mDifficulty = mDifficulty;
    }

    public String getmNew() {
        return mNew;
    }

    public void setmNew(String mNew) {
        this.mNew = mNew;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatFrom() {
        return mCatFrom;
    }

    public void setCatFrom(String catFrom) {
        mCatFrom = catFrom;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public String getCheefId() {
        return mCheefId;
    }

    public void setCheefId(String cheefId) {
        mCheefId = cheefId;
    }

    public String getLikes() {
        return mLikes;
    }

    public void setLikes(String likes) {
        mLikes = likes;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
