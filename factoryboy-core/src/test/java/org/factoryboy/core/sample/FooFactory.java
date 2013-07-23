package org.factoryboy.core.sample;

import org.factoryboy.core.ModelFactory;

import java.math.BigDecimal;

/**
 * User: tanglq
 * Date: 13-7-23
 * Time: 下午4:26
 */
public class FooFactory extends ModelFactory<Foo> {
    @Override
    public Foo define() {
        Foo foo = new Foo();
        foo.setAmount(BigDecimal.ONE);
        //foo.setCreatedAt(new Date());
        return foo;
    }

    public FooFactory setName(String value) {
        theOne().setName(value);
        return this;
    }
}
