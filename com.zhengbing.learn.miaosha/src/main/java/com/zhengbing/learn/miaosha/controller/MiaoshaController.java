package com.zhengbing.learn.miaosha.controller;

import com.zhengbing.learn.miaosha.common.CodeMsg;
import com.zhengbing.learn.miaosha.entity.MiaoshaOrder;
import com.zhengbing.learn.miaosha.entity.MiaoshaUser;
import com.zhengbing.learn.miaosha.entity.Orderinfo;
import com.zhengbing.learn.miaosha.entity.vo.GoodsVO;
import com.zhengbing.learn.miaosha.service.GoodsService;
import com.zhengbing.learn.miaosha.service.MiaoshaOrderService;
import com.zhengbing.learn.miaosha.service.MiaoshaService;
import com.zhengbing.learn.miaosha.service.OrderinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zhengbing on 2019/5/31.
 * Since Version 1.0
 */
@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderinfoService orderinfoService;

    @Autowired
    MiaoshaOrderService miaoshaOrderService;

    @Autowired
    MiaoshaService miaoshaService;

    @RequestMapping("/do_miaosha")
    public String do_miaosha( Model model, MiaoshaUser user, @RequestParam("goodsId") long goodsId ){
        model.addAttribute( "user",user );
        if( null == user ){
            return "login";
        }

        GoodsVO goods = goodsService.getGoodVOByGoodsId( goodsId );
        if ( goods.getStockCount() <= 0 ){
            model.addAttribute( "codeMsg", CodeMsg.MIAO_SHA_OVER.getMsg() );
            return "miaosha_fail";
        }
        MiaoshaOrder miaoshaOrder = miaoshaOrderService.getMiaoshaOrderByUserIdGoodsId(user.getId(),goodsId);
        if ( null != miaoshaOrder ){
            model.addAttribute( "codeMsg", CodeMsg.REPEAT_MIAO_SHA.getMsg() );
            return "miaosha_fail";
        }
        Orderinfo order = miaoshaService.miaosha(user,goods);
        model.addAttribute( "orderInfo",order );
        model.addAttribute( "goods",goods );
        return "order_detail";
    }
}
