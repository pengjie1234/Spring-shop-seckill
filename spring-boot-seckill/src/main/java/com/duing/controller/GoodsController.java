package com.duing.controller;

import com.duing.model.Goods;
import com.duing.services.GoodsService;
import com.duing.vo.GoodsDateilVo;
import com.duing.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/")
    public String list(Model model){
//        List<GoodsVo> result =  goodsService.getGoods();
        List<GoodsVo> result =  goodsService.selectOne();
        model.addAttribute("goodsVoList",result);
        return "list";
    }

    @GetMapping("/goodsDetail/{goodsId}")
    public String goodsDateil(Model model, @PathVariable String goodsId){
        GoodsDateilVo goodsDateilVo = goodsService.getGoodsDateil(goodsId);
        Date startTime = goodsDateilVo.getStartTime();
        Date endTime = goodsDateilVo.getEndTime();
        Date nowTime = new Date();
        int status;
        int countDown = -1;
        if(nowTime.before(startTime)){
//            秒杀未开始
            countDown = (int) ((startTime.getTime() - nowTime.getTime()) / 1000);
            status = 0;
        }else if(nowTime.after(endTime)){
//            秒杀已结束
            status = 2;
        }else{
//            秒杀进行中
            status = 1;

        }
        System.out.println(startTime);
        System.out.println(endTime);

        model.addAttribute("goodsDateilVo",goodsDateilVo);
        model.addAttribute("startTime",startTime);
        model.addAttribute("endTime",endTime);
        model.addAttribute("status",status);
        model.addAttribute("countDown",countDown);
        return "detail";
    }

}
