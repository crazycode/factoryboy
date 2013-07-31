package org.factoryboy.core;

import org.factoryboy.core.sample.Foo;
import org.factoryboy.core.sample.FooDAO;
import org.factoryboy.core.sample.FooFactory;
import org.factoryboy.core.sample.FooService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Mock测试
 */
@RunWith(MockitoJUnitRunner.class)
public class MockFooServiceTest {

    @Mock
    FooDAO fooDAO;

    @InjectMocks
    FooService fooService;

    @Test
    public void testFindAllWithoutFactoryBoy() throws Exception {
        List<Foo> sampleFooList = new ArrayList<Foo>();
        Foo foo1 = new Foo();
        foo1.setName("第1位员工");
        foo1.setAge(30);
        sampleFooList.add(foo1);
        Foo foo2 = new Foo();
        foo2.setName("第2位员工");
        foo2.setAge(30);
        sampleFooList.add(foo2);
        when(fooDAO.findAll()).thenReturn(sampleFooList);

        List<Foo> fooList = fooService.findAll();
        assertThat(fooList).isNotEmpty().hasSize(2);
        assertThat(fooList.get(0).getName())
                .startsWith("第").endsWith("位员工");
        assertThat(fooList.get(1).getAge()).isEqualTo(30);
    }

    FooFactory fooFactory;

    @Before
    public void setUp() throws Exception {
        // 设置生成Foo的默认age是30;
        fooFactory = new FooFactory().age(30);
    }

    @Test
    public void testFindAll() throws Exception {
        // Mock返回30个员工数据
        when(fooDAO.findAll()).thenReturn(
                fooFactory.name("第%d位员工").age(30)
                        .build(30));

        List<Foo> fooList = fooService.findAll();
        assertThat(fooList).isNotEmpty().hasSize(30);
        assertThat(fooList.get(0).getName())
                .startsWith("第").endsWith("位员工");
        assertThat(fooList.get(3).getAge()).isEqualTo(30);
    }

    @Test
    public void testFindById() throws Exception {
        when(fooDAO.findById(eq(9527L))).thenReturn(fooFactory.name("周星星").age(51).build());
        Foo zxx = fooService.findById(9527L);
        assertThat(zxx.getName()).isEqualTo("周星星");
        assertThat(zxx.getAge()).isEqualTo(51);
        verify(fooDAO, times(1)).findById(9527L);
        verify(fooDAO, never()).findById(eq(1L));
    }

    @Test
    public void testFindByIdNull() throws Exception {
        assertThat(fooService.findById(99l)).isNull();
        verify(fooDAO, never()).findById(any(Long.class));
    }
}
