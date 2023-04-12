package cn.resource.code;

import java.util.ServiceLoader;

/**
 * @author cuijiufeng
 * @Class SpiTest
 * @Date 2023/4/12 11:26
 */
public class JavaSpiTest {
    public static void main(String[] args) throws Throwable {
        for (ISpiIt it : ServiceLoader.load(ISpiIt.class)) {
            it.say();
        }
    }

    public static class SpiImpl1 implements ISpiIt {
        @Override
        public void say() {
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
        }
    }

    public static class SpiImpl2 implements ISpiIt {
        @Override
        public void say() {
            System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbb");
        }
    }
}

interface ISpiIt {
    void say();
}
