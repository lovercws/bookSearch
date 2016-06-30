package com.kingbase.bookSearch.system.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.Toolbox;
import com.github.abel533.echarts.axis.Axis;
import com.github.abel533.echarts.axis.AxisLabel;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.feature.Feature;
import com.github.abel533.echarts.json.GsonUtil;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Series;
import com.kingbase.bookSearch.core.excel.ExcelUtil;
import com.kingbase.bookSearch.system.bean.User;
import com.kingbase.bookSearch.system.dao.IUserDao;
import com.kingbase.bookSearch.system.dao.impl.UserDaoImpl;
import com.kingbase.bookSearch.system.service.IUserService;

/**
 * 角色实现类
 * @author ganliang
 */
@Service
public class UserServiceImpl implements IUserService{

	@Resource
	private IUserDao userDao=new UserDaoImpl();
	
	@Override
	public List<User> queryUser(User user) {
		String userName = user.getName();
		int id = user.getId();
		List<User> userList=null;
		//按照用户名 查询
		if(id==0&&userName!=null&&!"".equals(userName)){
			userList=userDao.find("FROM User WHERE name like '%"+userName+"%'");
		}
		//查询所有的用户
		else{
			userList=userDao.findAll();
		}
		if(userList!=null&&userList.size()>0){
			for (User usr : userList) {
				usr.setRoles(null);
			}
		}
		return userList;
	}

	@Override
	public void saveOrUpdate(User user) {
		//保存
		if(user.getId()==0){
			userDao.save(user);
		}
		//更新
		else{
			userDao.updateUser(user);
		}
	}

	@Override
	public void deleteUser(User user) {
		userDao.delete(user);
	}

	@Override
	public void updateUserPassword(User user) {
		userDao.updateUserPassword(user.getId(),user.getPassword());
	}

	@Override
	public Workbook exportUser(List<User> users) throws IOException {
		if(users==null||users.size()==0){
			return null;
		}
		//组装excel头
		List<String> headers=new ArrayList<String>();
		headers.add("id");headers.add("name");headers.add("password");
		headers.add("nickname");headers.add("sex");headers.add("email");
		headers.add("mobile");headers.add("contactTel");headers.add("address");
		headers.add("remark");headers.add("headerImage");headers.add("online");
		headers.add("birthday");headers.add("registryDate");headers.add("lastAccessDate");
		headers.add("active");
		//组装excel体
		List<List<Object>> data=new ArrayList<List<Object>>();
		for (User user : users) {
			List<Object> body=new ArrayList<Object>();
			body.add(user.getId());body.add(user.getName());body.add(user.getPassword());
			body.add(user.getNickname());body.add(user.getSex());body.add(user.getEmail());
			body.add(user.getMobile());body.add(user.getContactTel());body.add(user.getAddress());
			body.add(user.getRemark());body.add(user.getHeaderImage());body.add(user.isOnline());
			body.add(user.getBirthday());body.add(user.getRegistryDate());body.add(user.getLastAccessDate());
			body.add(user.isActive());
			data.add(body);
		}
		//生成excel表单
		return ExcelUtil.createWorkbook("菜单", headers, data, ExcelUtil.EXCEL_XLS);
	}

