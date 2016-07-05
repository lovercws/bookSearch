package com.kingbase.bookSearch.core.webservice.cxf.service.weather;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 天气服务实现类
 * 
 * @author ganliang
 */
@WebService
public class WeatherServiceImpl implements IWeatherService {

	@Override
	public String get(@WebParam(name = "mobileCode") String mobileCode, @WebParam(name = "userID") String userID) {
		return null;
	}

	@Override
	public String[] getDatabaseInfo() {
		return null;
	}

}
