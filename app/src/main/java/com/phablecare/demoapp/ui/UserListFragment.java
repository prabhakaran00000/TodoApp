package com.phablecare.demoapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phablecare.demoapp.R;
import com.phablecare.demoapp.databinding.FragmentUserListBinding;
import com.phablecare.demoapp.helper.Constants;
import com.phablecare.demoapp.model.UserDetail;
import com.phablecare.demoapp.viewmodels.ViewModelProviderFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class UserListFragment extends DaggerFragment implements UserListAdapter.OnUpDateDeleteButtonClick{

    private FragmentUserListBinding mFragmentUserListBinding;
    private UserListAdapter mUserListAdapter;
    private HomeViewModel mHomeViewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {

        mFragmentUserListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_list,
                container, false);
        initListeners();
        initRecyclerView();
        return mFragmentUserListBinding.getRoot();
    }

    private void initListeners() {
        mFragmentUserListBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAddUpdateFragment(Constants.INTENT_ADD,null);
            }
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = mFragmentUserListBinding.recyclerView;
        mUserListAdapter = new UserListAdapter(new ArrayList<UserDetail>(),getActivity(),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mUserListAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHomeViewModel = ViewModelProviders.of(this, providerFactory).get(HomeViewModel.class);
        mHomeViewModel.fetchUserDetails();
        mHomeViewModel.getAllUserDetials().observe(getViewLifecycleOwner(), new Observer<List<UserDetail>>() {
            @Override
            public void onChanged(List<UserDetail> allUserDetails) {
                if(allUserDetails != null){
                    mUserListAdapter.refreshData(allUserDetails);
                }
            }
        });
        mHomeViewModel.getAddUpdateRemoveStatus().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    Toast.makeText(getActivity(),getString(R.string.removed_successfully),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void startAddUpdateFragment(String intentType, UserDetail userDetail) {
        UserAddUpdateFragment userAddUpdateFragment = new UserAddUpdateFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.INTENT_TYPE,intentType);
        if(intentType.equals(Constants.INTENT_UPDATE)){
            bundle.putSerializable(Constants.DATA_TYPE,userDetail);
        }
        userAddUpdateFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_container, userAddUpdateFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onDeleteButtonClicked(UserDetail userDetail, boolean isUpdate) {
        if(isUpdate){
            startAddUpdateFragment(Constants.INTENT_UPDATE,userDetail);
        }else {
            mHomeViewModel.deleteUserDetail(userDetail);
        }
    }
}
