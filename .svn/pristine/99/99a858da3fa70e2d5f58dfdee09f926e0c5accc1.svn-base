package com.glch.estate.service.impl;

import com.glch.base.caches.Context;
import com.glch.base.caches.ServContexts;
import com.glch.base.util.BeanUtils;
import com.glch.estate.entity.Estate;
import com.glch.estate.entity.vo.EstateVo;
import com.glch.estate.mapper.EstateMapper;
import com.glch.estate.service.IEstateService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.glch.system.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  小区表服务实现类
 * </p>
 *
 * @author liangwen
 * @since 2020-07-08
 */
@Service
public class EstateServiceImpl extends ServiceImpl<EstateMapper, Estate> implements IEstateService {

    @Override
    public List<Estate> selectByName(String xqmc) {
        return this.baseMapper.selectByName(xqmc);
    }

    @Override    public int addEstate(EstateVo estateVo) throws  Exception {
        Estate estate = new Estate();
        BeanUtils.copyBeanProp(estateVo, estate);
        return this.baseMapper.insert(estate);
    }
}
