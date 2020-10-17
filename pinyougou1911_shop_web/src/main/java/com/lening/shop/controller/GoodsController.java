package com.lening.shop.controller;

import com.lening.pojogroup.Goods;
import com.lening.sellergoods.service.GoodsService;
import entity.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @date： 2020/9/7
 * @author：Dream
 */
@RequestMapping("/goods")
@RestController
public class GoodsController {
    @Resource
    private GoodsService goodsService;

    @RequestMapping("/upload")
    public Result upload(@Param("file") MultipartFile file, HttpServletRequest request) {
        String url = "";
        try {
            if (!file.isEmpty()) {
                String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
                Date date = new Date();
                DateFormat dfyear = new SimpleDateFormat("yyyy");
                DateFormat dfyue = new SimpleDateFormat("MM/dd");
                url += dfyear.format(date) + "/" + sellerId + "/" + dfyue.format(date);
                System.out.println(url);

                /**
                 * 获取项目中image的真实路径，然后才能通过流写进去
                 *
                 */
                String realPath = request.getSession().getServletContext().getRealPath("/image/");
                System.out.println(realPath);
                url = realPath + "/" + url;

                /**
                 * 存放原来的名字还是uuid，有商家可以不用uuid，原来的名字就OK啦
                 */
                url += "/" + file.getOriginalFilename();

                File newfile = new File(url);
                if (!newfile.exists()) {
                    newfile.mkdirs();
                }
                file.transferTo(newfile);
                String rsurl = "http://localhost:9102/image/" + dfyear.format(date) + "/" + sellerId + "/" + dfyue.format(date) + "/" + file.getOriginalFilename();

                String remoteAddr = request.getRemoteAddr();
                int remotePort = request.getRemotePort();
                int localPort = request.getLocalPort();

                System.out.println(rsurl);
                /**
                 * 获取项目的服务路径
                 */
                return new Result(true, rsurl);
            } else {
                return new Result(false, "图片为空");
            }

        } catch (Exception e) {
            return new Result(false, "图片上传失败");
        }
    }


    @RequestMapping("/save")
    public Result save(@RequestBody Goods goods) {
        try {
            String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
            goods.getGoods().setSellerId(sellerId);
            goodsService.save(goods);
            return new Result(true, "编辑成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "编辑失败");
        }

    }

}
