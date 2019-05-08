package com.jk.controller;

import com.jk.model.JobModel;
import com.jk.model.UserModel;
import com.jk.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


@Controller
@RequestMapping("apartment")
public class TimeLineController {
    @Resource
    private AccountService accountService;



    //账号信息页
    @RequestMapping("referuser")
    public String  referuser(UserModel user, Model model){
        UserModel userModel=accountService.referuser(user);
        model.addAttribute("user",userModel);
        return "Count";
    }
    //跳转增加子账户页面
    @RequestMapping("tosaveandupdate")
    public String tosaveandupdate(){
        return "saveandupdate";
    }
    //跳转查询页面
    @RequestMapping("toshow")
    public String toshow(){
        return "show";
    }
    //人员配置页面
    @RequestMapping("refer")
    @ResponseBody
    public HashMap<String,Object> refer(Integer start,Integer pageSize){

        return accountService.refer(start,pageSize);
    }

    //删除
    @RequestMapping("deleteuser")
    @ResponseBody
    public void deleteuser(Integer sid){
        accountService.deleteuser(sid);
    }

    //添加子账户
    @RequestMapping("saveuser")
    @ResponseBody
    public void saveuser(UserModel usermodel){
        if (usermodel.getUserId()==null) {
            accountService.saveuser(usermodel);
        }else{
            accountService.updateuser(usermodel);
        }
    }

    //跳转修改回显页面
    @RequestMapping("toupdate")
    public String toupdate(Integer xid,Model model){
        UserModel user=accountService.toupdate(xid);
        return "saveandupdate";
    }
    @RequestMapping("toposition")
    public String toposition(){
        return "position";
    }
    @RequestMapping("referposition")
    @ResponseBody
    public HashMap<String,Object> referposition(Integer start,Integer pageSize){

         return accountService.referposition(start,pageSize);
    }
    @RequestMapping("deletejob")
    @ResponseBody
    public void deletejob(Integer sid){
        accountService.deletejob(sid);
    }
    @RequestMapping("tosaveposition")
    public String tosaveposition(){
        return "saveandupdateposition";
    }

    //findMenu ztree显示
    @RequestMapping("findMenu")
    @ResponseBody
    public  List<LinkedHashMap<String, Object>> findMenu(){
        return  accountService.findMenu();
    }
    @RequestMapping("referpositiontwo")
    @ResponseBody
    public List<JobModel> referpositiontwo(){
        return accountService.referpositiontwo();
    }
}
