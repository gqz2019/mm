package com.gqz.mm.dao;

import com.gqz.mm.pojo.Catalog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: mm
 * @description:
 * @author: gqz20
 * @create: 2021-03-26 22:27
 **/
@Repository
public interface CatalogDao {
    Long findCountByCourseId(Integer id) ;

    List<Catalog> findCatalogListByCourseId(Integer id);
}
