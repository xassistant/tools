package javaDemo.jmxDemo;

import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

/*
 * http://www.cnblogs.com/trust-freedom/p/6842332.html
 */
public class HelloAgent {

	public static void main(String[] args) throws Exception {
		// create mbean server
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();

		// create object name
		ObjectName objectName = new ObjectName("jmxBean:name=hello");

		// create mbean and register mbean
		server.registerMBean(new Hello(), objectName);

		/**
		 * JMXConnectorServer service
		 */
		// 这句话非常重要，不能缺少！注册一个端口，绑定url后，客户端就可以使用rmi通过url方式来连接JMXConnectorServer
		Registry registry = LocateRegistry.createRegistry(1099);

		// 构造JMXServiceURL
		JMXServiceURL jmxServiceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi");
		// 创建JMXConnectorServer
		JMXConnectorServer cs = JMXConnectorServerFactory.newJMXConnectorServer(jmxServiceURL, null, server);
		// 启动
		cs.start();
	}
}

class Hello implements HelloMBean {
	private String name;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String printHello() {
		return "Hello " + name;
	}

	@Override
	public String printHello(String whoName) {
		return "Hello  " + whoName;
	}
}