package com.example.rxsubjectdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "myApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        asyncSubjectDemo1();
        asyncSubjectDemo2();
    }

    void asyncSubjectDemo1() {
        Observable<String> observable = Observable.just("JAVA", "KOTLIN", "XML", "JSON");
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        AsyncSubject<String> asyncSubject = AsyncSubject.create();
        observable.subscribe(asyncSubject);

        asyncSubject.subscribe(getFirstObserver());
        asyncSubject.subscribe(getSecondObserver());
        asyncSubject.subscribe(getThirdObserver());
    }

    void asyncSubjectDemo2() {

        AsyncSubject<String> asyncSubject = AsyncSubject.create();


        asyncSubject.subscribe(getFirstObserver());

        asyncSubject.onNext("JAVA");
        asyncSubject.onNext("KOTLIN");
        asyncSubject.onNext("XML");

        asyncSubject.subscribe(getSecondObserver());

        asyncSubject.onNext("JSON");
        asyncSubject.onComplete();

        asyncSubject.subscribe(getThirdObserver());
    }

    private Observer<String> getFirstObserver() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "First Observer onSubscribe ");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "First Observer onNext " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "First Observer onError ");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "First Observer onComplete ");
            }
        };

        return observer;
    }

    private Observer<String> getSecondObserver() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "Second Observer onSubscribe ");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "Second Observer onNext " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "Second Observer onError ");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "Second Observer onComplete ");
            }
        };

        return observer;
    }

    private Observer<String> getThirdObserver() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "Third Observer onSubscribe ");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "Third Observer onNext " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "Third Observer onError ");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "Third Observer onComplete ");
            }
        };

        return observer;
    }
}