package com.zhengbing.learn.miaosha.controller;

import com.zhengbing.learn.miaosha.common.redis.RedisService;
import com.zhengbing.learn.miaosha.entity.MiaoshaUser;
import com.zhengbing.learn.miaosha.entity.vo.GoodsVO;
import com.zhengbing.learn.miaosha.service.GoodsService;
import com.zhengbing.learn.miaosha.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by zhengbing on 2019/5/30.
 * Since Version 1.0
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @Autowired
    RedisService redisService;

    @Autowired
    GoodsService goodsService;

    @RequestMapping("to_list")
    public String toGoods( Model model, MiaoshaUser user){
        model.addAttribute( "user",user );

        // 查询商品列表
        List<GoodsVO> goodsVOList = goodsService.listGoodsVO();

        model.addAttribute( "goodsList",goodsVOList );
        return "goods_list";
    }

    @RequestMapping("to_detail/{goodsId}")
    public String toDetail( Model model,MiaoshaUser user, @PathVariable( "goodsId" )long goodsId){
        model.addAttribute( "user",user );

        // 查询商品列表
        List<GoodsVO> goodsVOList = goodsService.listGoodsVO();

        model.addAttribute( "goodsList",goodsVOList );

       GoodsVO goods = goodsService.getGoodVOByGoodsId(goodsId);

       model.addAttribute( "goods",goods );
        long start = goods.getStartDate().getTime();
        long end = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int miaoShaStatus = 0;
        int remainSeconds = 0;
        // 秒杀还没开始  进行倒计时
        if ( now < start ){
            miaoShaStatus = 0;
            remainSeconds = (int)(start-now);
        } else if ( now > start ){
            miaoShaStatus = 2;
            remainSeconds = -1;
            // 秒杀已结束
        } else {
            // 秒杀进行时
            miaoShaStatus = 1;
            remainSeconds = 0;
        }

        model.addAttribute( "remainSeconds",remainSeconds/1000 );
        model.addAttribute( "miaoshaStatus",miaoShaStatus );
        return "goods_detail";
    }



}
