package com.wistronits.aml.chat.mapper;

import com.wistronits.aml.chat.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gzp
 * @since 2018-09-19
 */
public interface UserMapper extends BaseMapper<User> {

	List<User> checkByName(@Param("userName") String userName, @Param("userId") String userId);

	User getUser(String userName);

	User getByUserId(@Param("userId") String userId);

	void updateByUserId(@Param("user") User user);

	void updatePwd(@Param("user") User user);
}
