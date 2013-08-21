package org.factoryboy.core;

import org.factoryboy.core.sample.Foo;
import org.factoryboy.core.sample.FooFactory;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * FactoryBoy.popBuild()方法测试.
 */
public class PopBuildFactoryTest {

    @Test
    public void testSimplePopBuild() throws Exception {
        FooFactory fooFactory = new FooFactory().name("Jack");
        fooFactory.popBuild(2);
        fooFactory.name("Tom").popBuild();
        List<Foo> fooList = fooFactory.getList();
        System.out.println("fooList = " + fooList);
        assertThat(fooList).hasSize(3);
        assertThat(fooList.get(0).getName()).isEqualTo("Jack");
        assertThat(fooList.get(2).getName()).isEqualTo("Tom");
    }

    @Test
    public void test2PopBuild() throws Exception {
        FooFactory fooFactory = new FooFactory().name("Jack");
        fooFactory.popBuild(2);
        List<Foo> fooList = fooFactory.name("Tom").build(5);
        assertThat(fooList).hasSize(7);
        assertThat(fooList.get(0).getName()).isEqualTo("Jack");
        assertThat(fooList.get(6).getName()).isEqualTo("Tom");
    }
}
