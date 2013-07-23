package org.factoryboy.core;

import org.factoryboy.core.sample.Foo;
import org.factoryboy.core.sample.FooFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: tanglq
 * Date: 13-7-23
 * Time: 下午4:20
 */
public class FactoryBoyTest {

    @Test
    public void testBuildSingleObject() throws Exception {
        Foo foo = (new FooFactory()).setName("Hello").build();
        assertEquals("Hello", foo.getName());
    }
}
