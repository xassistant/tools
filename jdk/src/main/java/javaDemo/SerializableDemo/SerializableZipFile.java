package javaDemo.SerializableDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.zip.ZipFile;

/*
 * ObjectOutputStream中的writeSerialData()方法说明了JVM检查writeObject(ObjectOutputStream out) 这个私有方法的潜在执行机制。这就是说，我们可以通过构造这个方法，使得原本不能序列化的类的部分数据域可以序列化。下面我们就开始对ZipFile进行可序列化的改造吧！
 */
public class SerializableZipFile implements Serializable {

	private static final long serialVersionUID = 2727937870009876901L;
	public ZipFile zf;

	public SerializableZipFile(String filename) throws IOException {
		zf = new ZipFile(filename);
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeObject(zf.getName());
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		String filename = (String) in.readObject();
		zf = new ZipFile(filename);
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File("d:/version.txt")));
		outputStream.writeObject(new SerializableZipFile("e:/agent_jar.zip"));
		outputStream.close();

		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File("d:/version.txt")));
		SerializableZipFile se = (SerializableZipFile) inputStream.readObject();
		inputStream.close();
		System.out.println("成功");
	}
}
