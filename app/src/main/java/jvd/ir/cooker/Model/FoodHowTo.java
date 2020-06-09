
package jvd.ir.cooker.Model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class FoodHowTo {

    @SerializedName("id")
    private String mId;
    @SerializedName("todo")
    private String mTodo;
    @SerializedName("warn")
    private String mWarn;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getTodo() {
        return mTodo;
    }

    public void setTodo(String todo) {
        mTodo = todo;
    }

    public String getWarn() {
        return mWarn;
    }

    public void setWarn(String warn) {
        mWarn = warn;
    }

}
