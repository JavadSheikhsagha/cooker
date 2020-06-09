
package jvd.ir.cooker.Model;


import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ProfileModel {

    @SerializedName("password")
    private String mPassword;
    @SerializedName("phone")
    private String mPhone;
    @SerializedName("user_name")
    private String mUserName;

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

}
