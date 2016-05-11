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

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import rx.Observable;
import rx.functions.Action1;

public final class RxSwipeRefreshLayout {

    /**
     * Create an observable of refresh events on {@code view}.
     * <p>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}.
     * Unsubscribe to free this reference.
     */
    @CheckResult
    @NonNull
    public static Observable<Void> refreshes(@NonNull SwipeRefreshLayout view) {
        checkNotNull(view, "view == null");
        return Observable.create(new SwipeRefreshLayoutRefreshOnSubscribe(view));
    }

    /**
     * An action which sets whether the layout is showing the refreshing indicator.
     * <p>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}.
     * Unsubscribe to free this reference.
     */
    @CheckResult @NonNull
    public static Action1<? super Boolean> refreshing(@NonNull final SwipeRefreshLayout view) {
        checkNotNull(view, "view == null");
        return new Action1<Boolean>() {
            @Override public void call(Boolean value) {
                view.setRefreshing(value);
            }
        };
    }

    private static <T> T checkNotNull(T value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
        return value;
    }

    private RxSwipeRefreshLayout() {
        throw new AssertionError("No instances.");
    }
}
