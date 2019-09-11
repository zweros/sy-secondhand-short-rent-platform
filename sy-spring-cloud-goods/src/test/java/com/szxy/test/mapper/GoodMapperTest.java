package  com.szxy.test.mapper;

import com.szxy.GoodsApplication;
import com.szxy.mapper.GoodsCommentsMapper;
import com.szxy.mapper.GoodsMapper;
import com.szxy.pojo.Comments;
import com.szxy.pojo.Goods;
import com.szxy.service.ProviderGoodsService;
import com.szxy.utils.GoodsGrid;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={GoodsApplication.class})
public class GoodMapperTest {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsCommentsMapper goodsCommentsMapper;
    @Autowired
    private ProviderGoodsService providerGoodsService;

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
    
    @Test
    public void selByCommentsGoodsIdServiceTest(){
        List<Comments> comments = this.goodsCommentsMapper.selCommentsByUserId(6);
        for (Comments comment : comments) {
            System.out.println(comment);
        }
    }

    @Test
    public void findGoodsByNameMapperTest(){
        List<Goods> goods = this.goodsMapper.findGoodsByNameMapper("小");
        System.out.println(goods);
    }

    @Test
    public void selUserPublishedAllGoodsMapperTest(){
        List<Goods> goodsExtendList = this.goodsMapper.selUserPublishedAllGoodsMapper(31);
        System.out.println(goodsExtendList);
    }

    @Test
    public void addGood(){
        Goods good  = new Goods();
        good.setId(999);
        good.setUserId(1);
        good.setCatelogId(1);
        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        good.setStartTime(dateStr);
        good.setEndTime(dateStr);
        good.setPolishTime(dateStr);
        good.setName("篮球");
        good.setPrice((float) 110.0);
        good.setRealPrice((float) 11.0);
        good.setDescrible("123");
        good.setStatus(1);
        this.goodsMapper.insertGoodsMapper(good);
    }


    @Test
    public void selGoodsPaginationTest(){
        List<Goods> goodsList = this.goodsMapper.selGoodsByPaginationMapper(0, 4);
        for (Goods goods : goodsList) {
            System.out.println(goods);
        }
        System.out.println("==============");
        GoodsGrid goodsGrid = this.providerGoodsService.findGoodsByPaginationService(2, 4);
        System.out.println(goodsGrid.getTotal()+" "+goodsGrid.getCurrent());
        List<Goods> goodsList2 = goodsGrid.getRows();
        for (Goods goods : goodsList2) {
            System.out.println(goods);
        }

    }

}
