package com.etroya.learning.rxjava.observer;
import io.reactivex.Observer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.reactivex.disposables.Disposable;


public class DemoObserver implements Observer {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoObserver.class);

    @Override
    public void onSubscribe(Disposable disposable) {
        LOGGER.info("onSubscribe");

    }

    @Override
    public void onError(Throwable throwable) {
        LOGGER.error("on error {}", throwable);

    }

    @Override
    public void onComplete() {
        LOGGER.info("onComplete");

    }

    @Override
    public void onNext(Object o) {
        LOGGER.info("onNext {}", o.toString());

    }
}
