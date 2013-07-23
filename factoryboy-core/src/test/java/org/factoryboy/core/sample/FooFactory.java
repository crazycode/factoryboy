package org.factoryboy.core.sample;

import org.factoryboy.core.SeqValue;
import org.factoryboy.core.FactoryBoy;
import org.factoryboy.core.Mold;

import java.math.BigDecimal;

/**
 * User: tanglq
 * Date: 13-7-23
 * Time: 下午4:26
 */
public class FooFactory extends FactoryBoy<Foo> {
    @Override
    public Foo defaultObject() {
        Foo foo = new Foo();
        foo.setAmount(BigDecimal.ONE);
        return foo;
    }

    public FooFactory name(final String value) {
        return install(this, new Mold<Foo>() {
            @Override
            public void build(Foo foo) {
                foo.setName(value);
            }
        });
    }

    public FooFactory name(final SeqValue<String> seqValue) {
        seqValue.setFactoryBoy(this);
        return install(this, new Mold<Foo>() {
            @Override
            public void build(Foo foo) {
                foo.setName(seqValue.value());
            }
        });
    }
}