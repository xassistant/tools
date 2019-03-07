package com.ant;

/**
 * 新增笔试环节，笔试题目如下：
 * JAVA语言实现三代以内直系亲属的关系计算器。具备几个简单的功能:(自带单元测试用例)
 * 1. 对于给定a,b两人，得出其亲属关系？
 * 2. 判断给定的a,b两人是否是指定的亲属关系？
 * 3. 给定a,找出给定亲属关系的都有哪些人？
 * <p>
 * 其他：可以使用第三方库， 程序最好具备可扩展性，
 * 比如无需修改结构定义可扩展到三代以外的关系判断。
 * 3.8日12点前邮件给到我。
 * <p>
 * f:父,m:母,h:夫,w:妻,s:子,d:女,z:兄,x:弟,c:姐,v:妹
 * 依据小米人物关系：分隔符为‘的’，例子：父的弟
 */
public class KinshipCalculator {

    public static void main(String[] args) {
        String kinship = "父的弟";
        String persona = KinshipCache.getPersona(kinship);
        System.out.println(persona);// fx

        String kinships = KinshipCache.getKinships(persona);
        System.out.println(kinships);

    }


}
