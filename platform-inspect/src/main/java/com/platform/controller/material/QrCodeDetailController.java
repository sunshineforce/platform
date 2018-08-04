package com.platform.controller.material;

import com.platform.entity.material.QrCodeDetailEntity;
import com.platform.service.material.QrCodeDetailService;
import com.platform.utils.*;
import com.platform.utils.excel.ExcelExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 二维码详情表Controller
 *
 * @author admin
 *  
 * @date 2018-08-04 10:17:20
 */
@Controller
@RequestMapping("qrcodedetail")
public class QrCodeDetailController {
    @Autowired
    private QrCodeDetailService qrCodeDetailService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<QrCodeDetailEntity> qrCodeDetailList = qrCodeDetailService.queryQrCodeDetails(query);
        int total = qrCodeDetailService.queryQrCodeDetailsTotal(query);

        PageUtils pageUtil = new PageUtils(qrCodeDetailList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    @RequestMapping("/export")
    public void export(@RequestParam Map<String, Object> params,HttpServletResponse response) {
        List<QrCodeDetailEntity> list = qrCodeDetailService.queryQrCodeDetails(params);
        ExcelExport excelExport = new ExcelExport();
        String[] colCaption = {"二维码","批次号","生成人员","生成时间","绑定人姓名","绑定时间","物品名称","物品类型"};
        String sheetName = "物品二维码详情信息" + System.currentTimeMillis()+".xls";
        List<Object[]> objectList = new ArrayList<>();
        for (QrCodeDetailEntity detail : list) {
            Object[] obj = new Object[colCaption.length];
            obj[0] = StringUtils.NullToString(detail.getQrCode(),"--");
            obj[1] = StringUtils.NullToString(detail.getBatchNo(),"--");
            obj[2] = StringUtils.NullToString(detail.getGenerateUserName(),"--");
            obj[3] = detail.getGenerateTime() != null ? DateUtils.format(detail.getGenerateTime() ,DateUtils.DATE_TIME_PATTERN) : "--";
            obj[4] = StringUtils.NullToString(detail.getBindUserName(),"--");
            obj[5] = detail.getBindTime() != null ? DateUtils.format(detail.getBindTime() ,DateUtils.DATE_TIME_PATTERN) : "--";
            obj[6] = StringUtils.NullToString(detail.getMatetialName(),"--");
            obj[7] = StringUtils.NullToString(detail.getMaterialTypeName(),"--");
            objectList.add(obj);
        }
        try {
            excelExport.addSheetByArray(sheetName,objectList,colCaption);
            excelExport.export(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        QrCodeDetailEntity qrCodeDetail = qrCodeDetailService.queryObject(id);

        return R.ok().put("qrCodeDetail", qrCodeDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @ResponseBody
    public R save(@RequestBody QrCodeDetailEntity qrCodeDetail) {
        qrCodeDetailService.save(qrCodeDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @ResponseBody
    public R update(@RequestBody QrCodeDetailEntity qrCodeDetail) {
        qrCodeDetailService.update(qrCodeDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        qrCodeDetailService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<QrCodeDetailEntity> list = qrCodeDetailService.queryList(params);

        return R.ok().put("list", list);
    }
}
