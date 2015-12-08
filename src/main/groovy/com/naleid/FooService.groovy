package com.naleid

import groovy.util.logging.Slf4j
import ratpack.exec.Promise

import javax.inject.Inject
import rx.Observable

@Slf4j
class FooService {
    FooConfig fooConfig

    @Inject
    public FooService(FooConfig fooConfig) {
        this.fooConfig = fooConfig
    }
    /*
    // Starting from:

    return Promise.of { g ->
      List results = []
      stringList.each { String string ->
          somePromise.getPromise([key: string]).then { List<Map> results ->
          results.each { Map map ->
          	results << map
          }
        }
      }

      g.success(results)
    }
     */

    Observable<Map> findItemsByItemCode(List<String> itemCodes) {
        Observable.from(itemCodes).flatMap(this.&selectMany).flatMap(Observable.&from)
    }

    Observable<List<Map>> selectMany(String itemCode) {
        // hand waving retrieving collection from DB wrapped in Observable
        Observable.just([
                [code: itemCode, value: 1, price: 1.99],
                [code: itemCode, value: 2, price: 4.77]
        ])
    }

}
