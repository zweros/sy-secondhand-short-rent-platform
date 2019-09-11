package conf.szxy.conf;

import com.szxy.WebPortalApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;

/**
 * @Auther:zwer
 * @Date:2019/8/25 21:07
 * @Description:conf.szxy.conf
 * @Version:1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes={WebPortalApplication.class})
public class ConlogNameTest {

    @Value("${DEFAULT_CATELOG_NAME}")
    private String DEFAULT_CATELOG_NAME;
    @Value("${REDIS_HOMEGOODS_SHOW_KEY}")
    private  String REDIS_HOMEGOODS_SHOW_KEY ;

    @Test
    public void setUser() {
        System.out.println(this.DEFAULT_CATELOG_NAME);
        System.out.println(this.REDIS_HOMEGOODS_SHOW_KEY);
        try {
            System.out.println(new String(this.DEFAULT_CATELOG_NAME.getBytes("ISO-8859-1"),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }




}
