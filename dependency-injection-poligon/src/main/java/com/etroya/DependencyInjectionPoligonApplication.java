package com.etroya;

import com.etroya.controllers.ConstructorInjectedController;
import com.etroya.controllers.MyController;
import com.etroya.controllers.PropertyInjectedController;
import com.etroya.controllers.SetterInjectedController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DependencyInjectionPoligonApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DependencyInjectionPoligonApplication.class, args);

		MyController controller = (MyController) ctx.getBean("myController");
		controller.hello();

//		System.out.println(ctx.getBean(PropertyInjectedController.class).sayHello());
//		System.out.println(ctx.getBean(SetterInjectedController.class).sayHello());
		System.out.println(ctx.getBean(ConstructorInjectedController.class).sayHello());
	}

}

