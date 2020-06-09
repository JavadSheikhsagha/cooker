package jvd.ir.cooker.RoomDatabase.BuyList;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BuyListModel2 {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "food_id")
    public String foodId;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "amount")
    public String amount;

    @ColumnInfo(name = "unit")
    public String unit;


    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
