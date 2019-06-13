package com.zhengbing.learn.miaosha.controller;

import com.zhengbing.learn.miaosha.common.CodeMsg;
import com.zhengbing.learn.miaosha.common.Result;
import com.zhengbing.learn.miaosha.common.exception.GlobalException;
import com.zhengbing.learn.miaosha.common.redis.RedisService;
import com.zhengbing.learn.miaosha.entity.MiaoshaUser;
import com.zhengbing.learn.miaosha.entity.Orderinfo;
import com.zhengbing.learn.miaosha.entity.vo.GoodsVO;
import com.zhengbing.learn.miaosha.entity.vo.OrderinfoVO;
import com.zhengbing.learn.miaosha.service.GoodsService;
import com.zhengbing.learn.miaosha.service.MiaoshaUserService;
import com.zhengbing.learn.miaosha.service.OrderinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhengbing on 2019-06-12.
 * Since Version 1.0
 */
@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    RedisService redisService;

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @Autowired
    OrderinfoService orderinfoService;

    @Autowired
    GoodsService goodsService;

    /**
     * 订单详情
     *
     * @param model
     * @param user
     * @param orderId
     * @return
     */
    @RequestMapping(value = "detail")
    @ResponseBody
    public Result<OrderinfoVO> detail( Model model, MiaoshaUser user,@RequestParam("orderId") long orderId ){
        if ( null  ==  user ){
            throw new GlobalException( CodeMsg.SESSION_ERROR );
        }
        Orderinfo orderinfo = orderinfoService.getOrderById( orderId );
        if ( null == orderinfo ){
            throw new GlobalException( CodeMsg.ORDER_NOT_EXIST );
        }
        long goodsId = orderinfo.getGoodsId();
        GoodsVO goods = goodsService.getGoodVOByGoodsId( goodsId );

        OrderinfoVO orderinfoVO = new OrderinfoVO();
        orderinfoVO.setGoods( goods );
        orderinfoVO.setOrder( orderinfo );

        return Result.success( orderinfoVO );
    }


}
