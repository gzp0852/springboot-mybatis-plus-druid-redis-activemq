package com.wistronits.aml.chat.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author gzp
 * @since 2018-09-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String userCode;

    private String userName;

    private String password;

    private String sex;

    private String age;

    private String profile;
    /**
     * 图片路劲
     */
    private String profilehead;

    private String registerTime;

    private String lastTime;

    private String status;

    private String lastIp;

    /**
     * 是否被删除  1代表被删除   -1不可删除
     */
    private String deleted;


}
