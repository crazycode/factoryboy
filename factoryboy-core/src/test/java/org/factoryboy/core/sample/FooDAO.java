package org.factoryboy.core.sample;

import java.util.List;

public interface FooDAO {

    /**
     * 查询所有对象
     */
    List<Foo> findAll();

    /**
     * 按主键查询对象
     */
    Foo findById(Long id);
}
