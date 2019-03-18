package com.tangdi.common.common;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 对象属性复制创建
 *
 * @author ron
 * @date 2018/11/20 18:25
 */
public class BeanConvertor {
    public static <T> T convertor(Object obj, Class<T> cls) {
        if(obj==null){
            return null;
        }
        try {
            T t = cls.newInstance();
            BeanUtils.copyProperties(obj, t);
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> convertorToList(Object objList, Class<T> cls) {
        if(objList==null){
            return null;
        }
        List<T> list = new ArrayList<>();
        List listTemp=(List)objList;
        for(int i=0;i<listTemp.size();i++){
            T t = convertor(listTemp.get(i), cls);
            list.add(t);
        }
        return list;
    }

    public static Map<String, Object> objectToMap(Object obj){
        if(obj == null){
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return map;
    }
}
