package com.travel.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.travel.bean.User;



public class Test {
	public static void main(String[] args) throws Exception {
		   List<String> warnings = new ArrayList<String>();
		   boolean overwrite = true;
		   File configFile = new File("generatorConfig.xml");
		   ConfigurationParser cp = new ConfigurationParser(warnings);
		   Configuration config = cp.parseConfiguration(configFile);
		   DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		   MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		   myBatisGenerator.generate(null);
		
//		String s = "{\"canlogin\":0,\"email\":\"17826855724@163.com\",\"id\":49,\"isOnline\":0,\"latitude\":30.316278647076203,\"logintime\":1523965917000,\"longitude\":120.35623202768016,\"password\":\"1234\",\"phone\":\"13526855724\",\"registertime\":1522250610000,\"status\":\"0\",\"updatelocationtime\":\"1523952816\",\"username\":\"黄秀丽\",\"xingetoken\":\"513d7f53f50d0824454ba75fd44be555456ad16d\"}";
//		User user = JSON.parseObject(s, new TypeReference<User>(){});
//		User u = JSON.parseObject(s, User.class);
//		System.out.println(user.getUsername());
//		System.out.println(u.getUsername());
	}

}
