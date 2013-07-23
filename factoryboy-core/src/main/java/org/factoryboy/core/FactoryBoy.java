package org.factoryboy.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class FactoryBoy<T> {

    protected static Map<Class<?>, Integer> modelSequenceMap = new HashMap<Class<?>, Integer>();

    protected List<Mold<T>> _moldList = new ArrayList<Mold<T>>();

    public abstract T defaultObject();

    protected T _object;
    public Integer sequence;

    private T newObject() {
        if (_object == null) {
            this._object = defaultObject();
            this.sequence = nextSequence(this._object.getClass());
        }
        return _object;
    }

    public static synchronized int nextSequence(Class<?> clazz) {
        Integer seq = modelSequenceMap.get(clazz);
        if (seq == null) {
            seq = 0;
        }
        modelSequenceMap.put(clazz, ++seq);
        return seq;
    }

    /**
     * 生成单个对象.
     */
    public T build() {
        T t = newObject();
        for (Mold<T> mold : _moldList) {
            mold.build(t);
        }
        return t;
    }

    public List<T> build(int number) {
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < number; i++) {
            list.add(build());
        }
        return list;
    }

    /**
     * 安装模具.
     */
    public <MF extends FactoryBoy<T>> MF install(MF modelFactory, Mold<T> mold) {
        modelFactory._moldList.add(mold);
        return modelFactory;
    }

    // ------------------ 生成动态值的static import method ---------------------------------
    public static SeqValue<String> format(final String format) {
        return new SeqValue<String>() {
            @Override
            public String value() {
                return String.format(format, getFactoryBoy().sequence);
            }
        };
    }

    public static SeqValue<Integer> increaseBase(final Integer baseValue) {
        return new SeqValue<Integer>() {
            @Override
            public Integer value() {
                return baseValue + getFactoryBoy().sequence;
            }
        };
    }
}
