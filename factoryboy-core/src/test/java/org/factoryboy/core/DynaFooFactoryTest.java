package org.factoryboy.core;

import org.factoryboy.core.sample.Foo;
import org.factoryboy.core.sample.FooFactory;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * 测试动态设置属性值的方法.
 */
public class DynaFooFactoryTest {

    @Test
    public void testSetFixedValue() throws Exception {
        Foo foo = new FooFactory().set("name", "Tom").build();
        assertThat(foo.getName()).isEqualTo("Tom");
    }

    @Test
    public void testSetDynaValue() throws Exception {
        Foo foo = new FooFactory().set("name", "Tom%dcat").build();
        assertThat(foo.getName()).startsWith("Tom").endsWith("cat");
    }
}
