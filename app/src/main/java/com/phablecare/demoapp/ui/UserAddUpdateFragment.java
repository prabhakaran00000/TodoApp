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

import com.phablecare.demoapp.R;
import com.phablecare.demoapp.databinding.FragmentUserAddRemoveBinding;
import com.phablecare.demoapp.helper.Constants;
import com.phablecare.demoapp.model.UserDetail;
import com.phablecare.demoapp.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class UserAddUpdateFragment extends DaggerFragment {

    private UserDetail mUserDetail;
    private HomeViewModel mHomeViewModel;
    private String mIntentType;
    private FragmentUserAddRemoveBinding mFragmentUserAddRemoveBinding;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mFragmentUserAddRemoveBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_user_add_remove, container, false);
        getRequestedIntentType();
        initListeners();
        return mFragmentUserAddRemoveBinding.getRoot();
    }

    private void getRequestedIntentType() {
        if(getArguments() != null && getArguments().getString(Constants.INTENT_TYPE) != null){
            mIntentType = getArguments().getString(Constants.INTENT_TYPE);
            if(mIntentType.equals(Constants.INTENT_UPDATE)){
                mUserDetail = (UserDetail) getArguments().getSerializable(Constants.DATA_TYPE);
                mFragmentUserAddRemoveBinding.btnAddUpdate.setText(getString(R.string.update));
            }else {
                mUserDetail = new UserDetail();
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
    }

    private void initListeners() {
        mFragmentUserAddRemoveBinding.btnAddUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHomeViewModel.validateUserDetail(mIntentType);
            }
        });
    }

    private void initViewModel(){
        mHomeViewModel = ViewModelProviders.of(this, providerFactory).get(HomeViewModel.class);
        mHomeViewModel.setUserDetail(mUserDetail);
        mFragmentUserAddRemoveBinding.setHomeViewModel(mHomeViewModel);
        mFragmentUserAddRemoveBinding.setUserDetail(mUserDetail);
        mHomeViewModel.getAddUpdateRemoveStatus().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    if(mIntentType.equals(Constants.INTENT_ADD)){
                        Toast.makeText(getActivity(), getString(R.string.added_successfully),
                                Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(), getString(R.string.updated_successfully),
                                Toast.LENGTH_SHORT).show();
                    }
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
