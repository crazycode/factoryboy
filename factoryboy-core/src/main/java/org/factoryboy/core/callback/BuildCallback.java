package org.factoryboy.core.callback;

/**
 * 生成器回调，用于设置一些不同的值.
 * 接下来的设计中应该不再需要回调方式。
 * @author crazycode@gmail.com
 * @param <T>
 */
public interface BuildCallback<T> {
    void build(T target);
}
