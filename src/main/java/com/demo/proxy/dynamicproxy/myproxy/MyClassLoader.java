package com.demo.proxy.dynamicproxy.myproxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class MyClassLoader extends ClassLoader {

    private String baseDir;

    public MyClassLoader() {
        this.baseDir = MyClassLoader.class.getResource("").getPath();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        // 获取类名
        String className = MyClassLoader.class.getPackage().getName() + "." + name;
        if (null == baseDir) {
            throw new ClassNotFoundException();
        }

        System.out.println("name" + name);
        // 获取类文件
        File file = new File(baseDir, name + ".class");
        if (!file.exists()) {
            throw new ClassNotFoundException();
        }

        try (FileInputStream in = new FileInputStream(file);
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }

            return defineClass(className, out.toByteArray(), 0, out.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(name);

    }
}
