package org.factoryboy.core.sample;

import org.factoryboy.core.SequenceValue;
import org.factoryboy.core.FactoryBoy;
import org.factoryboy.core.Mold;

import java.math.BigDecimal;

/**
 * Foo工厂.
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

    public FooFactory name(final SequenceValue<String> seqValue) {
        seqValue.setFactoryBoy(this);
        return install(this, new Mold<Foo>() {
            @Override
            public void build(Foo foo) {
                foo.setName(seqValue.get());
            }
        });
    }
}
