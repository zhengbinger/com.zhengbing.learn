package com.zhengbing.learn.miaosha.controller;

import com.zhengbing.learn.miaosha.common.CodeMsg;
import com.zhengbing.learn.miaosha.common.Result;
import com.zhengbing.learn.miaosha.common.exception.GlobalException;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/do_miaosha", method = RequestMethod.POST)
    @ResponseBody
    public Result<Orderinfo> do_miaosha( Model model, MiaoshaUser user, @RequestParam("goodsId") long goodsId ){
        model.addAttribute( "user",user );
        if( null == user ){
            throw new GlobalException( CodeMsg.MIAO_SHA_ERROR );
        }

        GoodsVO goods = goodsService.getGoodVOByGoodsId( goodsId );
        if ( goods.getStockCount() <= 0 ){
            model.addAttribute( "codeMsg", CodeMsg.MIAO_SHA_OVER.getMsg() );
            throw new GlobalException( CodeMsg.MIAO_SHA_OVER );
        }
        MiaoshaOrder miaoshaOrder = miaoshaOrderService.getMiaoshaOrderByUserIdGoodsId(user.getId(),goodsId);
        if ( null != miaoshaOrder ){
            model.addAttribute( "codeMsg", CodeMsg.REPEAT_MIAO_SHA.getMsg() );
            throw new GlobalException( CodeMsg.REPEAT_MIAO_SHA );
        }
        Orderinfo order = miaoshaService.miaosha(user,goods);

        return Result.success( order );
    }
}
