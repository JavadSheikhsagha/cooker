
package jvd.ir.cooker.Model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class FoodIngredients {

    @SerializedName("amount")
    private String mAmount;
    @SerializedName("ing_title")
    private String mIngTitle;
    @SerializedName("unit")
    private String mUnit;

    public String getAmount() {
        return mAmount;
    }

    public void setAmount(String amount) {
        mAmount = amount;
    }

    public String getIngTitle() {
        return mIngTitle;
    }

    public void setIngTitle(String ingTitle) {
        mIngTitle = ingTitle;
    }

    public String getUnit() {
        return mUnit;
    }

    public void setUnit(String unit) {
        mUnit = unit;
    }

}
