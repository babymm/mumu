package com.lovecws.mumu.common.core.utils;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeUtil {

    private volatile int initParam=3;

    private static Unsafe unsafe;

    static {
        try {
            //通过反射获取rt.jar下的Unsafe类
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            System.out.println("Get Unsafe instance occur error"+ e);
        }
    }

    /**
     * 将对象${obj}的字段${fieldName} 的值改为value
     * @param obj
     * @param fieldName
     * @param expect
     * @param update
     */
    public static boolean compareAndSwapInt(Object obj,String fieldName,int expect,int update){
        if(obj==null||fieldName==null){
            return false;
        }
        try {
            //计算对象偏移量
            long objectFieldOffset = unsafe.objectFieldOffset(obj.getClass().getDeclaredField(fieldName));
            return unsafe.compareAndSwapInt(obj, objectFieldOffset, expect, update);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws NoSuchFieldException {
        UnsafeUtil unsafe2=new UnsafeUtil();
        unsafe2.initParam=1;
        //boolean initParam = compareAndSwapInt(unsafe2, "initParam", 100);
        //System.out.println(initParam);

        for (int i=0;i<100;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(;;){
                        if(compareAndSwapInt(unsafe2, "initParam", unsafe2.initParam,unsafe2.initParam+1)){
                            System.out.println(Thread.currentThread().getName()+" : "+unsafe2.initParam);
                            break;
                        }
                    }

                }
            }).start();
        }
    }
}
