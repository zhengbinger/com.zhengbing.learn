package com.zhengbing.learn.miaosha.controller;

import com.zhengbing.learn.miaosha.common.Result;
import com.zhengbing.learn.miaosha.common.redis.GoodsKey;
import com.zhengbing.learn.miaosha.common.redis.RedisService;
import com.zhengbing.learn.miaosha.entity.MiaoshaUser;
import com.zhengbing.learn.miaosha.entity.vo.GoodsDetailVO;
import com.zhengbing.learn.miaosha.entity.vo.GoodsVO;
import com.zhengbing.learn.miaosha.service.GoodsService;
import com.zhengbing.learn.miaosha.service.MiaoshaUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    ApplicationContext applicationContext;

    /**
     * 跳转商品列表页面
     *
     * @param model
     * @param user
     * @return
     */
//    @RequestMapping("to_list")
//    public String toGoods( Model model, MiaoshaUser user){
//        model.addAttribute( "user",user );
//
//        // 查询商品列表
//        List<GoodsVO> goodsVOList = goodsService.listGoodsVO();
//
//        model.addAttribute( "goodsList",goodsVOList );
//
//        return "goods_list";
//    }

    /**
     * 使用页面缓存，到商品列表页面
     * 缓存实践不宜过长，过长会数据过期
     *
     * @param model
     * @param user
     * @return
     */
    @RequestMapping(value = "to_list",produces = "text/html")
    @ResponseBody
    public String toGoodsList( HttpServletRequest request, HttpServletResponse response, Model model, MiaoshaUser user){
        model.addAttribute( "user",user );

        // 查询商品列表
        List<GoodsVO> goodsVOList = goodsService.listGoodsVO();

        model.addAttribute( "goodsList",goodsVOList );

        // 获取缓存
        String html = redisService.get( GoodsKey.getGoodsList,"",String.class );
        if ( StringUtils.isNotEmpty(html)){
            return html;
        }

        // 缓存未命中 手动渲染
        WebContext ctx = new WebContext(request,response,request.
                getServletContext(),request.getLocale(),model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process( "goods_list",ctx );

        // 设置缓存
        if(StringUtils.isNotEmpty( html )){
            redisService.set( GoodsKey.getGoodsList,"",html );
        }

        // 响应结果
        return html;
//        return "goods_list";
    }

//    @RequestMapping("to_detail/{goodsId}")
//    public String toDetail( Model model,MiaoshaUser user, @PathVariable( "goodsId" )long goodsId){
//        model.addAttribute( "user",user );
//
//        // 查询商品列表
//        List<GoodsVO> goodsVOList = goodsService.listGoodsVO();
//
//        model.addAttribute( "goodsList",goodsVOList );
//
//        GoodsVO goods = goodsService.getGoodVOByGoodsId(goodsId);
//
//        model.addAttribute( "goods",goods );
//        long start = goods.getStartDate().getTime();
//        long end = goods.getEndDate().getTime();
//        long now = System.currentTimeMillis();
//
//        int miaoShaStatus = 0;
//        int remainSeconds = 0;
//        // 秒杀还没开始  进行倒计时
//        if ( now < start ){
//            miaoShaStatus = 0;
//            remainSeconds = (int)(start-now);
//        } else if ( now > start ){
//            miaoShaStatus = 2;
//            remainSeconds = -1;
//            // 秒杀已结束
//        } else {
//            // 秒杀进行时
//            miaoShaStatus = 1;
//            remainSeconds = 0;
//        }
//
//        model.addAttribute( "remainSeconds",remainSeconds/1000 );
//        model.addAttribute( "miaoshaStatus",miaoShaStatus );
//        return "goods_detail";
//    }


    /**
     * 跳转商品详情页面+URL缓存
     *
     * @param model
     * @param user
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "detail/{goodsId}")
    @ResponseBody
    public Result<GoodsDetailVO> toGoodsDetail( HttpServletRequest request, HttpServletResponse response, Model model, MiaoshaUser user, @PathVariable( "goodsId" )long goodsId){

        // 查询商品列表
        List<GoodsVO> goodsVOList = goodsService.listGoodsVO();

        model.addAttribute( "goodsList",goodsVOList );

        GoodsVO goods = goodsService.getGoodVOByGoodsId(goodsId);

        long start = goods.getStartDate().getTime();
        long end = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int miaoShaStatus = 0;
        int remainSeconds = 0;
        // 秒杀还没开始  进行倒计时
        if ( now < start ){
            miaoShaStatus = 0;
            remainSeconds = (int)(start-now);
        } else if ( now > end ){
            miaoShaStatus = 2;
            remainSeconds = -1;
            // 秒杀已结束
        } else {
            // 秒杀进行时
            miaoShaStatus = 1;
            remainSeconds = 0;
        }

        GoodsDetailVO detail = new GoodsDetailVO();
        detail.setGoods( goods );
        detail.setMiaoshaUser( user );
        detail.setMiaoshaStatus( miaoShaStatus );
        detail.setRemainSeconds( remainSeconds );

        model.addAttribute( "detail",detail );
        return Result.success( detail );
    }


}
