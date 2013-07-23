package org.factoryboy.core;

import org.apache.commons.beanutils.BeanUtils;

public abstract class ModelFactory<T> {

    public abstract T define();

    protected T _object;

    public T theOne() {
        if (_object == null) {
            this._object = define();
        }
        return _object;
    }

    public T build() throws Exception {
        T t = define();
        BeanUtils.copyProperties(t, theOne());
        return t;
    }

}
