package  com.szxy.test.mapper;

import com.szxy.GoodsApp;
import com.szxy.mapper.GoodsMapper;
import com.szxy.pojo.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={GoodsApp.class})
public class GoodMapperTest {

    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    public void selByCatelogGoodsIdServiceTest(){
        List<Goods> goods = this.goodsMapper.selByCatelogGoodsId(null, 6);
        for (Goods good:
             goods) {
            System.out.println(good);
        }
        System.out.println("=====================");
        Goods goods1 = this.goodsMapper.selByGoodId(1);
        System.out.println(goods1);
    }


}
