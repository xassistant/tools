package javaDemo.jvmDemo;

import java.io.IOException;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.management.MBeanServerConnection;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.remote.JMXConnectionNotification;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.sun.management.HotSpotDiagnosticMXBean;
import com.sun.management.OperatingSystemMXBean;

public class JVMINFO {
	/**
	 * Get system info map.put("os", (long) (os.getSystemCpuLoad() * 100));
	 * map.put("vm", (long) (os.getProcessCpuLoad() * 100)); map.put("cores",
	 * (long) (os.getAvailableProcessors())); map.put("freememory",
	 * os.getFreePhysicalMemorySize());
	 */
	public static void main(String[] a) throws Exception {

		OperatingSystemMXBean os = ManagementFactory.newPlatformMXBeanProxy(
				ManagementFactory.getPlatformMBeanServer(),
				"java.lang:type=OperatingSystem", OperatingSystemMXBean.class);
		System.out.println((long)os.getSystemCpuLoad() * 100);
		System.out.println((long)os.getProcessCpuLoad() * 100);
		System.out.println(os.getAvailableProcessors());
		System.out.println(os.getFreePhysicalMemorySize());

	}

}
