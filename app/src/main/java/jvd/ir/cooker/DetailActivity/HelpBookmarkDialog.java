package jvd.ir.cooker.DetailActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import jvd.ir.cooker.Profile.BookmarkFoods.BookmarkFoodsActivity;
import jvd.ir.cooker.R;

public class HelpBookmarkDialog extends DialogFragment {

    Button btnBookmark;

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.img_bookmark_help_dialog,container,false);

        setupViews();
        return view;
    }

    private void setupViews() {

        btnBookmark = view.findViewById(R.id.btn_helpBookmark_bookmarks);

        btnBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BookmarkFoodsActivity.class));
            }
        });
    }
}
