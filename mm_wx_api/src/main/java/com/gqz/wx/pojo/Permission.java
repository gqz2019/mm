package com.gqz.wx.pojo;

import lombok.Data;

/**
 * @author ：yp
 * @description : 权限POJO类
 * @version: 1.0
 */
@Data
public class Permission {
  private Integer id;
  private String name;
  private String keyword;
  private String description;
}
