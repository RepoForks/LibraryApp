package com.vpaliy.data.source.remote;


import android.support.annotation.NonNull;

import com.vpaliy.data.entity.UserEntity;
import com.vpaliy.data.source.DataSource;
import com.vpaliy.data.specification.Specification;

import java.util.List;


public class RemoteUserRepository implements DataSource<UserEntity,Specification> {

    @Override
    public void add(UserEntity item) {

    }

    @Override
    public void add(Iterable<UserEntity> items) {

    }

    @Override
    public List<UserEntity> query(@NonNull Specification specification) {
        return null;
    }

    @Override
    public void update(UserEntity item) {

    }

    @Override
    public void update(@NonNull UserEntity item, @NonNull Specification specification) {

    }

    @Override
    public UserEntity get(@NonNull Specification specification) {
        return null;
    }
}