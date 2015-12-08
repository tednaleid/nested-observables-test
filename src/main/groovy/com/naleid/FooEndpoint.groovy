package com.naleid

import groovy.util.logging.Slf4j
import ratpack.groovy.handling.GroovyChainAction
import ratpack.handling.Context
import ratpack.jackson.Jackson

import javax.inject.Inject

@Slf4j
class FooEndpoint extends GroovyChainAction {
    private final FooService fooService

    @Inject
    FooEndpoint(FooService fooService) {
        this.fooService = fooService
    }

    private void renderOffers(Context context, List<Map> offers) {
        if (!offers) {
            context.clientError(404)
        } else {
            context.render Jackson.json(offers)
        }
    }

    @Override
    void execute() throws Exception {
        path("offers") { Context context ->
            byMethod {
                get {
                    fooService.findItemsByItemCode(["123", "456"]).toList().subscribe { List<Map> offers ->
                        renderOffers(context, offers)
                    }
                }
            }
        }
    }
}
