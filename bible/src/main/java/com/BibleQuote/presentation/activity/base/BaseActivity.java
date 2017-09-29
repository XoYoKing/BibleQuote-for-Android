/*
 * Copyright (C) 2011 Scripture Software
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Project: BibleQuote-for-Android
 * File: BaseActivity.java
 *
 * Created by Vladimir Yakushev at 9/2017
 * E-mail: ru.phoenix@gmail.com
 * WWW: http://www.scripturesoftware.org
 */

package com.BibleQuote.presentation.activity.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.BibleQuote.R;
import com.BibleQuote.di.component.ActivityComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseActivity<T extends BasePresenter> extends BQActivity implements BaseView {

    private static final int DELAY_SHOW_PROGRESS = 500;

    @Inject protected T presenter;

    private ProgressDialog progressDialog;
    private Handler progressHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getRootLayout());
        ButterKnife.bind(this);

        inject(getActivityComponent());
        attachView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onViewCreated();
    }

    @Override
    public void showProgress(boolean cancelable) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setCancelable(cancelable);
        progressDialog.setMessage(getString(R.string.messageLoad));
        if (!progressDialog.isShowing()) {
            progressHandler.postDelayed(() -> progressDialog.show(), DELAY_SHOW_PROGRESS);
        }
    }

    @Override
    public void hideProgress() {
        progressHandler.removeCallbacksAndMessages(null);
        if (!isFinishing() && progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            progressDialog = null;
        }
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public Scheduler backgroundThread() {
        return Schedulers.newThread();
    }

    @Override
    public Scheduler mainThread() {
        return AndroidSchedulers.mainThread();
    }

    protected abstract void attachView();

    protected abstract int getRootLayout();

    protected abstract void inject(ActivityComponent component);

}
