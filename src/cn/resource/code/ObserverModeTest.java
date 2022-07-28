package cn.resource.code;

import java.util.Date;
import java.util.Observable;

/**
 * @Class: ObserverModeTest
 * @Date: 2022/7/28 15:09
 * @author: cuijiufeng
 */
public class ObserverModeTest {

    public static void main(String[] args) throws Throwable {

        //Vector<Observer> obs里存放着所有对此对象感兴趣的观察者，当对象发生改变时通知所有观察者
        ObservableObj observable = new ObservableObj();
        //定义一个观察者，添加到Vector<Observer>里
        observable.addObserver((o, arg) -> {
            System.out.println(o.toString() + "变化了");
        });
        //被观察对象变化
        observable.setNum(Integer.MIN_VALUE);
    }

    static class ObservableObj extends Observable {
        private Integer num;
        public void setNum(Integer num) {
            //当被观察的对象发生变化时通知观察者
            this.num = num;
            setChanged();
            notifyObservers();
        }
    }
}
