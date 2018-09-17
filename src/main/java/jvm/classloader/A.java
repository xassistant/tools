package jvm.classloader;
import java.util.logging.Level;

public class A {
	public static final Level CUSTOMLEVEL = new Level("test", 550) {
	}; // 内部类

}