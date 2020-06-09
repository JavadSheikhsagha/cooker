package jvd.ir.cooker.Comments.AddComment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import jvd.ir.cooker.Comments.SignInDialog;
import jvd.ir.cooker.LoginActivity.LoginActivity;
import jvd.ir.cooker.R;

public class AddCommentDialog extends DialogFragment {

    View view;

    Button btnSubmit;

    TextView txtUserName;

    EditText edtComment;

    setOnSubmitClick onSubmitClick;

    String userName;

    setOnDialogShow onDialogShow;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.dialog_add_comment,container);
        setupViews();
        return view;
    }

    public AddCommentDialog(String UserName){
        this.userName=UserName;
    }

    private void setupViews() {

        btnSubmit = view.findViewById(R.id.btn_addComment_submit);
        txtUserName = view.findViewById(R.id.txt_addComment_userName);
        edtComment = view.findViewById(R.id.edt_addComment_comment);

        txtUserName.setText(userName);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName.equals("")){
                    Toast.makeText(getActivity(), "ابتدا وارد شوید", Toast.LENGTH_SHORT).show();
                } if (!userName.equals("")){
                    onSubmitClick.onClick(edtComment.getText().toString(),txtUserName.getText().toString());
                }

            }
        });

    }

    public void setOnSubmitClick(setOnSubmitClick onSubmitClick) {
        this.onSubmitClick = onSubmitClick;
    }

    public void setOnDialogShow(setOnDialogShow onDialogShow) {
        this.onDialogShow = onDialogShow;
    }

    public interface setOnDialogShow{
        void onClick();
    }

    public interface setOnSubmitClick{
        void onClick(String comment,String UserName);
    }

}
