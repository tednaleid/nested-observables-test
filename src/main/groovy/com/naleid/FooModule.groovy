package com.naleid

import com.google.inject.AbstractModule

import static com.google.inject.Scopes.SINGLETON

class FooModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(FooService).in(SINGLETON)
        bind(FooConfig).in(SINGLETON)
        bind(FooEndpoint).in(SINGLETON)
    }
}
