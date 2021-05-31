package com.gqz.mm.controller;

import com.gqz.mm.entity.Result;
import com.gqz.mm.utils.UploadUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 包名:com.itheima.mm.controller
 *
 * @author Leevi
 * 日期2020-08-04  14:38
 */
@RestController
public class CommonController {
    @RequestMapping("/common/uploadFile")
    public Result uploadFile(HttpServletRequest request, MultipartFile item) throws IOException {
        try {
            String imgUrl;


            String uploadPath = request.getServletContext().getRealPath("img/upload" + UploadUtils.getDir());
            //创建一个File
            File file = new File(uploadPath);
            if (!file.exists()) {
                //这个目录不存在
                //将这个目录创建出来
                file.mkdirs();
            }
            String filename = item.getOriginalFilename();

            //文件名会不会重复呢????最好的办法是生成唯一的文件名
            String uuidName = UploadUtils.getUUIDName(filename);
            item.transferTo(new File(file, uuidName));

            //将输入流中的文件，通过输出流写到指定的目录中

            //获取文件的存储路径imgUrl
            imgUrl = "img/upload/" + uuidName;


            return new Result(true, "图片上传成功", imgUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, "图片上传失败");
        }

    }
}
