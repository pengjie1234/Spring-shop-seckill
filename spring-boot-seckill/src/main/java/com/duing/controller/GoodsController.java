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


import java.util.List;

@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/")
    public String list(Model model){
        List<GoodsVo> result =  goodsService.getGoods();
        model.addAttribute("goodsVoList",result);
        return "list";
    }

    @GetMapping("/goodsDetail/{goodsId}")
    public String goodsDateil(Model model, @PathVariable String goodsId){
        GoodsDateilVo goodsDateilVo = goodsService.getGoodsDateil(goodsId);
        model.addAttribute("goodsDateilVo",goodsDateilVo);
        return "detail";
    }

}
