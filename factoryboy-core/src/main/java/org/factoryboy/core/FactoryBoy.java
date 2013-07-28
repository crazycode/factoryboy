package org.factoryboy.core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 主工厂基类.
 * @param <T>
 */
public abstract class FactoryBoy<T> {

    protected List<Mold<T>> _moldList = new ArrayList<Mold<T>>();

    public abstract T defaultObject();

    protected T _object;
    private Integer sequence;

    private T newObject() {
        if (_object == null) {
            this._object = defaultObject();
            this.sequence = nextSequence(this.getClass());
        }
        return _object;
    }

    public synchronized Integer currentSequenct() {
        if (sequence == null) {
            return nextSequence(this.getClass());
        }
        return sequence;
    }

    public synchronized Integer nextSequence(Class<?> clazz) {
        if (sequence == null) {
            sequence = 0;
        }
        return sequence++;
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

    public T create() {
        T t = build();
        // TODO: 执行数据库插入操作
        return t;
    }

    public List<T> create(int number) {
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < number; i++) {
            list.add(create());
        }
        return list;
    }

    /**
     * 返回最后一个值对象.
     * @return
     */
    public T last() {
        return null; //TODO
    }

    public T lastOrBuild() {
        return null; //TODO
    }

    public Set<T> buildSet(int number) {
        Set<T> set = new HashSet<T>();
        for (int i = 0; i < number; i++) {
            set.add(build());
        }
        return set;
    }

    /**
     * 安装模具.
     */
    public <MF extends FactoryBoy<T>> MF install(MF modelFactory, Mold<T> mold) {
        modelFactory._moldList.add(mold);
        return modelFactory;
    }

    // ------------------ 生成动态值的static import method ---------------------------------
    public static SequenceValue<String> format(final String format) {
        return new SequenceValue<String>() {
            @Override
            public String get() {
                return String.format(format, getFactoryBoy().currentSequenct());
            }
        };
    }

    public static SequenceValue<Integer> increaseFrom(final Integer baseValue) {
        return new SequenceValue<Integer>() {
            @Override
            public Integer get() {
                return baseValue + getFactoryBoy().currentSequenct();
            }
        };
    }

    public static SequenceValue<Integer> decreaseFrom(final Integer baseValue) {
        return new SequenceValue<Integer>() {
            @Override
            public Integer get() {
                return baseValue - getFactoryBoy().currentSequenct();
            }
        };
    }


    public static SequenceValue<Long> increaseFrom(final Long baseValue) {
        return new SequenceValue<Long>() {
            @Override
            public Long get() {
                return baseValue + getFactoryBoy().currentSequenct();
            }
        };
    }

    public static SequenceValue<Long> decreaseFrom(final Long baseValue) {
        return new SequenceValue<Long>() {
            @Override
            public Long get() {
                return baseValue - getFactoryBoy().currentSequenct();
            }
        };
    }

    public static SequenceValue<BigDecimal> increaseFrom(final BigDecimal baseValue) {
        return new SequenceValue<BigDecimal>() {
            @Override
            public BigDecimal get() {
                return baseValue.add(new BigDecimal(getFactoryBoy().currentSequenct()));
            }
        };
    }

    public static SequenceValue<BigDecimal> decreaseFrom(final BigDecimal baseValue) {
        return new SequenceValue<BigDecimal>() {
            @Override
            public BigDecimal get() {
                return baseValue.subtract(new BigDecimal(getFactoryBoy().currentSequenct()));
            }
        };
    }

    public <V extends SequenceValue<?>> V withThis(V seqValue) {
        seqValue.setFactoryBoy(this);
        return seqValue;
    }


    // 考虑把所有static方法放一个单独类，包括DateHelper
    // 使用 Mockito.doThrow(new Exception()).when(instance).methodName(); 看上去也不能级联，不优雅

    public static String contentFrom(String fileName) {
        // TODO
        return null;
    }

}
