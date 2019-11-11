import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Auther:zwer
 * @Date:2019/11/8 14:32
 * @Description:PACKAGE_NAME
 * @Version:1.0
 **/
public class FtpTest {
    public static void main(String[] args) throws Exception {
        ftpClientDemo();
    }
    public static void ftpClientDemo() throws Exception{
        System.out.println("ftp 服务器...");
        long start = System.currentTimeMillis();
        //创建 FTPClient
        FTPClient ftp = new FTPClient();
        ftp.setBufferSize(1024*1024*5);
        //连接 ftp 服务器的 IP和 port
        ftp.connect("www.xylyy.site", 21);
//        ftp.connect("192.168.170.129", 21);
        //指定登录 ftp 服务器的用户名和密码
        boolean flag = ftp.login("ftpuser", "ftpuser");
        System.out.println("登录 flag:"+flag);
        //创建输入流
        InputStream is = new FileInputStream("d://1.png");
        BufferedInputStream bis = new BufferedInputStream(is);
        //指定上传目录以绝对路径的形式
        ftp.changeWorkingDirectory("/home/ftpuser");
        //指定上传以字符流上传，默认会采用字符流上传，图片会有损失
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
        //第一个参数表示上传后图片的名称，第二个参数表示要上传图片的资源
        boolean flag2= ftp.storeFile("java-4.png", bis);
        System.out.println("上传 flag:"+flag2);

        //关闭资源
        is.close();
        //关闭 ftp 连接
        ftp.logout();
        System.out.println("ftp 服务器结束....");
        System.out.println("time:"+(System.currentTimeMillis() - start));
    }
}
