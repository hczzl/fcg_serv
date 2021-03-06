package com.glch.estateRegister.mapper;

import com.glch.estateRegister.entity.EstateRegister;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.glch.estateRegister.entity.vo.RegisVo;
import org.apache.ibatis.annotations.Mapper;

import javax.swing.text.html.ObjectView;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 小区房屋登记信息表 Mapper 接口
 * </p>
 *
 * @author liangwen
 * @since 2020-07-08
 */
@Mapper
public interface EstateRegisterMapper extends BaseMapper<EstateRegister> {

    List<EstateRegister> queryList(Map<String, Object> queryMap);

    List<Map<String, Object>> selectList(RegisVo regisVo);
}
