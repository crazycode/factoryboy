package org.factoryboy.core;

/**
 * User: tanglq
 * Date: 13-7-23
 * Time: 下午11:01
 */
public interface Mold<T> {
    public void build(T t);
}
