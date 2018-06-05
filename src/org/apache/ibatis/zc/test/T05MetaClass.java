package org.apache.ibatis.zc.test;

import org.apache.ibatis.reflection.MetaClass;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.junit.Test;

public class T05MetaClass
{
    @Test
    public void test01()
    {
        // public static MetaClass forClass(Class<?> type, ReflectorFactory reflectorFactory)
        MetaClass.forClass(Class, reflectorFactory)
    }
}
