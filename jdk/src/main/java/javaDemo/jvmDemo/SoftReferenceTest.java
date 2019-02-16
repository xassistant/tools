package javaDemo.jvmDemo;
import java.lang.ref.SoftReference;  
  
public class SoftReferenceTest {  
    private static class BiggerObject{//占用空间的一个大对象  
        public int[] values;  
        public String name;  
        public BiggerObject(String name){  
            this.name=name;  
            values=new int[1024];  
        }  
    }  
    public static void main(String[] args) {  
        int count=10000000;//对象数量为100000，保证使得堆内存溢出  
        SoftReference[] values=new SoftReference[count];  
        for(int i=0;i<count;i++){  
            values[i]=new SoftReference<BiggerObject>(new BiggerObject("Object-"+i));  
        }  
        System.out.println(((BiggerObject)(values[values.length-1].get())).name);  
        for(int i=0;i<10;i++){  
            System.out.println(((BiggerObject)(values[i].get())).name);  
        }   
    }   
}  