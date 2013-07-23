package org.factoryboy.core;

import junit.framework.AssertionFailedError;
import org.factoryboy.core.callback.BuildCallback;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class FactoryBoy {

    protected static Map<Class<?>, ModelFactory<?>> modelFactoryCacheMap = new HashMap<Class<?>, ModelFactory<?>>();

    protected static Map<Class<?>, Integer> modelSequenceMap = new HashMap<Class<?>, Integer>();

    protected static ThreadLocal<Set<Class<?>>> _threadLocalModelDeletedSet = new ThreadLocal<Set<Class<?>>>();

    protected static ThreadLocal<Map<Class<?>, Object>> _lastObjectMap = new ThreadLocal<Map<Class<?>, Object>>();


    public static <T> T with(Class<T> clazz) {
        return null;
    }

    protected static synchronized Set<Class<?>> modelDeletedSet() {
        Set<Class<?>> modelDeletedSet = _threadLocalModelDeletedSet.get();
        if (modelDeletedSet == null) {
            modelDeletedSet = new HashSet<Class<?>>();
            _threadLocalModelDeletedSet.set(modelDeletedSet);
        }
        return modelDeletedSet;
    }

    protected static synchronized Map<Class<?>, Object> lastObjectMap() {
        Map<Class<?>, Object> lastObjectMap = _lastObjectMap.get();
        if (lastObjectMap == null) {
            lastObjectMap = new HashMap<Class<?>, Object>();
            _lastObjectMap.set(lastObjectMap);
        }
        return lastObjectMap;
    }

    protected static void reset() {
        _lastObjectMap.set(null);
        _threadLocalModelDeletedSet.set(null);
    }

    /**
     * Only delete the Model when first call create(...) method.
     */
    public static void lazyDelete() {
        reset();
    }

    public static synchronized <T> ModelFactory<T> findModelFactory(
            Class<T> clazz) {
        // If the Model has not delete after lazyDelete, delete it all.
        ModelFactory<T> modelFactory = (ModelFactory<T>) modelFactoryCacheMap
                .get(clazz);
        if (modelFactory != null) {
            return modelFactory;
        }
        String clazzFullName = clazz.getName();
        String modelFactoryName = clazzFullName.replaceAll("^models\\.",
                "factory.") + "Factory";
        try {
            modelFactory = null; // TODO 找到这个类，或者使用cglib弄出一个类来
            modelFactoryCacheMap.put(clazz, modelFactory);
            return modelFactory;
        } catch (Exception e) {
            throw new RuntimeException("Can't find class:" + modelFactoryName, e);
        }
    }

    /**
     * Create the <i>clazz</i> Object and SAVE it to Database.
     *
     * @param clazz
     * @return
     */
    public static <T> T create(Class<T> clazz) {
        T t = build(clazz);
        saveModelObject(t);
        return t;
    }

    /**
     * Create the named <i>clazz</i> Object and SAVE it to Database.
     *
     * @param clazz
     * @param name
     * @return
     */
    public static <T> T create(Class<T> clazz, String name) {
        T t = build(clazz, name);
        saveModelObject(t);
        return t;
    }

    public static <T> T create(Class<T> clazz,
                                                    String name, BuildCallback<T> buildCallback) {
        T t = build(clazz, name, buildCallback);
        saveModelObject(t);
        return t;
    }

    public static <T> T create(Class<T> clazz,
                                                    BuildCallback<T> buildCallback) {
        T t = build(clazz, buildCallback);
        saveModelObject(t);
        return t;
    }

    /**
     * Build the <i>clazz</i> Object, but NOT save it.
     *
     * @param clazz
     * @return
     */
    public static <T> T build(Class<T> clazz) {
        ModelFactory<T> modelFactory = findModelFactory(clazz);
        T t = modelFactory.define();
        return t;
    }

    /**
     * Build the named <i>clazz</i> Object, but NOT save it.
     *
     * @param clazz
     * @param name
     * @return
     */
    public static <T> T build(Class<T> clazz, String name) {

        ModelFactory<T> modelFactory = findModelFactory(clazz);

        T t = modelFactory.define();

        return t;
    }

    private static <T> T invokeModelFactoryMethod(
            ModelFactory<T> modelFactory, T t, Method baseMethod)
            throws IllegalAccessException, InvocationTargetException {
        int parameterNumber = baseMethod.getParameterTypes().length;
        Object returnObject = null;
        switch (parameterNumber) {
            case 0:
                // void method
                returnObject = baseMethod.invoke(modelFactory, new Object[] {});
                break;
            case 1:
                returnObject = baseMethod.invoke(modelFactory, t);
                break;
            case 2:
                returnObject = baseMethod.invoke(modelFactory, t,
                        sequence(t.getClass()));
        }
        if (returnObject != null) {
            return (T) returnObject;
        }
        return t;
    }

    /**
     * Build the named <i>clazz</i> Object, but NOT save it.
     *
     * @param clazz
     * @param name
     * @return
     */
    public static <T> T build(Class<T> clazz, String name,
                                                   BuildCallback<T> buildCallback) {
        T t = build(clazz, name);
        buildCallback.build(t);
        return t;
    }

    public static <T> T build(Class<T> clazz,
                                                   BuildCallback<T> buildCallback) {
        T t = build(clazz);
        buildCallback.build(t);
        return t;
    }

    /*
     * // TODO public static <T> List<T> batchCreate(int
     * size, Class<T> clazz) { return null; }
     */

    public static <T> List<T> batchCreate(int size,
                                                               Class<T> clazz, BuildCallback<T> sequenceCallback) {
        List<T> list = batchBuild(size, clazz, sequenceCallback);
        for (T t : list) {
            saveModelObject(t);
        }
        return list;
    }

    public static <T> List<T> batchBuild(int size,
                                                              Class<T> clazz, BuildCallback<T> buildCallback) {
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < size; i++) {
            T t = build(clazz);
            buildCallback.build(t);
            list.add(t);
        }
        return list;
    }

    public static <T> List<T> batchCreate(int size,
                                                               Class<T> clazz, String name, BuildCallback<T> buildCallback) {
        List<T> list = batchBuild(size, clazz, name, buildCallback);
        for (T t : list) {
            saveModelObject(t);
        }
        return list;
    }

    private static <T> void saveModelObject(T t) {
        // t.save();  TODO: 是否要保存到数据库？ 提供一个接口
        lastObjectMap().put(t.getClass(), t);
    }

    public static <T> List<T> batchBuild(int size,
                                                              Class<T> clazz, String name, BuildCallback<T> buildCallback) {
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < size; i++) {
            T t = build(clazz, name);
            buildCallback.build(t);
            list.add(t);
        }
        return list;
    }

    public static synchronized int sequence(Class<?> clazz) {
        Integer seq = modelSequenceMap.get(clazz);
        if (seq == null) {
            seq = 0;
        }
        modelSequenceMap.put(clazz, ++seq);
        return seq;
    }

    public static <T> T last(Class<T> clazz) {
        T lastObject = (T) lastObjectMap().get(clazz);
        if (lastObject == null) {
            throw new AssertionFailedError("Can't get the last " + clazz.getName()
                    + " Object, Please call FactoryBoy.create("
                    + clazz.getName() + ".class) at first.");
        }
        return lastObject;
    }

    public static <T> T lastOrCreate(Class<T> clazz) {
        T lastObject = (T) lastObjectMap().get(clazz);
        if (lastObject == null) {
            return create(clazz);
        }
        return lastObject;
    }

    public static <T> T lastOrCreate(Class<T> clazz, String name) {
        T lastObject = (T) lastObjectMap().get(clazz);
        if (lastObject == null) {
            return create(clazz, name);
        }
        return lastObject;
    }

}
