package com.vpaliy.mvp.view.utils.eventBus;


import android.support.annotation.Nullable;

public class Action<T>{

    @Nullable
    private  T data;

    private final int actionCode;

    public Action(final int actionCode) {
        this.actionCode=actionCode;
    }

    public Action(@Nullable T data, int actionCode) {
        this.data=data;
        this.actionCode=actionCode;
    }

    @Nullable
    public T getData() {
        return data;
    }

    public int getActionCode() {
        return actionCode;
    }
}
