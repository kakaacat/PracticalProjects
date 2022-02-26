package com.sqs.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sqs.pojo.ProductInfo;
import com.sqs.pojo.vo.ProductVo;
import com.sqs.service.ProductInfoService;
import com.sqs.utils.FileNameUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @Author : kaka
 * @Date: 2022-02-22 15:45
 */
@Controller
@RequestMapping("/prod")
public class ProductInfoAction {
    //每页显示的记录数
    public static final int PAGE_SIZE = 5;
    public String saveFileName = "";//异步上传存文件名，增加和更新用这个文件名

    //界面层(springMVC)有业务逻辑层(spring)的对象
    @Autowired
    private ProductInfoService productInfoService;

    //显示所有商品不分页
    @RequestMapping("/getAll")
    public String getAll(HttpServletRequest request) {
        List<ProductInfo> list = productInfoService.getAll();
        request.setAttribute("list", list);
        return "product";
    }

    //显示第一页的5条记录
    @RequestMapping("/split")
    public String split(HttpServletRequest request){
        //得到第一页的数据
        PageInfo info = productInfoService.splitPage(1, PAGE_SIZE);
        request.setAttribute("info", info);
        return "product";
    }

    //ajax分页翻页处理
    @ResponseBody
    @RequestMapping("/ajaxsplit")
    public void ajaxSplit(int page, HttpSession session) {
        //取当前page参数的页面的数据
        PageInfo info = productInfoService.splitPage(page, PAGE_SIZE);
        session.setAttribute("info", info);
    }

    //删除后分页显示,切记切记切记：坑：使用@ResponseBody注解，返回值不能是String,
    // 如果一定要使用String,则手工封装成JSON格式
    @ResponseBody
    @RequestMapping(value = "/dajaxsplit",produces = "text/html;charset=UTF-8")
    public Object dajaxsplit(ProductVo vo, HttpSession session) {
        PageInfo info = productInfoService.splitPageVo(vo, PAGE_SIZE);
        session.setAttribute("pb", info);
        saveFileName = "";

        //手工封装返回删除成功或删除失败字符串为JSON格式
        String s=session.getAttribute("msg").toString();
        JSONObject object=new JSONObject();
        object.put("msg",s);
        return object.toString();
    }
    //执行删除操作
    @RequestMapping("/delete")
    public String delete(Integer pid, HttpSession session) {
        int num = productInfoService.delete(pid);
        if (num > 0) {
            session.setAttribute("msg", "删除成功！");
        } else {
            session.setAttribute("msg", "删除失败！");
        }
        //增删改后用重定向跳转
        return "forward:/prod/dajaxsplit.action";
    }

    //执行批量删除操作
    @RequestMapping("/deleteBatch")
    public String deleteBatch(String pids, HttpSession session) {
        String []ps=pids.split(",");
        try {
            int num = productInfoService.deleteBatch(ps);
            if (num > 0) {
                session.setAttribute("msg", "删除成功！");
            } else {
                session.setAttribute("msg", "删除失败！");
            }
        } catch (Exception e) {

            session.setAttribute("msg","商品不可删除！");
        }

        //增删改后用重定向跳转
        return "forward:/prod/dajaxsplit.action";
    }
    @ResponseBody
    @RequestMapping("/ajaxImg")
    public Object ajaxImg(MultipartFile pimage, HttpServletRequest request){
        //要进行文件上传操作
        //取文件名
        saveFileName = FileNameUtil.getUUIDFileName() + FileNameUtil.getFileType(pimage.getOriginalFilename());
        //取路径
        try {
            String path = request.getServletContext().getRealPath("/image_big");
            //转存
            pimage.transferTo(new File(path + File.separator + saveFileName));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //为了在客户端显示图片，要将存储的文件名回传下去，由于是自定义的上传插件，所以此处要手工处理JSON
        JSONObject object = new JSONObject();
        object.put("imgurl",saveFileName);
        //切记切记：JSON对象一定要toString()回到客户端
        return object.toString();
    }
    @RequestMapping("/one")
    public String one(Integer pid,Integer page, Model model) {
        ProductInfo info = productInfoService.getById(pid);
        model.addAttribute("prod", info);
        model.addAttribute("page",page);
        return "update";
    }
    //执行增加操作
    @RequestMapping("/save")
    public String save(ProductInfo info, HttpServletRequest request) {



        //图片处理好后，设置到商品对象中
        info.setpImage(saveFileName);
        info.setpDate(new Date());

        //到此为止，商品对象构建完毕，有自动从表单元素注入的，有上传图片的，有上架日期
        //完成数据库增加操作
        int num = -1;
        try {
            num = productInfoService.save(info);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (num > 0){
            request.getSession().setAttribute("msg", "增加成功！");
        } else {
            request.getSession().setAttribute("msg", "增加失败!");
        }

        saveFileName="";
        //增删改后用重定向跳转
        return "redirect:/prod/split.action";
    }

    //更新操作
    @RequestMapping("/update")
    public String update(ProductInfo info,Integer page,HttpServletRequest request) {

        //因为是修改，所以要判断有没有点文件上传的铵钮
        if(!saveFileName.equals("")){
            info.setpImage(saveFileName);
        }

        boolean flag = false;
        //有没有时间改变
        //此处要进行info对象的上架时间的更新
        //首先要判断当前新更新上来的日期不能大于当前日期
        if (info.getpDate() != null) {
            if (info.getpDate().getTime() > System.currentTimeMillis()) {
                //日期不正常了，则置为空，底层做更新处理时不做更改
                info.setpDate(null);
                flag=true;
            }
        }
        //完成对象更新
        int num = -1;
        try {
            num = productInfoService.update(info);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (num > 0) {
            if (flag) {
                request.getSession().setAttribute("msg", "日期不能大于当前日期");
            } else {
                request.getSession().setAttribute("msg", "更新成功！");
            }
        } else {
            request.getSession().setAttribute("msg", "更新失败");
        }
        saveFileName="";
        return "redirect:/prod/split.action?page="+page;
    }


}
