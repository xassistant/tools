package com.ant;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.ant.KinshipConstants.KINSHIP_SEPARATOR;

/**
 * 语法说明：
 * 【关系】f:父,m:母,h:夫,w:妻,s:子,d:女,z:兄,x:弟,c:姐,v:妹
 */
public class KinshipCache {
    private static final Map<String, String> kinships = new ConcurrentHashMap<>();
    private static final Map<String, String> personas = new HashMap<>();

    static {
        // 任务角色
        personas.put("父", "f");
        personas.put("母", "m");
        personas.put("夫", "h");
        personas.put("妻", "w");
        personas.put("子", "s");
        personas.put("女", "d");
        personas.put("兄", "z");
        personas.put("弟", "x");
        personas.put("姐", "c");
        personas.put("妹", "v");

        // 内置三代关系
        kinships.put("ff", "爷爷");
        kinships.put("fm", "奶奶");
        kinships.put("fs", "自己/哥哥/弟弟");
        kinships.put("fd", "自己/姐姐/妹妹");
        kinships.put("fc", "姑母");
        kinships.put("fv", "姑姐");
        kinships.put("fz", "伯父");
        kinships.put("fx", "叔叔");
        kinships.put("fw", "妈妈");
    }

    public static void putKinship(String key, String value) {
        if (key == null || value == null) {
            throw new KinshipException("亲属关系属性和值不能为空");
        }
        kinships.put(key, value);
    }

    public static String getKinships(String kinshipKey) {
        validKinshipKey(kinshipKey);

        return kinships.get(kinshipKey);
    }

    private static void validKinshipKey(String kinshipKey) {
        if (kinshipKey == null || kinshipKey.isEmpty()) {
            throw new KinshipException("亲属关系角色代号错误");
        }
    }

    public static String getPersona(String kinshipName) {
        // 校验数据有效性
        validKinshipName(kinshipName);

        String[] personaArr = kinshipName.split(KINSHIP_SEPARATOR);
        if (personaArr == null || personaArr.length == 0) {
            throw new KinshipException("请正确输入任务角色");
        }
        StringBuilder builder = new StringBuilder();
        for (String persona : personaArr) {
            String s = personas.get(persona);
            builder.append(s);
        }
        return builder.toString();
    }

    private static void validKinshipName(String persona) {
        if (persona == null || persona.isEmpty()) {
            throw new KinshipException("请正确输入任务角色名称");
        }
    }
}