package com.glch.estateRegister.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glch.base.caches.Context;
import com.glch.base.caches.ServContexts;
import com.glch.base.config.ImageConfig;
import com.glch.base.util.BeanUtils;
import com.glch.base.util.PageUtils;
import com.glch.base.util.StringUtil;
import com.glch.base.util.base64.Base64Utils;
import com.glch.estate.entity.Estate;
import com.glch.estate.mapper.EstateMapper;
import com.glch.estateRegister.entity.EstateRegister;
import com.glch.estateRegister.entity.vo.EstateRegisterVo;
import com.glch.estateRegister.entity.vo.PersonVo;
import com.glch.estateRegister.mapper.EstateRegisterMapper;
import com.glch.estateRegister.service.IEstateRegisterService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.glch.system.user.entity.User;
import com.glch.system.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.SystemException;
import java.util.*;

/**
 * <p>
 * 小区房屋登记信息表 服务实现类
 * </p>
 *
 * @author liangwen
 * @since 2020-07-08
 */
@Service
public class EstateRegisterServiceImpl extends ServiceImpl<EstateRegisterMapper, EstateRegister> implements IEstateRegisterService {

    @Autowired
    EstateMapper estateMapper;
    @Autowired
    UserMapper userMapper;

    @Autowired
    ImageConfig imageConfig;

    @Override
    public void saveEstateRegister(EstateRegisterVo estateRegisterVo) throws Exception {
        try {
            Estate estate = estateMapper.selectById(estateRegisterVo.getEstateId());

            for (PersonVo personVo : estateRegisterVo.getPersonVoList()) {
                EstateRegister estateRegister = new EstateRegister();

                //小区信息
                estateRegister.setEstateId(estate.getId());
                estateRegister.setXqmc(estate.getEstateName());
                estateRegister.setXqshi(estate.getCityproper());
                estateRegister.setBuilding(estateRegisterVo.getBuilding());
                estateRegister.setUnitNumber(estateRegisterVo.getUnitNumber());
                estateRegister.setRoomNumber(estateRegisterVo.getRoomNumber());

                //保存图片
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                if (personVo.getBase64() != null || personVo.getBase64() != "") {
                    String savePath = imageConfig.getSavePath() + uuid + ".jpg";
                    boolean flag = Base64Utils.GenerateImage(personVo.getBase64(), savePath);
                    if (!flag) {
                        throw new SystemException("保存图片失败！");
                    }
                    estateRegister.setImagePath(savePath);
                }

                //人员信息
                estateRegister.setName(personVo.getName());
                estateRegister.setCardId(personVo.getCardId());
                estateRegister.setTel(personVo.getTel());
                estateRegister.setLiveType(personVo.getLiveType());
                estateRegister.setOwner(personVo.getOwner());
                estateRegister.setGender(personVo.getGender());
                estateRegister.setBirthday(personVo.getBirthday());
                estateRegister.setAddrress(personVo.getAddrress());

                //业主信息
                estateRegister.setOwnerName(estateRegisterVo.getOwnerName());
                estateRegister.setOwnerCardid(estateRegisterVo.getOwnerCardid());
                estateRegister.setOwnerTel(estateRegisterVo.getOwnerTel());

                int flag = this.baseMapper.insert(estateRegister);
                if (flag <= 0) {
                    throw new SystemException("录入失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("录入失败");
        }
    }

    @Override
    public PageInfo<EstateRegister> listEstateRegister(JSONObject params) throws Exception {
        int pageIndex = params.getInteger("pageIndex");
        int pageSize = params.getInteger("pageSize") <= 0 ? 10 : params.getInteger("pageSize");
        PageHelper.startPage(pageIndex, pageSize);

        Map<String, Object> queryMap = new HashMap<>();
        if (!StringUtil.isEmpty(params.getString("keyWord"))) {
            queryMap.put("keyWord", params.getString("keyWord"));
        }
        if (!StringUtil.isEmpty(params.getString("estateId"))) {
            queryMap.put("estateId", params.getString("estateId"));
        }
        if (!StringUtil.isEmpty(params.getString("beginTime"))) {
            queryMap.put("beginTime", params.getString("beginTime"));
        }
        if (!StringUtil.isEmpty(params.getString("endTime"))) {
            queryMap.put("endTime", params.getString("endTime"));
        }
        List<EstateRegister> queryList = this.baseMapper.queryList(queryMap);
        List<EstateRegister> resultList = new ArrayList<>();
        for (EstateRegister estateRegister : queryList) {
            estateRegister.setBase64(Base64Utils.ImageToBase64ByLocal(estateRegister.getImagePath()));
            User user = userMapper.selectByUserId(estateRegister.getCreator());
            estateRegister.setCreatorName(user.getName());
            resultList.add(estateRegister);
        }
        PageInfo<EstateRegister> pageInfo = new PageInfo<EstateRegister>(resultList);
        return pageInfo;
    }

    @Override
    public void updateEstateRegister(EstateRegisterVo estateRegisterVo) throws Exception {
        try {
            if (StringUtil.isEmpty(estateRegisterVo.getId())) {
                throw new SystemException("修改对象不存在");
            }

            Estate estate = estateMapper.selectById(estateRegisterVo.getEstateId());

            for (PersonVo personVo : estateRegisterVo.getPersonVoList()) {
                EstateRegister estateRegister = new EstateRegister();
                estateRegister.setId(estateRegisterVo.getId());

                //小区信息
                estateRegister.setEstateId(estate.getId());
                estateRegister.setXqmc(estate.getEstateName());
                estateRegister.setXqshi(estate.getCityproper());
                estateRegister.setBuilding(estateRegisterVo.getBuilding());
                estateRegister.setUnitNumber(estateRegisterVo.getUnitNumber());
                estateRegister.setRoomNumber(estateRegisterVo.getRoomNumber());

                //保存图片
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                if (personVo.getBase64() != null || personVo.getBase64() != "") {
                    String savePath = imageConfig.getSavePath() + uuid + ".jpg";
                    boolean flag = Base64Utils.GenerateImage(personVo.getBase64(), savePath);
                    if (!flag) {
                        throw new SystemException("保存图片失败！");
                    }
                    estateRegister.setImagePath(savePath);
                }

                //人员信息
                estateRegister.setName(personVo.getName());
                estateRegister.setCardId(personVo.getCardId());
                estateRegister.setTel(personVo.getTel());
                estateRegister.setLiveType(personVo.getLiveType());
                estateRegister.setOwner(personVo.getOwner());
                estateRegister.setGender(personVo.getGender());
                estateRegister.setBirthday(personVo.getBirthday());
                estateRegister.setAddrress(personVo.getAddrress());

                //业主信息
                estateRegister.setOwnerName(estateRegisterVo.getOwnerName());
                estateRegister.setOwnerCardid(estateRegisterVo.getOwnerCardid());
                estateRegister.setOwnerTel(estateRegisterVo.getOwnerTel());

                Context context = ServContexts.getCurContext();
                if (context != null) {
                    User user = ServContexts.getCurUser();
                    estateRegister.setLastUpdateUser(user.getId());
                }
                estateRegister.setLastUpdateTime(new Date());

                this.baseMapper.updateById(estateRegister);
            }


        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("修改失败！");
        }
    }
}
