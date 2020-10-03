package com.example.rxjavasample.rx.java;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class HelloWorld {
    Flowable<String> flowable =
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                String[] dataList = { "Hello, World!", "こんにちは、世界！" };
                for (String data : dataList) {
                    if (emitter.isCancelled()) {
                        return;
                    }

                    emitter.onNext(data);
                }

                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER);
}
