package cn.resource.code;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName: NIOTest
 * @Date: 2020/10/20 15:08
 * @auth: Administrator
 * 流stream只能是单向操作，阻塞
 * 管理channel可以双向操作，非阻塞
 * Buffer-->ByteBuffer-->HeapByteBuffer (DirectByteBuffer为直接内存缓冲区)
 *      private int mark = -1;          //标记一个位置
 *      private int position = 0;       //标记放入数据时要放入的位置
 *      private int limit;              //标记最大可读可写位置
 *      private int capacity;           //初始大小位置
 *      public final Buffer clear()     //转为写模式
 *      public final Buffer flip()      //转为读模式
 *      public final Buffer mark()      //标记当前position位置
 *      public final Buffer reset()     //恢复标记的mark值
 */
public class NioFileTest {
    
    public static void main(String[] args) throws Exception {
        File file = new File("E:\\idea_workspace\\algorithm\\src\\leetcode\\editor\\cn\\test1.txt");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        FileChannel channel = randomAccessFile.getChannel();

        //往通道写
        ByteBuffer byteBuffer = ByteBuffer.wrap("hello world!".getBytes());
        channel.write(byteBuffer);

        //设置通道的位置
        channel.position(0);

        //从通道读
        //byteBuffer转为写模式
        byteBuffer.clear();
        channel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array()));

        channel.close();
        randomAccessFile.close();
    }
}
