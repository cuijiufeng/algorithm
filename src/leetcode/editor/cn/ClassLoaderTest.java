package leetcode.editor.cn;

/**
 * @ClassName: ClassLoaderTest
 * @Date: 2020/10/12 15:39
 * @auth: Administrator
 * //启动类加载器(显示为null，非java编写)--->扩展类加载器--->应用类加载器(自定义类加载器继承于应用类加载器)
 * protected final Class<?> defineClass(String name, byte[] b, int off, int len)方法功能为把二进制数组的字节码加载进内存
 * //双亲委派机制
 * protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
 *     synchronized (getClassLoadingLock(name)) {
 *         // First, check if the class has already been loaded
 *         //类是否已经被加载过了
 *         Class<?> c = findLoadedClass(name);
 *         //如果没有被加载过
 *         if (c == null) {
 *             long t0 = System.nanoTime();
 *             try {
 *                  //父类加载器不为空，交由父类加载器加载
 *                 if (parent != null) {
 *                     c = parent.loadClass(name, false);
 *                 } else {
 *                      //如果父类加载器为空，则交由启动类加载器加载
 *                     c = findBootstrapClassOrNull(name);
 *                 }
 *             } catch (ClassNotFoundException e) {
 *                 // ClassNotFoundException thrown if class not found
 *                 // from the non-null parent class loader
 *             }
 *              //如果父类、祖先类加载器没有加载过
 *             if (c == null) {
 *                 // If still not found, then invoke findClass in order
 *                 // to find the class.
 *                 long t1 = System.nanoTime();
 *                 //自定义类加载器应该重写findClass方法，实现类加载的逻辑
 *                 c = findClass(name);
 *
 *                 // this is the defining class loader; record the stats
 *                 sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
 *                 sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
 *                 sun.misc.PerfCounter.getFindClasses().increment();
 *             }
 *         }
 *         //解析
 *         if (resolve) {
 *             resolveClass(c);
 *         }
 *         return c;
 *     }
 * }
 */
public class ClassLoaderTest {
    public static void main(String[] args){

    }
}
