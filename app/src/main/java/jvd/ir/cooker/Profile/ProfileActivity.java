package jvd.ir.cooker.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jvd.ir.cooker.MainActivity.MainActivity;
import jvd.ir.cooker.Model.ProfileMenuModel;
import jvd.ir.cooker.Profile.BookmarkFoods.BookmarkFoodsActivity;
import jvd.ir.cooker.Profile.EditProfile.EditProfileActivity;
import jvd.ir.cooker.Profile.LikedComments.LikedCommentsActivity;
import jvd.ir.cooker.Profile.LikedFoods.LikedFoodsActivity;
import jvd.ir.cooker.Profile.MyComments.MyCommentsActivity;
import jvd.ir.cooker.Profile.ReportedComments.ReportedComments;
import jvd.ir.cooker.R;

public class ProfileActivity extends AppCompatActivity {

    ImageView imgBack;

    RecyclerView recyclerView;

    TextView txtUserName;

    List<ProfileMenuModel> profileMenuModel;

    String USER_NAME;

    ProfileViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        USER_NAME = getIntent().getStringExtra("user_name");

        setupViews();
    }

    private void setupViews() {

        viewModel=new ProfileViewModel(this);

        profileMenuModel=new ArrayList<>();

        imgBack = findViewById(R.id.img_profile_back);

        recyclerView = findViewById(R.id.rv_profile_menu);

        txtUserName = findViewById(R.id.txt_profile_userName);
        txtUserName.setText(USER_NAME);

        ProfileMenuModel model1=new ProfileMenuModel();
        model1.setTitle("ویرایش حساب کاربری");
        model1.setIcon(R.drawable.ic_person_black_24dp);
        profileMenuModel.add(model1);

        ProfileMenuModel model2=new ProfileMenuModel();
        model2.setTitle("غذا های ذخیره شده");
        model2.setIcon(R.drawable.ic_bookmark_black_24dp);
        profileMenuModel.add(model2);

        ProfileMenuModel model3=new ProfileMenuModel();
        model3.setTitle("غذا های مورد علاقه");
        model3.setIcon(R.drawable.ic_like_black_24dp);
        profileMenuModel.add(model3);

        ProfileMenuModel model4=new ProfileMenuModel();
        model4.setTitle("کامنت های من");
        model4.setIcon(R.drawable.ic_comment_black_24dp);
        profileMenuModel.add(model4);

        ProfileMenuModel model5=new ProfileMenuModel();
        model5.setTitle("کامنت های لایک شده");
        model5.setIcon(R.drawable.ic_like_border_black_24dp);
        profileMenuModel.add(model5);

        ProfileMenuModel model7=new ProfileMenuModel();
        model7.setTitle("کامنت های گزارش شده");
        model7.setIcon(R.drawable.ic_report_black_24dp);
        profileMenuModel.add(model7);


        ProfileMenuModel model6=new ProfileMenuModel();
        model6.setTitle("خروج");
        model6.setIcon(R.drawable.ic_exit_to_app_black_24dp);
        profileMenuModel.add(model6);

        ProfileMenuRvAdapter adapter=new ProfileMenuRvAdapter(profileMenuModel);
        adapter.setOnMenuClick(new ProfileMenuRvAdapter.setOnMenuClick() {
            @Override
            public void onClick(int position) {
                switch (position){
                    case 0:
                        startActivity(new Intent(ProfileActivity.this, EditProfileActivity.class));
                        break;

                    case 1:
                        startActivity(new Intent(ProfileActivity.this, BookmarkFoodsActivity.class));
                        break;

                    case 2:
                        Intent intent4=new Intent(ProfileActivity.this, LikedFoodsActivity.class);
                        startActivity(intent4);
                        break;

                    case 3:
                        Intent intent2=new Intent(ProfileActivity.this, MyCommentsActivity.class);
                        startActivity(intent2);
                        break;

                    case 4:
                        Intent intent3=new Intent(ProfileActivity.this, LikedCommentsActivity.class);
                        startActivity(intent3);
                        break;

                    case 5:
                        Intent intent6=new Intent(ProfileActivity.this, ReportedComments.class);
                        startActivity(intent6);
                        break;

                    case 6:
                        viewModel.userLogOut("");
                        Intent intent=new Intent(ProfileActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
