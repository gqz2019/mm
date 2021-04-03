package com.gqz.mm.dao;

import com.gqz.mm.pojo.Tag;

import java.util.List;

/**
 * @program: mm
 * @description:
 * @author: gqz20
 * @create: 2021-03-26 22:27
 **/
public interface TagDao {
    Long findCountByCourseId(Integer id);
    List<Tag> findTagListByCourseId(int courseId);
}