package org.factoryboy.core;

/**
 * User: tanglq
 * Date: 13-7-23
 * Time: 下午11:30
 */
public abstract class SeqValue<T> {

    FactoryBoy<?> _factoryBoy;

    public SeqValue<T> setFactoryBoy(FactoryBoy value) {
        _factoryBoy = value;
        return this;
    }

    protected FactoryBoy<?> getFactoryBoy() {
        if (_factoryBoy == null) {
            throw new RuntimeException("Please call DynaValue.setFactoryBoy() first!");
        }
        return _factoryBoy;
    }

    public abstract T value();
}
