package com.doooogh.publicused.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description 基础类
 */
@Data
public class BaseEntity implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
}
