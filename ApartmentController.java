package com.jk.controller;

import com.jk.model.Contractzuke;
import com.jk.model.Income;
import com.jk.service.ApartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;

@Controller
@RequestMapping("apartment")
public class ApartmentController {

    @Resource
    ApartmentService apertmentService;

    /**
     * 查询
     */
    @RequestMapping("toContract")
    public String tocontract(){

        return "contract";
    }

    @RequestMapping("findcontract")
    @ResponseBody
    public HashMap<String, Object> findcontract(Contractzuke contract,Integer start,Integer pageSize) {

        return apertmentService.findcontract(contract,start,pageSize);
    }


    /**
     * 查看详情
     */
    @RequestMapping("findAll")
    public ModelAndView findAll(Integer id){
        ModelAndView mv=new ModelAndView();
        mv.addObject("id",id);
        mv.setViewName("findAll");
        return mv;
    }

    /**
     * 查询联系人
     */
    @RequestMapping("findContact")
    @ResponseBody
    public HashMap<String, Object> findContact(Integer start,Integer pageSize,Integer id){

        return apertmentService.findContact(start,pageSize,id);
    }

    /**
     * 查询合同信息
     */
    @RequestMapping("findContractinfo")
    @ResponseBody
    public HashMap<String, Object> findContractinfo(Integer start,Integer pageSize,Integer id){

        return apertmentService.findContractinfo(start,pageSize,id);
    }

    /**
     * 查询费用
     */
    @RequestMapping("findCost")
    @ResponseBody
    public HashMap<String, Object> findCost(Integer start,Integer pageSize,Integer id){

        return apertmentService.findCost(start,pageSize,id);
    }

    /**
     * 收支流水
     */
    @RequestMapping("findIncome")
    @ResponseBody
    public HashMap<String, Object> findIncome(Income income,Integer start, Integer pageSize){

        return apertmentService.findIncome(income,start,pageSize);
    }

    @RequestMapping("tofindIncome")
    public String findIncome(){

        return "Income";
    }


}
