package jvd.ir.cooker.RoomDatabase.FoodMain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FoodModel2 {



        @PrimaryKey(autoGenerate = true)
        public int uid;

        @ColumnInfo(name = "food_id")
        public String foodId;

        @ColumnInfo(name = "title")
        public String title;

        @ColumnInfo(name = "likes")
        public String likes;

        @ColumnInfo(name = "category")
        public String category;

        @ColumnInfo(name = "cat_from")
        public String catFrom;

        @ColumnInfo(name = "chef_id")
        public String chefId;

        @ColumnInfo(name = "image")
        public String image;

        @ColumnInfo(name = "difficulty")
        public String difficulty;

        @ColumnInfo(name = "time")
        public String time;


        public String getFoodId() {
            return foodId;
        }

        public void setFoodId(String foodId) {
            this.foodId = foodId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLikes() {
            return likes;
        }

        public void setLikes(String likes) {
            this.likes = likes;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getCatFrom() {
            return catFrom;
        }

        public void setCatFrom(String catFrom) {
            this.catFrom = catFrom;
        }

        public String getChefId() {
            return chefId;
        }

        public void setChefId(String chefId) {
            this.chefId = chefId;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDifficulty() {
            return difficulty;
        }

        public void setDifficulty(String difficulty) {
            this.difficulty = difficulty;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }


}
