package jvd.ir.cooker.RoomDatabase.FoodHowTo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class HowToModel2 {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "food_id")
    public String foodId;

    @ColumnInfo(name = "todo")
    public String todo;

    @ColumnInfo(name = "warn")
    public String warn;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getWarn() {
        return warn;
    }

    public void setWarn(String warn) {
        this.warn = warn;
    }
}
