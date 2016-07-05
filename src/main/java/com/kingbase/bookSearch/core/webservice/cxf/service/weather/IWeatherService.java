package com.kingbase.bookSearch.core.webservice.cxf.service.weather;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 天气服务
 * @author ganliang
 */
@WebService
public interface IWeatherService {

	/**
	 * 获得国内手机号码归属地省份、地区和手机卡类型信息
	 * @param mobileCode 手机号码，最少前7位数字
	 * @param userID 商业用户ID） 免费用户为空字符串
	 * @return 返回数据：字符串（手机号码：省份 城市 手机卡类型）
	 */
	public String get(@WebParam(name="mobileCode") String mobileCode,@WebParam(name="userID")String userID);
	
	/**
	 * 获得国内手机号码归属地数据库信息
	 * @return 一维字符串数组（省份 城市 记录数量）
	 */
	public String[] getDatabaseInfo();
}
