package com.platform.controller.material;

import com.platform.controller.AbstractController;
import com.platform.entity.SysUserEntity;
import com.platform.entity.material.QrCodeApplyEntity;
import com.platform.entity.material.QrCodeDetailEntity;
import com.platform.service.material.QrCodeApplyService;
import com.platform.service.material.QrCodeDetailService;
import com.platform.utils.*;
import com.platform.utils.enums.QrCodeEnum;
import com.platform.utils.upload.Upload;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 二维码申请表Controller
 *
 * @author admin
 *  
 * @date 2018-07-24 10:41:09
 */
@Controller
@RequestMapping("qrcodeapply")
public class QrCodeApplyController extends AbstractController {
    @Autowired
    private QrCodeApplyService qrCodeApplyService;

    @Autowired
    private QrCodeDetailService qrCodeDetailService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        SysUserEntity user = getUser();
        if (user != null && user.getEnterpriseId() != null){
            params.put("enterpriseId",user.getEnterpriseId());
        }
        //查询列表数据
        Query query = new Query(params);

        List<QrCodeApplyEntity> qrCodeApplyList = qrCodeApplyService.queryList(query);
        int total = qrCodeApplyService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(qrCodeApplyList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("qrcodeapply:info")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        QrCodeApplyEntity qrCodeApply = qrCodeApplyService.queryObject(id);

        return R.ok().put("qrCodeApply", qrCodeApply);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("qrcodeapply:save")
    @ResponseBody
    public R save(@RequestBody QrCodeApplyEntity qrCodeApply) {
        SysUserEntity user = getUser();
        if (null !=  user){
            qrCodeApply.setApplicant(user.getUserId());
            qrCodeApply.setEnterpriseId(user.getEnterpriseId());
        }
        Date time = new Date();
        qrCodeApply.setCreateDate(time);
        qrCodeApply.setApplyDate(time);
        qrCodeApply.setBatchNo(RandomUtils.generateSNNumber()); // 批次号
        qrCodeApply.setQrCodeStatus(QrCodeEnum.PENDING.getCode()); //待审核
        qrCodeApply.setIsGenerated(QrCodeEnum.UNGENERATED.getCode()); //未生成
        qrCodeApplyService.save(qrCodeApply);

        return R.ok();
    }

    /**
     * 生成二维码
     * @param qrCodeApply
     * @return
     */
    @RequestMapping("/generateQr")
    //@RequiresPermissions("qrcodeapply:update")
    @ResponseBody
    public R generateQr(@RequestBody QrCodeApplyEntity qrCodeApply) {
        Map<String, String> properties = null;
        try {
            properties = PropertiesUtil.getInstance("/upload.properties");
        } catch (Exception e) {
            e.printStackTrace();
        }
        SysUserEntity user = getUser();
        if (user == null){ return  R.error("系统用户未找到，请联系管理员！");}
        int num = qrCodeApply.getQuantity();
        QrCodeDetailEntity detailEntity = null;
        for (int i = 0 ; i < num; i++ ){

            String idCode = qrCodeApply.getBatchNo() + "" + i; //识别码
            String qrCode  = null;
            InputStream is = null;
            try {
                String fileName  = idCode+".png";
                String path = QrCodeCreateUtil.generateQRCode(idCode,300,300,"png","/tmp/"+fileName);
                File file = new File(path);
                //is = new FileInputStream(file);
               // qrCode  =  OSSFactory.build().upload(is,QrCodeCreateUtil.getPath("qrCode")); //二维码地址
                String remotePath = properties.get("remotePath") + "mall/qrCode/";
                Upload.ftpFile(path, remotePath);//上传到服务器
                qrCode = properties.get("dirPath") + "mall/qrCode/"+fileName;
                file.delete(); // 执行成功，删除临时文件
            } catch (Exception e) {
                e.printStackTrace();
            }
            detailEntity = new QrCodeDetailEntity();
            detailEntity.setIdCode(idCode); //识别码
            detailEntity.setQrCode(qrCode);
            detailEntity.setApplyId(qrCodeApply.getId());
            qrCodeDetailService.save(detailEntity);
        }
        qrCodeApply.setGenerateDate(new Date());
        qrCodeApply.setGenerateMan(user.getUserId());
        //qrCodeApply.setQrCodeStatus(QrCodeEnum.GRANTED.getCode()); //已发放
        qrCodeApply.setIsGenerated(QrCodeEnum.GENERATED.getCode()); //已生成
        qrCodeApplyService.update(qrCodeApply);

        return R.ok();
    }

    /**
     * 发放
     * @param qrCodeApply
     * @return
     */
    @RequestMapping("/grant")
    //@RequiresPermissions("qrcodeapply:update")
    @ResponseBody
    public R grant(@RequestBody QrCodeApplyEntity qrCodeApply) {
        qrCodeApply.setQrCodeStatus(QrCodeEnum.GRANTED.getCode());
        qrCodeApplyService.update(qrCodeApply);

        return R.ok();
    }

    /**
     * 驳回
     * @param qrCodeApply
     * @return
     */
    @RequestMapping("/reject")
    //@RequiresPermissions("qrcodeapply:update")
    @ResponseBody
    public R reject(@RequestBody QrCodeApplyEntity qrCodeApply) {
        qrCodeApply.setQrCodeStatus(QrCodeEnum.REJECT.getCode());
        qrCodeApplyService.update(qrCodeApply);

        return R.ok();
    }




    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("qrcodeapply:update")
    @ResponseBody
    public R update(@RequestBody QrCodeApplyEntity qrCodeApply) {
        qrCodeApplyService.update(qrCodeApply);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("qrcodeapply:delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        qrCodeApplyService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<QrCodeApplyEntity> list = qrCodeApplyService.queryList(params);

        return R.ok().put("list", list);
    }
}
