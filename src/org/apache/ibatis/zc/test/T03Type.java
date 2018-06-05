package org.apache.ibatis.zc.test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import org.apache.ibatis.reflection.TypeParameterResolver;

public class T03Type
{
    SubClassA<Long> sa = new SubClassA<Long>();

    public static void main(String[] args) throws Exception
    {
        Field f = ClassA.class.getDeclaredField("map");
        System.out.println(f.getGenericType());// java.util.Map<K, V>
        System.out.println(f.getGenericType() instanceof ParameterizedType);// true

        // 解析SubA<Long>(ParameterType类型)中的map字段,注意:ParameterizedTypeImpl是
        // 在sun.reflect.generics.reflectiveObjects包下的ParameterizedType接口实现
        // TypeParameterResolver.resolveFieldType(f, ParameterizedTypeImpl.make(SubClassA.class, new Type[]{Long.class}, T03Type.class));
        Type type = TypeParameterResolver.resolveFieldType(f, T03Type.class.getDeclaredField("sa").getGenericType());
        System.out.println(type.getClass());// class org.apache.ibatis.reflection.TypeParameterResolver$ParameterizedTypeImpl  (该类是ibatis对ParameterizedType接口的实现)

        ParameterizedType p = (ParameterizedType) type;
        System.out.println(p.getRawType());// interface java.util.Map
        System.out.println(p.getOwnerType());// null

        for (Type t : p.getActualTypeArguments())
        {
            System.out.println(t);// class java.lang.Long    class java.lang.Long
        }

    }
}
