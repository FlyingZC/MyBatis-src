package org.apache.ibatis.zc.test;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.junit.Test;

public class T04ObjectFactory
{
    @Test
    public void t01()
    {
        ObjectFactory factory = new DefaultObjectFactory();
        factory.create(ClassA.class);
    }
}
