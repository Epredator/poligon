package com.etroya.learning.rxjava.operators.transforming;

import com.etroya.learning.rxjava.models.Shape;
import com.etroya.learning.rxjava.utils.RxUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroupByOperator {
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupByOperator.class);

    public static void main(String[] args) {
        Observable.fromIterable(RxUtils.shapes(20))
                .groupBy(new Function<Shape, Object>() {
                    @Override
                    public Object apply(Shape shape) throws Exception {
                        return shape.getShape();
                    }
                }).observeOn(Schedulers.newThread())
                .subscribe(new Observer<GroupedObservable<Object, Shape>>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(GroupedObservable<Object, Shape> objectShapeGroupedObservable) {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                })
    }
}
