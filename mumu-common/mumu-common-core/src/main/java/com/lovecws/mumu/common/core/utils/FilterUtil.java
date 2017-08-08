package com.lovecws.mumu.common.core.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterUtil {

    public static Map<String, Object> filter(Object object, String... fields) {
        if (object == null || fields == null) {
            return new HashMap<String, Object>();
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            List<Field> fieldsList = new ArrayList<Field>();
            //递归获取所有字段
            getAllDeclaredFields(object.getClass(), fieldsList);
            for (String field : fields) {
                for (Field declaredField : fieldsList) {
                    declaredField.setAccessible(true);
                    //id:bankCardId id为字段名称，bankCardId为key
                    String[] splitFileds = field.split(":");
                    if(splitFileds.length>=2){
                        if (declaredField.getName().equals(splitFileds[0])) {
                            resultMap.put(splitFileds[1], declaredField.get(object));
                            break;
                        }
                    }else{
                        if (declaredField.getName().equals(field)) {
                            resultMap.put(field, declaredField.get(object));
                            break;
                        }
                    }
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @SuppressWarnings("rawtypes")
    public static void getAllDeclaredFields(Class clazz, List<Field> fieldsList) {
        if (clazz == null) {
            return;
        }
        Field[] declaredFields = clazz.getDeclaredFields();
        fieldsList.addAll(Arrays.asList(declaredFields));

        Class superclass = clazz.getSuperclass();
        if (superclass != null) {
            getAllDeclaredFields(superclass, fieldsList);
        }
    }

    @SuppressWarnings("rawtypes")
    public static List<Map<String, Object>> filters(List list, String... fields) {
        if (list == null || list.size() == 0 || fields == null) {
            return new ArrayList<Map<String, Object>>();
        }
        List<Map<String, Object>> mapData = new ArrayList<Map<String, Object>>();
        for (Object object : list) {
            mapData.add(filter(object, fields));
        }
        return mapData;
    }
}
