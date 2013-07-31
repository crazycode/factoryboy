package org.factoryboy.core.sample;

import java.util.List;

/**
 * User: tanglq
 * Date: 13-7-29
 * Time: 下午4:06
 */
public class FooService {
    FooDAO fooDAO;

    public List<Foo> findAll() {
        return fooDAO.findAll();
    }

    public Foo findById(Long id) {
        if (id < 1000l) {
            return null;
        }
        return fooDAO.findById(id);
    }
}
