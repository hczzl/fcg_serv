package com.glch.estateRegister.service.impl;

import com.glch.base.util.ExcelHandleUtils;
import com.glch.base.util.FileUtil;
import com.glch.base.util.FileZipCompressorUtil;
import com.glch.base.util.StringUtil;
import com.glch.estateRegister.entity.EstateRegister;
import com.glch.estateRegister.entity.vo.RegisVo;
import com.glch.estateRegister.service.DownService;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.datanucleus.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhongzhilong
 * @date 2020/7/12 0012
 */
@Service
public class DownServiceImpl implements DownService {
    @Override
    public String downloadTotalComprehensive(List<Map<String, Object>> resultList, String date, RegisVo regisVo) {
        try {
            String rootDir = "/";
            File rootFiles = new File(rootDir);
            if (!rootFiles.exists()) {
                rootFiles.mkdirs();
            }
            String resultFileDir = null;
            StringBuilder fileName = new StringBuilder("");
            fileName.append("小区登记表信息");
            if (!StringUtil.isEmpty(date)) {
                resultFileDir = rootDir + fileName.toString() + "_" + date;
            } else {
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                date = df.format(new Date());
                resultFileDir = rootDir + fileName.toString() + "_" + StringUtil.get8RandomValiteCode(8);
            }
            File resultFile = new File(resultFileDir);
            if (!resultFile.exists()) {
                resultFile.mkdir();
            }
            //File.separator，斜杠线
            String carDir = resultFileDir + File.separator + "picture";
            File carFile = new File(carDir);
            String suffix;
            if (!carFile.exists()) {
                carFile.mkdir();
                if (StringUtil.isNotEmpty(resultList)) {
                    JSONObject object = null;
                    int i = 0;
                    for (int end = resultList.size(); i < end; i++) {
                        object = JSONObject.fromObject(resultList.get(i));
                        List<Map<String, Object>> mapList = new ArrayList();
                        String fileOriginalName = null;
                        String[] picturUrlArray = object.get("imagePath").toString().split("/");
                        if ((null != picturUrlArray) && (picturUrlArray.length > 0)) {
                            fileOriginalName = picturUrlArray[(picturUrlArray.length - 1)];
                        }
                        suffix = ".jpg";
                        if ((!StringUtils.isEmpty(fileOriginalName)) && (fileOriginalName.contains("."))) {
                            suffix = fileOriginalName.substring(fileOriginalName.lastIndexOf('.'), fileOriginalName.length());
                        }
                        //String newPicName = "estateRegister_" + object.get("id") + suffix;
                        String newPicName = object.getString("cardId");
                        String carPicturePath = carDir + File.separator + newPicName;
                        FileUtil.dowloadFileFromUrl(object.get("imagePath").toString(), carPicturePath);
                    }
                }
            }
            OutputStream out = null;
            try {
                String excelFilenPath = resultFileDir + File.separator + "数据表.xls";
                File f = new File(excelFilenPath);
                if (!f.exists()) {
                    try {
                        f.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                out = new FileOutputStream(excelFilenPath);
                String[] carHeaders = {"租用人姓名", "性别", "生日","地址", "身份证", "租用人联系方式", "是否业主",
                        "业主姓名", "业主身份证号","业主联系方式", "实际居住人工作（单位、职务）", "小区编号",
                        "小区省", "小区市", "小区县",
                        "小区名称", "小区详址","单元号", "房间号", "户型", "房屋用途", "居住类型",
                        "备注", "状态", "创建人",
                        "创建时间", "照片地址"};
                HSSFWorkbook workbook = new HSSFWorkbook();
                ExcelHandleUtils.exportRecogExcel(workbook, carHeaders, resultList, out);
                workbook.write(out);
                try {
                    if (null != out) {
                        out.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String zipFileDirName = "";
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            } finally {
                try {
                    if (null != out) {
                        out.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String zipFileDirName = rootDir + fileName.toString() + "_" + date + ".zip";
            File rarFile = new File(zipFileDirName);
            if (rarFile.exists()) {
                rarFile.delete();
            }
            FileZipCompressorUtil fileZipUtil = new FileZipCompressorUtil(zipFileDirName);
            fileZipUtil.compressExe(resultFileDir);
            try {
                File f = new File(resultFileDir);
                FileUtil.deleteFileOrDirector(f);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return zipFileDirName;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public HttpServletResponse downloadZip(File file, HttpServletResponse response) {
        OutputStream toClient = null;
        File f;
        try {
            InputStream fis = new java.io.BufferedInputStream(new FileInputStream(file.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();

            response.reset();
            toClient = new java.io.BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream; charset=utf-8");

            response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(file.getName(), "UTF-8"));
            toClient.write(buffer);
            return response;
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                toClient.flush();
                toClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
           /* try {
                f = new File(file.getPath());
                f.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            return response;
        }
    }
}
