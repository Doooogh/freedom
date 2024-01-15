package com.doooogh.common.systemmanagement.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.doooogh.publicused.entity.BaseDateEntity;
import lombok.Data;

import java.util.List;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description 用户
 */

@Data
public class SysUser extends BaseDateEntity {

    private String username;

    private String password;


    private String name;

    private String email;

    private String mobile;

    //状态
    private String userStatus;

    private String englishName;

    @TableField(exist = false)
    private List<SysRole> roleList;

}