	@Override
	public List<User> getOnlineUser(User user) {
		List<User> userList = userDao.find("FROM User where online=true");
		for (User usr : userList) {
			usr.setRoles(null);
		}
		return userList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByName(String userName) {
		List<User> userList = (List<User>) userDao.find("FROM User WHERE name=?",new Object[]{userName});
		for (User user : userList) {
			user.setRoles(null);
		}
		return  userList!=null&&userList.size()>0?userList.get(0):null;
	}

	@Override
	public User changeDetailMessage(User user) {
		//先获取user
		User usr = userDao.get(user.getId());
		
		//更改密码 
		usr.setName(user.getName());
		usr.setNickname(user.getNickname());
		usr.setSex(user.getSex());
		usr.setMobile(user.getMobile());
		usr.setContactTel(user.getContactTel());
		usr.setBirthday(user.getBirthday());
		usr.setHeaderImage(user.getHeaderImage());
		usr.setEmail(user.getEmail());
		usr.setRemark(user.getRemark());
		
		userDao.update(usr);	
		usr.setRoles(null);
		
		return usr;
	}

	@Override
	public User findUserByEmail(String email) {
		User user = userDao.findUniqueEntity("FROM User where email=?", email);
		if(user!=null){
			user.setRoles(null);
		}
		return user;
	}

	@Override
	public void avtiveUser(User user) {
		userDao.updateUserActive(user.getEmail(), user.isActive());
	}

	@Override
	public String getUserRegistoryStatistics() {
		Option registryOption=new Option();
		
		registryOption.title().setText("近一周用户注册量");
		
		registryOption.tooltip().setTrigger(Trigger.axis);
		
		List<String> legendData=new ArrayList<String>();
		legendData.add("一周注册量");
		registryOption.legend().setData(legendData);
		
		//工具栏 
		Toolbox toolbox = registryOption.toolbox();
		//显示工具栏
		toolbox.setShow(true);
		
		//添加工具栏特性
		Map<String, Feature> featureMap=new HashMap<String,Feature>();
		featureMap.put("mark", Feature.mark.show(true));
		featureMap.put("dataView", Feature.dataView.show(true).readOnly(null));
		featureMap.put("magicType", Feature.magicType.show(true).type(new String[]{"line", "bar"}));
		featureMap.put("restore", Feature.restore.show(true));
		featureMap.put("saveAsImage", Feature.saveAsImage.show(true));
		toolbox.setFeature(featureMap);
		
		registryOption.setCalculable(true);
		
		//设置x轴
		List<Axis> xAxis=new ArrayList<Axis>();
		Axis<CategoryAxis> axis=new CategoryAxis();
		axis.setBoundaryGap(false);
		
		//添加x轴 数据
		List<String> axisData=new ArrayList<String>();
		axisData.add("周一");
		axisData.add("周二");
		axisData.add("周三");
		axisData.add("周四");
		axisData.add("周五");
		axisData.add("周六");
		axisData.add("周七");
		axis.setData(axisData);
		
		xAxis.add(axis);
		registryOption.setxAxis(xAxis);
		
		//设置y轴
		List<Axis> yAxis=new ArrayList<Axis>();
		Axis yaxis=new ValueAxis();
		yaxis.setAxisLabel(new AxisLabel().formatter("{value}"));
		yAxis.add(yaxis);
		registryOption.setyAxis(yAxis);
		
		//设置数据
		List<Series> series=new ArrayList<Series>();
		Line line=new Line();
		line.setName("一周注册量");
		List<String> seriesData=new ArrayList<String>();
		seriesData.add("1");
		seriesData.add("2");
		seriesData.add("4");
		seriesData.add("5");
		seriesData.add("7");
		seriesData.add("8");
		seriesData.add("20");
		line.setData(seriesData);
		series.add(line);
		
		registryOption.setSeries(series);
		
		String registryOptionJson= GsonUtil.format(registryOption);
		registryOptionJson=registryOptionJson.replace("\"", "'");
		return registryOptionJson;
	}

	@Override
	public String getUserAccessStatistics() {
		Option registryOption=new Option();
		
		registryOption.title().setText("近一周用户访问量");
		
		registryOption.tooltip().setTrigger(Trigger.axis);
		
		List<String> legendData=new ArrayList<String>();
		legendData.add("一周访问量");
		registryOption.legend().setData(legendData);
		
		//工具栏 
		Toolbox toolbox = registryOption.toolbox();
		//显示工具栏
		toolbox.setShow(true);
		
		//添加工具栏特性
		Map<String, Feature> featureMap=new HashMap<String,Feature>();
		featureMap.put("mark", Feature.mark.show(true));
		featureMap.put("dataView", Feature.dataView.show(true).readOnly(null));
		featureMap.put("magicType", Feature.magicType.show(true).type(new String[]{"line", "bar"}));
		featureMap.put("restore", Feature.restore.show(true));
		featureMap.put("saveAsImage", Feature.saveAsImage.show(true));
		toolbox.setFeature(featureMap);
		
		registryOption.setCalculable(true);
		
		//设置x轴
		List<Axis> xAxis=new ArrayList<Axis>();
		Axis<CategoryAxis> axis=new CategoryAxis();
		axis.setBoundaryGap(false);
		
		//添加x轴 数据
		List<String> axisData=new ArrayList<String>();
		axisData.add("周一");
		axisData.add("周二");
		axisData.add("周三");
		axisData.add("周四");
		axisData.add("周五");
		axisData.add("周六");
		axisData.add("周七");
		axis.setData(axisData);
		
		xAxis.add(axis);
		registryOption.setxAxis(xAxis);
		
		//设置y轴
		List<Axis> yAxis=new ArrayList<Axis>();
		Axis yaxis=new ValueAxis();
		yaxis.setAxisLabel(new AxisLabel().formatter("{value}"));
		yAxis.add(yaxis);
		registryOption.setyAxis(yAxis);
		
		//设置数据
		List<Series> series=new ArrayList<Series>();
		Line line=new Line();
		line.setName("一周访问量");
		List<String> seriesData=new ArrayList<String>();
		seriesData.add("13");
		seriesData.add("25");
		seriesData.add("41");
		seriesData.add("57");
		seriesData.add("70");
		seriesData.add("23");
		seriesData.add("244");
		line.setData(seriesData);
		series.add(line);
		
		registryOption.setSeries(series);
		
		String registryOptionJson= GsonUtil.format(registryOption);
		registryOptionJson=registryOptionJson.replace("\"", "'");
		return registryOptionJson;
	}
	
}
