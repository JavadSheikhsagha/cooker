package jvd.ir.cooker.Comments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import jvd.ir.cooker.R;

public class CommentReportDialog extends DialogFragment {

    Button btnReport;

    EditText edtReason;

    View view;

    String Reason;

    SetOnReportClick reportClick;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.reportcomment_dialog,container,false);

        setupViews();

        return view;
    }

    private void setupViews() {

        btnReport = view.findViewById(R.id.btn_report_submit);
        edtReason = view.findViewById(R.id.edt_report_reason);

        Reason = edtReason.getText().toString();

        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportClick.onClick(edtReason.getText().toString());
            }
        });
    }

    public void setReportClick(SetOnReportClick reportClick) {
        this.reportClick = reportClick;
    }

    public interface SetOnReportClick{
        void onClick(String reason);
    }
}
