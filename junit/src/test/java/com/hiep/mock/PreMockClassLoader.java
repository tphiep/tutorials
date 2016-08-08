package com.hiep.mock;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

/**
 * Created by hiep on 8/7/2016.
 */
public class PreMockClassLoader extends ClassLoader {

    private static Set<String> classes = Collections.emptySet();
    private ClassPool pool;

    public static void setClasses(Set<String> classes) {
        PreMockClassLoader.classes = classes;
    }

    public PreMockClassLoader(ClassLoader parent) {
        super(parent);
        this.pool = ClassPool.getDefault();
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {

        // no mater what, do not allow certain classes to be loaded by this
        // class loader. change this as you see fit (and are able to).
        if (name.startsWith("java.")) {
            return super.loadClass(name);
        } else if (name.startsWith("javax.")) {
            return super.loadClass(name);
        } else if (name.startsWith("sun.")) {
            return super.loadClass(name);
        } else if (name.startsWith("org.junit.")) {
            return super.loadClass(name);
        } else if (name.startsWith("org.mockito.")) {
            return super.loadClass(name);
        } else if (name.startsWith("com.objectpartners.buesing.premock.")) {
            return super.loadClass(name);
        } else {
            if (classes.contains(name)) {
                // only load the classes specified with the class loader,
                // otherwise leave it up to the parent.
                return findClass(name);
            } else {
                return super.loadClass(name);
            }
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            CtClass ctClass = pool.get(name);

            if (Modifier.isFinal(ctClass.getModifiers())){
                ctClass.setModifiers(ctClass.getModifiers() & ~Modifier.FINAL);
            }

            // remove final modifiers from all methods
            CtMethod[] methods = ctClass.getDeclaredMethods();
            for (CtMethod method : methods) {
                if (Modifier.isFinal(method.getModifiers())) {
                    method.setModifiers(method.getModifiers() & ~Modifier.FINAL);
                }
            }

            byte[] b = ctClass.toBytecode();
            Class<?> result = defineClass(name, b, 0, b.length);
            return result;
        } catch (NotFoundException | IOException | CannotCompileException e) {
            throw new ClassNotFoundException();
        }
    }
}
