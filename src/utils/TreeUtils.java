package utils;

import java.lang.reflect.Constructor;

/**
 * @Class: TreeUtils
 * @Date: 2022/3/17 11:17
 * @auth: cuijiufeng
 */
public class TreeUtils {

    /**
     * @param i 传0
     * @param arr 值数组
     * @param clazz 如果是内部类必须是static的
     * @return T
     */
    public static <T, V> T arrtyToTree(int i, V[] arr, Class<T> clazz) {
        if (i >= arr.length || arr[i] == null) {
            return null;
        }
        T left = arrtyToTree(2 * i + 1, arr, clazz);
        T right = arrtyToTree(2 * i + 2, arr, clazz);
        return instanceTreeNode(clazz, arr[i], left, right);
    }

    private static <T, V> T instanceTreeNode(Class<T> clazz, V val, T left, T right) {
        try {
            Class<?> valCls = val.getClass();
            if (Integer.class.isAssignableFrom(val.getClass())) {
                valCls = int.class;
            }
            Constructor<T> constructor = clazz.getDeclaredConstructor(valCls, clazz, clazz);
            constructor.setAccessible(true);
            return constructor.newInstance(val, left, right);
        } catch (Exception e) {
            throw new RuntimeException("instance tree node failed", e);
        }
    }
}
