package org.factoryboy.core;

/**
 * 模具，用于修改对象的部分数值.
 * @param <T>
 */
public interface Mold<T> {
    public void build(T t);
}
