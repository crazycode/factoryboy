package org.factoryboy.core;

import org.factoryboy.core.sample.FooFactory;
import org.junit.Before;
import org.junit.Test;

import static org.factoryboy.core.FactoryBoy.format;
import static org.junit.Assert.assertEquals;

/**
 * 变值测试.
 */
public class SequenceFactoryBoyTest {

    FooFactory fooFactory;

    @Before
    public void setUp() throws Exception {
        fooFactory = new FooFactory().name("World");
    }

    @Test
    public void testOriginName() throws Exception {
        assertEquals("World", fooFactory.build().getName());
    }

    @Test
    public void testSeqName() throws Exception {
        int baseSeq = fooFactory.currentSequenct();
        assertEquals("Hello" + (baseSeq + 1), fooFactory.name(format("Hello%d")).build().getName());
    }

    @Test
    public void testSeqName2() throws Exception {
        int baseSeq = fooFactory.currentSequenct();
        assertEquals((baseSeq + 1) + "Factory", fooFactory.name(format("%dFactory")).build().getName());
    }
}
