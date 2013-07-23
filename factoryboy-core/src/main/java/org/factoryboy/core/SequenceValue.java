package org.factoryboy.core;

/**
 * 提供变值对象的操作，实现方式有点丑陋。
 */
public abstract class SequenceValue<T> {

    FactoryBoy<?> _factoryBoy;

    public SequenceValue<T> setFactoryBoy(FactoryBoy value) {
        _factoryBoy = value;
        return this;
    }

    protected FactoryBoy<?> getFactoryBoy() {
        if (_factoryBoy == null) {
            throw new RuntimeException("Please call DynaValue.setFactoryBoy() first!");
        }
        return _factoryBoy;
    }

    public abstract T get();
}
