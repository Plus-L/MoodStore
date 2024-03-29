/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the moodstore4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.kci.moodstore.admin.service;

import com.kci.moodstore.admin.api.dto.AppSmsDTO;
import com.kci.moodstore.common.core.util.R;

/**
 * @author lengleng
 * @date 2018/11/14
 */
public interface AppService {

	/**
	 * 发送手机验证码
	 * @param sms phone
	 * @return code
	 */
	R<Boolean> sendSmsCode(AppSmsDTO sms);

	/**
	 * 校验验证码
	 * @param phone 手机号
	 * @param code 验证码
	 * @return
	 */
	boolean check(String phone, String code);

}
