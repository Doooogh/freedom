package com.doooogh.publicused.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description 基础日期类
 */
@Data
public class BaseDateEntity extends BaseEntity {

    private Date createDate;

    private Date updateDate;
}
