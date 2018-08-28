package com.platform.controller.app;

import com.platform.constants.CommonConstant;
import com.platform.service.SysRegionService;
import com.platform.service.enterprise.IEnterpriseService;
import com.platform.service.material.MaterialTypeService;
import com.platform.utils.ErrorCode;
import com.platform.utils.FtpUpload;
import com.platform.utils.PropertiesUtil;
import com.platform.utils.R;
import com.platform.utils.upload.UploadVo;
import com.platform.vo.TreeVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/7/28 14:57
 * ModifyUser: bjlixiaopeng
 * Class Description: 公共Controller，包括查询物品类型、区域树等等
 * To change this template use File | Settings | File and Code Template
 */

@RestController
@RequestMapping("/app")
public class AppCommonController{

    @Autowired
    private MaterialTypeService materialTypeService;

    @Autowired
    private IEnterpriseService enterpriseService;

    @Autowired
    private SysRegionService regionService;

    @RequestMapping(value = "/material/type/list")
    @ResponseBody
    public R queryMaterialTypeList(){
        List<TreeVo> list = materialTypeService.loadAllMaterialType();
        return R.succeed().put(CommonConstant.DATA_KEY,list);
    }

    @RequestMapping(value = "/enterprise/list")
    @ResponseBody
    public R queryEnterpriseByRegionId(@RequestParam("regionId") Integer regionId){
        if (regionId == null) {
            return R.paramsIllegal();
        }

        return R.succeed().put(CommonConstant.DATA_KEY,enterpriseService.loadEnterpriseByRegionId(regionId));
    }

    @RequestMapping(value = "/region/list")
    @ResponseBody
    public R queryAllRegion(){
        return R.succeed().put(CommonConstant.DATA_KEY,regionService.queryAllRegion());
    }

    @RequestMapping(value = "/wx/region/list")
    @ResponseBody
    public R queryAllRegionForWeixin(){
        return R.succeed().put(CommonConstant.DATA_KEY,regionService.buildWeixinTree());
    }

    @RequestMapping("/upload")
    public Object upLoad( HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        //设置字符编码防止乱码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        List<MultipartFile> fileList = multipartRequest.getFiles("file");
        String platformCode = multipartRequest.getParameter("platformCode");
        String dirFolderName = multipartRequest.getParameter("dirFolderName");
        List<UploadVo> uploadFiles  = null;
        try {
            uploadFiles  = FtpUpload.upload(fileList,platformCode,dirFolderName,PropertiesUtil.getInstance("/upload.properties"));
            R r = new R();
            r.put("code",ErrorCode.SUCCEED.getCode());
            StringBuilder urls = new StringBuilder();
            for (UploadVo uploadFile : uploadFiles) {
                urls.append(uploadFile.getFileServerPath());
                urls.append(",");
            }
            if (urls.length()>0) {
                urls.setLength(urls.length()-1);
            }
            r.put("url",urls);
            return r;
        } catch (Exception e) {
            R r = new R();
            r.put("code",ErrorCode.FAILURE.getCode());
            return r;
        }


    }

}
