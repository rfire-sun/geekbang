package com.sun.geekbang.TrainingCamp;


import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此
 * 文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。
 * <p>
 * 实践证明，没有package，通过o.getClass().getMethod("hello")获取方法进行执行
 * <p>
 * ref：
 * https://www.cnblogs.com/nice0e3/p/13719903.html
 */
public class HomeWorkHelloClassLoader extends ClassLoader {

    public static void main(String[] args)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Object o = new HomeWorkHelloClassLoader().findClass("Hello").newInstance();

        //还要执行里面的Hello方法  怎么调用那个的方法
        //反射获取method方法
        Method method = o.getClass().getMethod("hello");
        //反射去调用执行method方法
        String str = (String) method.invoke(o);
        System.out.println(str);

    }

    @Override
    protected Class<?> findClass(String name) {
        // 不知道名字是啥，感觉就是一个Hello,没准时jvm.Hello呢

        // 拿文件出来，转为byte数组
        byte[] sourceBytes = getFileByte();
        assert sourceBytes != null;

        // 将byte数组中的东西用255相减
        byte[] classBytes = new byte[sourceBytes.length];
        for (int i = 0; i < sourceBytes.length; i++) {
            classBytes[i] = (byte) (255 - sourceBytes[i]);
        }


        return defineClass(name, classBytes, 0, classBytes.length);
    }

    private byte[] getFileByte() {

        String path = "src/main/java/com/sun/geekbang/TrainingCamp/Hello.xlass";

        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            is = new FileInputStream(path);
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;


    }

}
