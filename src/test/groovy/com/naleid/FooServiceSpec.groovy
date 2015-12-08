package com.naleid

import ratpack.rx.RxRatpack
import rx.Observable
import spock.lang.Specification

class FooServiceSpec extends Specification {
    FooService fooService
    def setup() {
        RxRatpack.initialize()
        fooService = new FooService(new FooConfig(uri: "http://foo.bar.com"))
    }

    def "Product offer for itemCodes"(){
        given:
        List<String> itemCodes = ["123", "456"]

        when:
        Observable<Map> result = fooService.findItemsByItemCode(itemCodes)

        then:
        result.toList().subscribe { List<Map> offers ->
            println offers
        }
    }
}
