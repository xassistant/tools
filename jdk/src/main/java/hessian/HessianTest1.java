package hessian;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xlj
 * @Date 2018/12/23 15:09
 */
public class HessianTest1 {
    public static void main(String[] args) throws IOException {

        //hessian
        Long startLong = System.currentTimeMillis();
        int size = 0;
        for (int i = 0; i < 100000; i++) {
            byte[] bytes = javaSerialize(new User("dby" + i));
            size += bytes.length;
        }
        System.out.println("-----java--" + (System.currentTimeMillis() - startLong) + "  size=" + size);
        size = 0;
        startLong = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            byte[] bytes = serialize2(new User("dby" + i));
            size += bytes.length;
        }

        System.out.println("-----hessian2--" + (System.currentTimeMillis() - startLong) + "  size=" + size);
        size = 0;
        startLong = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            byte[] bytes = serialize(new User("dby" + i));
            size += bytes.length;
        }
        System.out.println("-----hessian--" + (System.currentTimeMillis() - startLong) + "  size=" + size);

    }

    public static byte[] serialize(Object obj) throws IOException {
        if (obj == null) throw new NullPointerException();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        HessianOutput ho = new HessianOutput(os);
        ho.writeObject(obj);
        return os.toByteArray();
    }

    public static Object deserialize(byte[] by) throws IOException {
        if (by == null) throw new NullPointerException();

        ByteArrayInputStream is = new ByteArrayInputStream(by);
        HessianInput hi = new HessianInput(is);
        return hi.readObject();
    }

    public static byte[] serialize2(Object obj) throws IOException {
        if (obj == null) throw new NullPointerException();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Hessian2Output ho = new Hessian2Output(os);
        ho.startMessage();
        ho.writeObject(obj);
        ho.completeMessage();
        ho.close();
        return os.toByteArray();
    }

    public static Object deserialize2(byte[] by) throws IOException {
        if (by == null) throw new NullPointerException();

        ByteArrayInputStream is = new ByteArrayInputStream(by);
        Hessian2Input hi = new Hessian2Input(is);
        return hi.readObject();
    }

    public static byte[] javaSerialize(Object obj) throws IOException {
        if (obj == null) throw new NullPointerException();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(obj);
        return os.toByteArray();
    }

    public static Object javaDeserialize(byte[] by) throws IOException, ClassNotFoundException {
        if (by == null) throw new NullPointerException();

        ByteArrayInputStream is = new ByteArrayInputStream(by);
        ObjectInputStream in = new ObjectInputStream(is);
        return in.readObject();
    }

    static class User implements Serializable {
        private String string;

        public User(String string) {
            this.string = string;
        }
    }
}
