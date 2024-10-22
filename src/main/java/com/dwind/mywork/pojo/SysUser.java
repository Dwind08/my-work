package com.dwind.mywork.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


import java.util.List;


/**
 * SYS_USER
 * 用户信息表
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)

public class SysUser {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;


    private String nickname;


    private Integer gender;


    private String password;


    private Long postId;

    private String empNo;

}
