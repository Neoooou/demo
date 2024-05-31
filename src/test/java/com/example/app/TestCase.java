package com.example.app;

import com.example.app.beaninject.MyImportSelector;
import com.example.app.config.PropertiesDemo;
import com.example.machine_learning.Prophet;
import com.example.app.model.Employee;
import com.example.app.beaninject.XFactoryBean;
import com.example.app.model.User;
import lombok.RequiredArgsConstructor;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = {DemoApplication.class})
@RunWith(SpringRunner.class)
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Import({Prophet.class, User.class, MyImportSelector.class})
class TestCase {

	final PropertiesDemo myProperties;

	final ApplicationContext applicationContext;

	@Test
	public void test1() {
		Assert.assertNotNull(applicationContext.getBean(User.class));
		Assert.assertNotNull(applicationContext.getBean(XFactoryBean.class));
		Assert.assertTrue(applicationContext.containsBean(Employee.class.getName()));
		Assert.assertTrue(applicationContext.containsBean(Prophet.class.getName()));
		Assert.assertTrue(applicationContext.containsBean("fooService1"));
		Assert.assertTrue(applicationContext.containsBean("fooService2"));
	}


	@Test
	public void testClassLoader(){


	}
}
