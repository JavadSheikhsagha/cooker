package jvd.ir.cooker.CategoryActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.google.android.material.tabs.TabLayout;
import de.hdodenhof.circleimageview.CircleImageView;
import jvd.ir.cooker.R;

public class ActivityCategory extends AppCompatActivity {

    ViewPager viewPager;

    TabLayout tabLayout;

    ImageView imgBack;

    int  position;

    String positionSt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        positionSt = getIntent().getStringExtra("position");
        position = Integer.parseInt(positionSt);
        setupViews();
    }

    private void setupViews() {

        imgBack = findViewById(R.id.img_cat_back);
        viewPager = findViewById(R.id.vp_cat_viewPager);
        tabLayout=findViewById(R.id.tl_cat_tabLayout);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment("ته چین");
        adapter.addFragment("مرغ");
        adapter.addFragment("پیتزا");
        adapter.addFragment("کباب");
        adapter.addFragment("خورشت");
        adapter.addFragment("آب پز");
        adapter.addFragment("گیاه خواری");
        adapter.addFragment("نوشیدنی");
        adapter.addFragment("دسر");

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position-1);

        tabLayout.setupWithViewPager(viewPager);


    }
}
