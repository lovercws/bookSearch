package com.kingbase.bookSearch.core.webservice.cxf.service.hello;

import javax.jws.WebService;

@WebService
public class HelloWorld implements IHello {

	public String sayHello() {
		return "Hello world!";
	}
}
