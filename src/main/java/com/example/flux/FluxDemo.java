package com.example.flux;

import org.assertj.core.util.Lists;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @Author: Neo.zr
 * @Since: 2026/2/9
 **/
public class FluxDemo {

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList();

        Flux.just(1, 2, 3, 4).subscribe(list::add);

        Assert.isTrue(list.size() == 4, "list size is not 4");

        Flux.just(1, 2, 3, 4).subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription subscription) {

            }

            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
