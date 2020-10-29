package leetcode.editor.cn;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioClientSocketTest{

    public static void main(String[] args) throws Exception{
        //客户端
        SocketChannel socketChannel = SocketChannel.open();
        //连接服务端
        socketChannel.connect(new InetSocketAddress(InetAddress.getLocalHost(),8888));
        //非阻塞
        socketChannel.configureBlocking(false);
        //选择器
        Selector selector= Selector.open();
        //在selector上注册channel关心的事件，并附着和channel关联的对象(这个附着的对象并不能在服务端取到，只是在客户端方便使用)
        socketChannel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE, ByteBuffer.allocate(128));
        while (selector.select() != 0 ){
            //SelectionKey为selector和channel关联的对象
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()){
                SelectionKey key = it.next();
                it.remove();
                if(key.isConnectable()){
                    SocketChannel channel = (SocketChannel) key.channel();
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();
                    }
                } else if (key.isReadable()) {
                    SocketChannel channel=(SocketChannel)key.channel();
                    ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                    byteBuffer.clear();
                    channel.read(byteBuffer);
                    System.out.println(new String(byteBuffer.array()));
                } else if (key.isWritable()) {
                    SocketChannel channel=(SocketChannel)key.channel();
                    ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                    byteBuffer.clear();
                    byteBuffer.put("hello server".getBytes());
                    byteBuffer.flip();
                    channel.write(byteBuffer);
                    Thread.sleep(1000);
                }
            }
        }
    }
}
