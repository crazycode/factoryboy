package org.factoryboy.core;

import org.factoryboy.core.sample.Foo;
import org.factoryboy.core.sample.FooFactory;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * User: tanglq
 * Date: 13-7-23
 * Time: 下午4:20
 */
public class FactoryBoyTest {

    @Test
    public void testBuildSingleObject() throws Exception {
        Foo foo = new FooFactory().name("Hello").build();
        assertEquals("Hello", foo.getName());
    }

    @Test
    public void testBuildListObject() throws Exception {
        List<Foo> fooList = new FooFactory().name("World").build(30);
        assertEquals(30, fooList.size());
        for (Foo foo : fooList) {
            assertEquals("World", foo.getName());
        }
    }
}
