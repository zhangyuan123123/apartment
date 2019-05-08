package com.jk.controller;

import com.jk.bean.Already;
import com.jk.bean.Charge;
import com.jk.bean.Wait;
import com.jk.service.ZuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

@Controller
@RequestMapping("apartment")
public class ZuController {

    @Resource
    private ZuService zuService;


    /**
     * 跳转代收账单页面
     */
    @RequestMapping("toCharge")
    public String toCharge(){
        return "findCharge";
    }

    /**
     * 跳转代支付账单页面
     */
    @RequestMapping("toWait")
    public String toWait(){
        return "findWait";
    }


    /**
     * 页面顶部代收总额查询
     */
    @RequestMapping("findPriceCount")
    @ResponseBody
    public Integer findPriceCount(){
        Integer count=zuService.findPriceCount();
        return count;
    }

    /**
     * 代收账单查询
     * @return
     */
    @RequestMapping("findCharge")
    @ResponseBody
    public HashMap<String ,Object> findCharge(Integer page,Integer rows,Charge charge){
        HashMap<String ,Object> hashMap=zuService.findCharge(page,rows,charge);
        return hashMap;
    }


    /**
     * 确认收款 updateStatus
     */
    @RequestMapping("updateStatus")
    @ResponseBody
    public String updateStatus(Integer room_id){
        zuService.updateStatus(room_id);
        return null;
    }


    /**
     * 确认收款打开弹框  toPage
     */
    @RequestMapping("toPage")
    public String toPage(Integer id,Model model){
        Charge charge=zuService.findChargeById(id);
        model.addAttribute("charge",charge);
        return "updatePage";
    }


    /**
     * 查看更多信息   toFindPage
     */
    @RequestMapping("toFindPage")
    public String toFindPage(Integer id,Model model){
        Charge charge=zuService.toFindPage(id);
        model.addAttribute("one",charge);
        return "updatePageAll";
    }


    /**
     * 点击确认收款弹框的提交 addAlready
     */
    @RequestMapping("addAlready")
    @ResponseBody
    public String addAlready(Already already){
        Integer roomId = already.getRoomId();
        zuService.addAlready(roomId,already);
        return null;
    }


    /**
     * 待支付页面顶部代支总额  findWaitPriceCount
     */
    @RequestMapping("findWaitPriceCount")
    @ResponseBody
    public Integer findWaitPriceCount(){
        Integer count=zuService.findWaitPriceCount();
        return count;
    }


    /**
     * 待支付页面查询  findWait
     */
    @RequestMapping("findWait")
    @ResponseBody
    public HashMap<String ,Object> findWait(Integer page, Integer rows, Wait wait){
        HashMap<String ,Object> hashMap=zuService.findWait(page,rows,wait);
        return hashMap;
    }




}
