package com.zhengbing.learn.miaosha.controller;

import com.zhengbing.learn.miaosha.common.CodeMsg;
import com.zhengbing.learn.miaosha.common.Result;
import com.zhengbing.learn.miaosha.common.exception.GlobalException;
import com.zhengbing.learn.miaosha.common.rabbitmq.MQSender;
import com.zhengbing.learn.miaosha.common.rabbitmq.MiaoshaMessage;
import com.zhengbing.learn.miaosha.common.redis.GoodsKey;
import com.zhengbing.learn.miaosha.common.redis.RedisService;
import com.zhengbing.learn.miaosha.entity.MiaoshaOrder;
import com.zhengbing.learn.miaosha.entity.MiaoshaUser;
import com.zhengbing.learn.miaosha.entity.vo.GoodsVO;
import com.zhengbing.learn.miaosha.service.GoodsService;
import com.zhengbing.learn.miaosha.service.MiaoshaOrderService;
import com.zhengbing.learn.miaosha.service.MiaoshaService;
import com.zhengbing.learn.miaosha.service.OrderinfoService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zhengbing on 2019/5/31.
 * Since Version 1.0
 */
@Controller
@RequestMapping("/miaosha")
public class MiaoshaController implements InitializingBean {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderinfoService orderinfoService;

    @Autowired
    MiaoshaOrderService miaoshaOrderService;

    @Autowired
    MiaoshaService miaoshaService;

    @Autowired
    RedisService redisService;

    @Autowired
    MQSender sender;

    private HashMap<Long,Boolean> goodsOver = new HashMap<Long,Boolean>(  );

    @RequestMapping(value = "/do_miaosha", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> do_miaosha( Model model, MiaoshaUser user, @RequestParam("goodsId") long goodsId ){
        model.addAttribute( "user",user );
        if( null == user ){
            throw new GlobalException( CodeMsg.MIAO_SHA_ERROR );
        }

        // 内存标记，减少redis访问
        boolean goodsOvered = goodsOver.get( goodsId );
        if ( goodsOvered ){
            throw new GlobalException( CodeMsg.MIAO_SHA_OVER );
        }


        // 预减库存 redis
        long stock = redisService.decr(  GoodsKey.getGoodsMiaoshaStock,""+goodsId );
        if ( stock < 0 ){
            goodsOver.put( goodsId,true );
            throw new GlobalException( CodeMsg.MIAO_SHA_OVER );
        }

        // 判断是否已经秒杀过了
        MiaoshaOrder miaoshaOrder = miaoshaOrderService.getMiaoshaOrderByUserIdGoodsId(user.getId(),goodsId);
        if ( null != miaoshaOrder ){
            model.addAttribute( "codeMsg", CodeMsg.REPEAT_MIAO_SHA.getMsg() );
            throw new GlobalException( CodeMsg.REPEAT_MIAO_SHA );
        }

        // 加入队列
        MiaoshaMessage mm = new MiaoshaMessage();
        mm.setGoodsId( goodsId );
        mm.setUser( user );
        sender.sendMiaoshaMessage( mm );

        // 代表排队中的返回
        return Result.success( 0 );

//        // 判断秒杀的商品是否还有库存
//        GoodsVO goods = goodsService.getGoodVOByGoodsId( goodsId );
//        if ( goods.getStockCount() <= 0 ){
//            model.addAttribute( "codeMsg", CodeMsg.MIAO_SHA_OVER.getMsg() );
//            throw new GlobalException( CodeMsg.MIAO_SHA_OVER );
//        }
//
//        // 判断是否已经秒杀过了
//        MiaoshaOrder miaoshaOrder = miaoshaOrderService.getMiaoshaOrderByUserIdGoodsId(user.getId(),goodsId);
//        if ( null != miaoshaOrder ){
//            model.addAttribute( "codeMsg", CodeMsg.REPEAT_MIAO_SHA.getMsg() );
//            throw new GlobalException( CodeMsg.REPEAT_MIAO_SHA );
//        }
//        Orderinfo order = miaoshaService.miaosha(user,goods);
//
//        return Result.success( order );
    }

  @RequestMapping(value = "/result", method = RequestMethod.GET)
  @ResponseBody
  public Result<Long> miaoshaResult(
      Model model, MiaoshaUser user, @RequestParam("goodsId") long goodsId) {
    model.addAttribute("user", user);
        if (null == user) {
          throw new GlobalException(CodeMsg.MIAO_SHA_ERROR);
        }
       long result = miaoshaService.getMiaoshaResult(user.getId(),goodsId);
        return Result.success( result );
    }

    /**
     * 系统初始化的时候，加载商品库存数据到redis
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsVO> goodsList = goodsService.listGoodsVO();
        if ( null == goodsList ){
            return ;
        }
        for ( GoodsVO goods:goodsList ){
            redisService.set( GoodsKey.getGoodsMiaoshaStock,""+goods.getId(),goods.getStockCount() );
            goodsOver.put( goods.getId(),false );
        }
    }
}
