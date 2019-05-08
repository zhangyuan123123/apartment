package com.jk.controller;

import com.alibaba.fastjson.JSON;
import com.jk.model.Contractzuke;
import com.jk.model.LandlordBean;
import com.jk.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping("apartment")
public class ApartmentController {
    @Autowired
    private ApartmentService apartmentService;
    //显示页面
    @RequestMapping("toindex")
    public String index(){
        return "landlord";
    }
    //清退页面
    @RequestMapping("toqingtui")
    public String qingtui(){
        return "qingtui";
    }
    //选项配置页面 Configuration options
    @RequestMapping("tooptions")
    public String options(){
        return "options";
    }

    //房东合同查询方法
    @RequestMapping("findLandlord")
    @ResponseBody
    public HashMap<String,Object> findLandlord(LandlordBean landlord, Integer start , Integer pageSize){

        return apartmentService.findLandlord(landlord,start,pageSize);
    }

    //查看详情 dialog  findPage
    @RequestMapping("findPage")
    public ModelAndView findPage(Integer id){
        ModelAndView mv = new ModelAndView();
        mv.addObject("id",id);
        mv.setViewName("findPage");
        return mv;
    }
    //查询房东信息  findLandlordInformation
    @RequestMapping("findLandlordInformation")
    @ResponseBody
    public HashMap<String,Object> findLandlordInformation(Integer start , Integer pageSize,Integer id){
        System.out.print(id);
        return apartmentService.findLandlordInformation(start,pageSize,id);
    }
    //查询房源信息 findHousingInformation
    @RequestMapping("findHousingInformation")
    @ResponseBody
    public HashMap<String,Object> findHousingInformation(Integer start , Integer pageSize,Integer id){

        return apartmentService.findHousingInformation(start,pageSize,id);
    }
    //查询合同信息 findContractInformation
    @RequestMapping("findContractInformation")
    @ResponseBody
    public HashMap<String,Object> findContractInformation(Integer start , Integer pageSize,Integer id){

        return apartmentService.findContractInformation(start,pageSize,id);
    }
    //费用结算 findCostSettlement
    @RequestMapping("findCostSettlement")
    @ResponseBody
    public HashMap<String,Object> findCostSettlement(Integer start , Integer pageSize,Integer id){

        return apartmentService.findCostSettlement(start,pageSize,id);
    }
    //清退查询页面 findqingtui
    @RequestMapping("findqingtui")
    @ResponseBody
    public HashMap<String,Object> findqingtui(Integer start , Integer pageSize,Integer id){

        return apartmentService.findqingtui(start,pageSize,id);
    }

    //清退 clearRefund    string address
    @RequestMapping("clearRefund")
    @ResponseBody
    public List<Contractzuke> clearRefund(String address){
        List<Contractzuke> list = apartmentService.clearRefund(address);
        String jsonString = JSON.toJSONString(list);
        System.out.print(jsonString);
        return list;
    }

    //业务区域查询 findyewu
    @RequestMapping("findyewu")
    @ResponseBody
    public List<LinkedHashMap<String,Object>> findyewu(){
        return apartmentService.findyewu();
    }
    //租金支付方式 findzujin
    @RequestMapping("findzujin")
    @ResponseBody
    public List<LinkedHashMap<String,Object>> findzujin(){
        return apartmentService.findzujin();
    }
    //第三方金融 finddisanfang
    @RequestMapping("finddisanfang")
    @ResponseBody
    public List<LinkedHashMap<String,Object>> finddisanfang(){
        return apartmentService.finddisanfang();
    }
    //付款方式 findfukuan
    @RequestMapping("findfukuan")
    @ResponseBody
    public List<LinkedHashMap<String,Object>> findfukuan(){
        return apartmentService.findfukuan();
    }
    //账单类型 findtype
    @RequestMapping("findtype")
    @ResponseBody
    public List<LinkedHashMap<String,Object>> findtype(){
        return apartmentService.findtype();
    }
    //租住方式 findzuzhu
    @RequestMapping("findzuzhu")
    @ResponseBody
    public List<LinkedHashMap<String,Object>> findzuzhu(){
        return apartmentService.findzuzhu();
    }
    //房源户型 findhuxing
    @RequestMapping("findhuxing")
    @ResponseBody
    public List<LinkedHashMap<String,Object>> findhuxing(){
        return apartmentService.findhuxing();
    }
    //房源配置 findpeizhi
    @RequestMapping("findpeizhi")
    @ResponseBody
    public List<LinkedHashMap<String,Object>> findpeizhi(){
        return apartmentService.findpeizhi();
    }


}
