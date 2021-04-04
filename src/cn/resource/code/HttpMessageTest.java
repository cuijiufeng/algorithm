package cn.resource.code;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @ClassName: HttpMessageTest
 * @Date: 2020/10/10 11:02
 * @auth: Administrator
 * 写个带请求体body的GET请求
 * http协议:按照一定的规则传输数据，服务端会返回一定格式的数据.
 */
public class HttpMessageTest {
    public static final String ip = "www.baidu.com";
    public static final int port = 80;
    public static final String rn = "\r\n";

    public static void main(String[] args) throws Exception{
        Socket socket = new Socket(InetAddress.getByName(ip), port);
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        String httpMsg = "GET / HTTP/1.1" + rn +
                         "Host:naf.mnc000.mcc460.pub.3gppnetwork.org" + rn +
                         "Content-Type:application/octet-stream" + rn +
                         "Accept:*/*" + rn +
                         "X-Key-Material:B-TID=\"3hmxBoJs24m1cM6dUfGqXDAwMDIwMDAy@xxgbsf.ims.mnc001.mcc460.3gppnetwork.org" + rn +
                         "Content-Length:11" + rn + //此处值为body的长度,即"data=123123".getBytes().length
                         rn +
                         "data=123123";
        os.write(httpMsg.getBytes());
        Thread.sleep(500);      //一点延时，立即从输入流读，有时会读不到东西。
        int len = is.available();
        byte[] buf = new byte[len];
        is.read(buf, 0, len);
        System.out.println(new String(buf));
        os.close();
        is.close();
        socket.close();
    }
}
