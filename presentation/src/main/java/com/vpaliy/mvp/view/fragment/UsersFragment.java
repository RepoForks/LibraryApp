package com.vpaliy.mvp.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.otto.Bus;
import com.vpaliy.domain.model.UserModel;
import com.vpaliy.mvp.App;
import com.vpaliy.mvp.R;
import com.vpaliy.mvp.di.component.DaggerFragmentComponent;
import com.vpaliy.mvp.di.module.PresenterModule;
import com.vpaliy.mvp.mvp.contract.UserListContract;
import com.vpaliy.mvp.view.adapter.UserAdapter;
import com.vpaliy.mvp.view.utils.Constant;
import com.vpaliy.mvp.view.utils.eventBus.Action;

import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


import static com.vpaliy.mvp.mvp.contract.UserListContract.Presenter;

public class UsersFragment extends Fragment
        implements UserListContract.View {

    private Presenter presenter;
    private UserAdapter adapter;

    @Inject
    protected Bus eventBus;

    @BindView(R.id.recycleView)
    protected RecyclerView userList;

    @BindView(R.id.actionButton)
    protected FloatingActionButton actionButton;

    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeInjector();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.stop();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter.onAttachView(this);
        View root=inflater.inflate(R.layout.fragment_models,container,false);
        unbinder=ButterKnife.bind(this,root);
        ButterKnife.setDebug(true);
        actionButton=ButterKnife.findById(root,R.id.actionButton);
        actionButton.setOnClickListener(this::addClick);
        return root;
    }

    private void initializeInjector() {
        DaggerFragmentComponent.builder()
                .applicationComponent(App.app().provideAppComponent())
                .presenterModule(new PresenterModule())
                .build().inject(this);
    }

    @Override
    @Inject
    public void attachPresenter(@NonNull Presenter presenter) {
        this.presenter=presenter;
    }

    @Override
    public void showLoadingError() {

    }


    @Override
    public void showDeleteUser() {

    }

    @Override
    public void showUserList(@NonNull List<UserModel> userModelList) {
        adapter=new UserAdapter(getContext(),userModelList);
        userList.setLayoutManager(new GridLayoutManager(getContext(),
                getResources().getInteger(R.integer.spanCount),
                GridLayoutManager.VERTICAL,false));
        userList.setAdapter(adapter);
    }

    @Override
    public void appendUserList(@NonNull List<UserModel> userModelList) {
        adapter.appendData(userModelList);
    }

    @Override
    public void setLoadingIndicator(boolean isVisible) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void switchToBooks() {
        eventBus.post(new Action<Void>(Constant.SWAP_TO_BOOKS));
    }

    @OnClick(R.id.actionButton)
    public void addClick(View view) {
        presenter.addUser();
    }

    @Override
    public void addUserAction() {
        eventBus.post(new Action<Void>(Constant.ADD_USER));
    }
}
