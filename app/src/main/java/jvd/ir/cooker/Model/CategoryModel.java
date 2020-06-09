
package jvd.ir.cooker.Model;


import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class CategoryModel {

    @SerializedName("category")
    private String mCategory;
    @SerializedName("id")
    private String mId;
    @SerializedName("image")
    private String mImage;
    @SerializedName("position")
    private String mPosition;


    public String getmPosition() {
        return mPosition;
    }

    public void setmPosition(String mPosition) {
        this.mPosition = mPosition;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

}
