package com.gqz.mm.dao;

import com.gqz.mm.pojo.Catalog;

import java.util.List;

/**
 * @program: mm
 * @description:
 * @author: gqz20
 * @create: 2021-03-26 22:27
 **/
public interface CatalogDao {
    public Long findCountByCourseId(Integer id) ;

    List<Catalog> findCatalogListByCourseId(Integer id);
}
