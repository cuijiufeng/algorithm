package cn.resource.code;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioServerSocketTest{

    public static void main(String[] args) throws Exception{
        //服务端
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置非阻塞
        serverSocketChannel.configureBlocking(false);
        //绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(8888));
        //选择器
        Selector selector = Selector.open();;
        //在selector上注册channel关心的事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while(selector.select() != 0){
            //SelectionKey为selector和channel关联的对象
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while(iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                if(key.isAcceptable()){
                    ServerSocketChannel server = (ServerSocketChannel)key.channel();
                    //和客户端连接
                    SocketChannel channel  = server.accept();
                    //非阻塞
                    channel.configureBlocking(false);
                    //在Selector里注册channel事件，并附着和channel绑定的一个对象ByteBuffer(这个附着的对象并不能在客户端取到，只是在服务端方便使用)
                    channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, ByteBuffer.allocate(128));
                } else if(key.isReadable()){
                    //获得和客户端连接的channel
                    SocketChannel channel=(SocketChannel)key.channel();
                    //获取附着在channel上的对象
                    ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                    byteBuffer.clear();
                    channel.read(byteBuffer);
                    System.out.println(new String(byteBuffer.array()));
                } else if(key.isWritable()){
                    SocketChannel channel=(SocketChannel)key.channel();
                    ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                    byteBuffer.clear();
                    byteBuffer.put("hello client".getBytes());
                    byteBuffer.flip();
                    channel.write(byteBuffer);
                    Thread.sleep(1000);
                }
            }
        }
    }
}