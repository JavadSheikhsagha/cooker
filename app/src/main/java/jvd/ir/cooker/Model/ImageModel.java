
package jvd.ir.cooker.Model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ImageModel {

    @SerializedName("confirm")
    private String mConfirm;
    @SerializedName("image")
    private String mImage;

    @SerializedName("info")
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getConfirm() {
        return mConfirm;
    }

    public void setConfirm(String confirm) {
        mConfirm = confirm;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

}
