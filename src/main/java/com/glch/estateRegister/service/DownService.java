package com.glch.estateRegister.service;

import com.glch.estateRegister.entity.EstateRegister;
import com.glch.estateRegister.entity.vo.RegisVo;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author zhongzhilong
 * @date 2020/7/12 0012
 */
public interface DownService {
    String downloadTotalComprehensive(List<Map<String,Object>> resultList, String date, RegisVo regisVo);

    HttpServletResponse downloadZip(File file, HttpServletResponse response);
}
