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
public class UserFriends implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String friendId;

    private String bestFriendId;


}
