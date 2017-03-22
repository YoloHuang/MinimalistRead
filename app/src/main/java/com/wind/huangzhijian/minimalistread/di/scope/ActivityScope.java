package com.wind.huangzhijian.minimalistread.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by huangzhijian on 2017/3/10.
 */

@Scope
@Retention(RUNTIME)
public @interface ActivityScope {
}
