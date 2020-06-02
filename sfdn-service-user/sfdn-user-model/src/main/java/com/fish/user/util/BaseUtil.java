package com.fish.user.util;

import com.alibaba.dubbo.rpc.RpcContext;
import com.fish.core.commons.Constants;
import com.fish.core.model.ResponseEntity;
import com.fish.core.utils.HttpSessionUtil;
import com.fish.core.utils.RedissionUtils;
import com.fish.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.fish.core.commons.Constants.*;
import static java.lang.Boolean.FALSE;
import static java.util.Arrays.asList;

/**
 * @Description : Controller数据操作接口
 * @Project : finance-user-model
 * @Program Name : com.hongkun.finance.user.utils.BaseUtil.java
 * @Author : zhichaoding@hongkun.com zc.ding
 */
public interface BaseUtil {
	String LOCK_SUFFIX = "LOCK_KEY";
	/**
	 * 普通的异常提示语
	 */
	 String commExceptionMsg = "网络异常";
	/**
	 * 日志类
	 */
	Logger LOG = LoggerFactory.getLogger(BaseUtil.class);

	/**
	 * @description 从缓存中获取user
	 * @return com.fish.user.model.User
	 * @date 2020/6/2 23:08
	 * @author hh
	 */
	static User getLoginUser() {
		User user = (User) HttpSessionUtil.getLoginUser();
		if (user == null) {// session中没有去redis中获取
			user = (User) RedissionUtils.getInstance().getRedissionClient().getBucket(
					User.class.getSimpleName() + user.getId()).get();
		}
		return user;
	}

	/**
	 * @Description : 获得登录用户的id
	 * @Method_Name : getLoginUserId
	 * @return : Integer
	 * @Creation Date : 2018年3月9日 上午10:57:41
	 * @Author : zhichaoding@hongkun.com zc.ding
	 */
	static Integer getLoginUserId() {
		return getLoginUser().getId();
	}

	/**
	 * @description 从redis中获取，获取不到，通过supplier从db中加载
	 * @param supplier
	 * @return com.fish.user.model.User
	 * @date 2020/6/2 23:17
	 * @author hh
	 */
	static User getUser(Supplier<User> supplier) {
		return (User) tryLoadObjectRedis(User.class.getSimpleName() + BaseUtil.getLoginUser().getId(),
				User.class, supplier);
	}

	/**
	 * @description 从redis中获取，获取不到，换其他方式
	 * @param key
	 * @param clazz
	 * @param ifNotBackup
	 * @return java.lang.Object
	 * @date 2020/6/2 23:18
	 * @author hh
	 */
	static Object tryLoadObjectRedis(String key, Class<?> clazz, Supplier<?> ifNotBackup) {
		Assert.notNull(key, "键值不能为null");
		return (User) RedissionUtils.getInstance().getRedissionClient().getBucket(
				User.class.getSimpleName() + BaseUtil.getLoginUser().getId()).get();
	}
}
