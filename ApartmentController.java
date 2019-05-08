package com.jk.controller;

import com.jk.util.HttpClient;
import com.jk.model.LoginUser;
import com.jk.service.ApartmentService;
import com.jk.util.MenuTree;
import com.jk.util.TreeNoteUtil;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("apartment")
public class ApartmentController {
    @Resource
    private ApartmentService apartmentService;

    /**
     * 去登陆页面
     */
    @RequestMapping("toLogin")
    private String toLogin(){
            return "UserLogin";
    }

    /**
     * 邮箱验证码
     */
    @RequestMapping("getemailCode")
    @ResponseBody
    public String emailCode(String emailName){
        Jedis jedis = new Jedis();
        String flg="1";
        HtmlEmail email=new HtmlEmail();
        int newcode = (int)(Math.random()*899999)+100000;
        Long llen = jedis.llen(emailName);
        if(llen>=3){
            flg="2";
        }else{
        email.setHostName("smtp.163.com");
        try {
            email.setCharset("UTF-8");
            email.addTo(emailName);
            email.setFrom("15942087701@163.com","登录验证码");
            email.setAuthentication("15942087701@163.com","han7758521");
            email.setSubject("验证码");
            email.setMsg(newcode+"");
            email.send();
        }catch (EmailException e) {
            e.printStackTrace();
        }
        jedis.set(emailName+"1",newcode+"");
        jedis.expire(emailName+"1",60*2);
        jedis.lpush(emailName,newcode+"");
        jedis.expire(emailName,60*60*24);

        flg="1";
        }


        return flg;
    }

    /**
     *手机验证码
     */
    @RequestMapping("getphoneCode")
    @ResponseBody
    public String getphoneCode(String phoneName){
         String flg="1";
        Jedis jedis = new Jedis();
        Long llen = jedis.llen(phoneName);
        int newcode = (int)(Math.random()*899999)+100000;
        if(llen>=3){
            flg="2";
        }else{
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("mobile",phoneName);
        hashMap.put("tpl_id","156275");
        hashMap.put("tpl_value","%23code%23%3d"+newcode);
        hashMap.put("key","a70999110fa25d1a7be51036d57272ff");
        String s = HttpClient.sendGet("http://v.juhe.cn/sms/send", hashMap);
       jedis.set(phoneName+"1",newcode+"");
       jedis.expire(phoneName+"1",60*2);
       jedis.lpush(phoneName,newcode+"");
       jedis.expire(phoneName,60*60*24);
        flg="1";
        }
        return flg;
    }

    /**
     *登录
     */
    @RequestMapping("login")
    @ResponseBody
    public HashMap<String, Object> login(LoginUser loginUser){
       //1:登录成功 2:验证码错误 3:账号或密码错误
        Jedis jedis = new Jedis();
        String code=null;
        HashMap<String, Object> hashMap = new HashMap<>();
        if(loginUser!=null){
        if(loginUser.getEmailName()!=null&&loginUser.getEmailName().length()>0){
            code= jedis.get(loginUser.getEmailName() + "1");
        }else{
            code= jedis.get(loginUser.getPhoneName() + "1");
        }
        if(loginUser.getCode()!=null&&code.equals(loginUser.getCode())){
            hashMap=apartmentService.findUserByNamePwd(loginUser);
        }else{
            hashMap.put("flg","2");
        }
        }
        return hashMap;
    }

    /**
     *普通树
     */
    @RequestMapping("getTree")
    @ResponseBody
    private List<MenuTree> getTree(String jobId){
        List<MenuTree> list=apartmentService.getTree(jobId);
        return TreeNoteUtil.getFatherNode(list);
    }

    /**
     *去树页面
     */
    @RequestMapping("toTreePage")
    public String toTreePage(String accounts,String userPassWord,String jobId,String userName,Model model){
        model.addAttribute("accounts",accounts);
        model.addAttribute("userPassWord",userPassWord);
        model.addAttribute("jobId",jobId);
        model.addAttribute("userName",userName);
        return "treePage";
    }
    /**
     * 用户退出
     */
    @RequestMapping("dropout")
    @ResponseBody
    public String dropout(String accounts){
        apartmentService.dropout(accounts);
        return null;
    }
    /**
     * 忘记密码页面
     */
    @RequestMapping("toforgetPasswordPage")
    public String toforgetPasswordPage(){
        return "forgetPasswordPage";
    }
    @RequestMapping("toShow")
    public String toShow(){
        return "show";
    }

}
