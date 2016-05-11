/*
 * Copyright (C) 2016 Taeho Kim <jyte82@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.androidhuman.circlerefreshlayout.rxbinding;

import com.androidhuman.circlerefreshlayout.SwipeRefreshLayout;

import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

import static rx.android.MainThreadSubscription.verifyMainThread;

final class SwipeRefreshLayoutRefreshOnSubscribe implements Observable.OnSubscribe<Void> {

    final SwipeRefreshLayout view;

    SwipeRefreshLayoutRefreshOnSubscribe(SwipeRefreshLayout view) {
        this.view = view;
    }

    @Override
    public void call(final Subscriber<? super Void> subscriber) {
        verifyMainThread();

        SwipeRefreshLayout.OnRefreshListener listener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                subscriber.onNext(null);
            }
        };
        view.setOnRefreshListener(listener);

        subscriber.add(new MainThreadSubscription() {
            @Override
            protected void onUnsubscribe() {
                view.setOnRefreshListener(null);
            }
        });
    }
}
