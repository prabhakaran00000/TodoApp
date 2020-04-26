package com.phablecare.demoapp.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.phablecare.demoapp.R;
import com.phablecare.demoapp.databinding.RowUserDetailBinding;
import com.phablecare.demoapp.model.UserDetail;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserDetailHolder> {

    private List<UserDetail> mAllUserDetials;
    private Context mContext;
    private OnUpDateDeleteButtonClick mOnUpDateDeleteButtonClick;

    public UserListAdapter(ArrayList<UserDetail> userDetails, Context context,
                           OnUpDateDeleteButtonClick OnUpDateDeleteButtonClick) {
        mContext = context;
        mAllUserDetials = userDetails;
        mOnUpDateDeleteButtonClick = OnUpDateDeleteButtonClick;
    }

    @NonNull
    @Override
    public UserDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RowUserDetailBinding rowUserListBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.row_user_detail, parent, false);
        return new UserDetailHolder(rowUserListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserDetailHolder holder, int position) {
        UserDetail userDetail = mAllUserDetials.get(position);
        holder.bind(userDetail);
    }

    @Override
    public int getItemCount() {
        return mAllUserDetials.size();
    }

    public void refreshData(List<UserDetail> allUserDetials) {
        mAllUserDetials = allUserDetials;
        notifyDataSetChanged();
    }

    public class UserDetailHolder extends RecyclerView.ViewHolder {

        private final RowUserDetailBinding mRowUserDetailBinding;

        public UserDetailHolder(@NonNull RowUserDetailBinding rowUserDetailBinding) {
            super(rowUserDetailBinding.getRoot());
            mRowUserDetailBinding = rowUserDetailBinding;

            mRowUserDetailBinding.cardviewRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  showAlertDialog();
                }
            });

        }

        public void bind(UserDetail userDetail) {
            mRowUserDetailBinding.setUserDetail(userDetail);
        }

        private void showAlertDialog() {
            new MaterialAlertDialogBuilder(mContext)
                    .setTitle(mContext.getString(R.string.dialog_title))
                    .setPositiveButton(mContext.getString(R.string.update), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mOnUpDateDeleteButtonClick.onDeleteButtonClicked(mAllUserDetials
                                    .get(getAdapterPosition()),true);
                        }
                    })
                    .setNegativeButton(mContext.getString(R.string.delete), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mOnUpDateDeleteButtonClick.onDeleteButtonClicked(mAllUserDetials
                                    .get(getAdapterPosition()),false);
                        }
                    }).show();
        }
    }

    public interface OnUpDateDeleteButtonClick {
        void onDeleteButtonClicked(UserDetail userDetail, boolean isUpdate);
    }

}
