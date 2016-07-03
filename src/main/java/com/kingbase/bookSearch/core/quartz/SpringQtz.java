package com.kingbase.bookSearch.core.quartz;

import java.util.Date;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class SpringQtz extends QuartzJobBean {

	private static int counter = 0;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		System.out.println();
		long ms = System.currentTimeMillis();
		System.out.println("\t\t" + new Date(ms));
		System.out.println(ms);
		System.out.println("(" + counter++ + ")");
		String s = (String) context.getMergedJobDataMap().get("service");
		System.out.println(s);
		System.out.println();

	}
	
	public static void main(String[] args) {
		try {
			SchedulerFactory factory=new StdSchedulerFactory();
			//获取任务调度器
			Scheduler scheduler = factory.getScheduler();
			
			//获取任务
			JobDetailImpl jobDetail=new JobDetailImpl("jobDetail", Scheduler.DEFAULT_GROUP, SpringQtz.class);
			jobDetail.setJobClass(SpringQtz.class);
			
			//获取触发器
			SimpleTriggerImpl simpleTrigger=new SimpleTriggerImpl("simpleTrigger", new Date());
			simpleTrigger.setRepeatCount(10);
			simpleTrigger.setRepeatInterval(1000);
			JobDataMap jobDataMap = simpleTrigger.getJobDataMap();
			jobDataMap.put("service", "123");
			
			//调度
			scheduler.scheduleJob(jobDetail, simpleTrigger);
			scheduler.start();
			
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
