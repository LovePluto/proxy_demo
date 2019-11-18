package com.demo.proxy.dynamicproxy.myproxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MyProxy {

    public static final String PROXY_0 = "$Proxy0";
    public static final String JAVA = ".java";
    private static String ln = "\r";

    public static Object newProxyInstance(MyClassLoader loader,
                                          Class<?> interfaces,
                                          MyInvocationHandler h) throws IllegalArgumentException {

        try {

            // 1.生成代理类的源代码
            String src = genJavaResource(MyClassLoader.class.getPackage().getName(), interfaces);

            // 2.将生成的源代码输出到磁盘，保存为.java文件
            String path = MyProxy.class.getResource("").getPath();
            File file = new File(path + interfaces.getSimpleName() + PROXY_0 + JAVA);
            FileWriter fw = new FileWriter(file);
            fw.write(src);
            fw.close();

            // 3.编译源代码，并生成.java文件
            JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = javaCompiler
                    .getStandardFileManager(null, null, null);
            Iterable iterable = manager.getJavaFileObjects(file);
            JavaCompiler.CompilationTask task = javaCompiler
                    .getTask(null, manager, null, null, null, iterable);
            task.call();
            manager.close();

            // 5.删除.java文件
            file.deleteOnExit();

            // 6.将class文件中的内容，动态加载到JVM中
            Class proxyClass = loader.findClass(interfaces.getSimpleName() + PROXY_0);

            // 7.返回被代理后的代理对象
            Constructor c = proxyClass.getConstructor(MyInvocationHandler.class);
            return c.newInstance(h);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String genJavaResource(String packagePath, Class<?> interfaces) {
        StringBuilder src = new StringBuilder();
        src.append("package ")
                .append(packagePath).append(";").append(ln)
                .append("import java.lang.reflect.Method;").append(ln)
                .append("public class ")
                .append(interfaces.getSimpleName())
                .append(PROXY_0)
                .append(" implements ").append(interfaces.getName()).append("{").append(ln)
                .append("private MyInvocationHandler h;").append(ln)
                .append("public ")
                .append(interfaces.getSimpleName())
                .append(PROXY_0)
                .append("(MyInvocationHandler h){").append(ln)
                .append("this.h=h;").append(ln)
                .append("}").append(ln);

        for (Method method : interfaces.getMethods()) {
            src.append("public ")
                    .append(method.getReturnType())
                    .append(" ")
                    .append(method.getName())
                    .append("() {").append(ln)
                    .append("try {").append(ln)
                    .append("Method m = ")
                    .append(interfaces.getName())
                    .append(".class.getMethod(\"")
                    .append(method.getName())
                    .append("\");").append(ln)
                    .append("this.h.invoke(this, m, new Object[]{});").append(ln)
                    .append("}catch (Throwable e){").append(ln)
                    .append("e.printStackTrace();").append(ln)
                    .append("}").append(ln)
                    .append("}").append(ln);
        }
        src.append("}");

        return src.toString();

    }
}
