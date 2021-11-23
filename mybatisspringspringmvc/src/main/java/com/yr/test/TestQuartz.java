package com.yr.test;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yr.entity.Father;
import com.yr.service.FatherService;

@Component
public class TestQuartz {

	@Autowired
	private FatherService<Father> fatherService;

	@Scheduled(cron = "0 0 0-2 * * ?")
	public void query() {
		System.out.println(new Date() + " , " + fatherService.query());
	}
}
