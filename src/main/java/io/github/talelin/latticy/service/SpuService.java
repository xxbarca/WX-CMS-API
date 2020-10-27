package io.github.talelin.latticy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.latticy.dto.SpuDTO;
import io.github.talelin.latticy.model.*;
import io.github.talelin.latticy.mapper.SpuMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2020-05-28
 */
@Service
public class SpuService extends ServiceImpl<SpuMapper, SpuDO> {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SpuImgService spuImgService;

    @Autowired
    private SpuDetailImgService spuDetailImgService;

    @Autowired
    private SpuKeyService spuKeyService;

    public SpuDetailDO getDetail(Long id) {
        return this.getBaseMapper().getDetail(id);
    }

    @Transactional
    public void create(SpuDTO dto) {

        SpuDO spuDO = new SpuDO();
        BeanUtils.copyProperties(dto, spuDO);
        CategoryDO categoryDO = categoryService.getCategoryById(dto.getCategoryId().intValue());
        spuDO.setRootCategoryId(categoryDO.getParentId());
        this.save(spuDO);

        List<String> spuImgList = new ArrayList<>();

        // 如果为空则把主图当作SpuImgList
        if (dto.getSpuImgList() == null) {
            spuImgList.add(dto.getImg());
        } else {
            spuImgList = dto.getSpuImgList();
        }
        this.insertSpuImgList(spuImgList, spuDO.getId());
        if (dto.getDetailImgList() != null) {
            this.insertSpuDetailImgList(dto.getDetailImgList(), spuDO.getId());
        }
        if (dto.getSpecKeyIdList() != null) {
            this.insertSpuKeyList(dto.getSpecKeyIdList(), spuDO.getId());
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void update(SpuDTO dto, Integer id) {
        SpuDO spuDO = this.getById(id);
        if (spuDO == null) {
            throw new NotFoundException(70000);
        }
        BeanUtils.copyProperties(dto, spuDO);
        CategoryDO category = categoryService.getCategoryById(dto.getCategoryId());
        if (category.getParentId() != null) {
            spuDO.setRootCategoryId(category.getParentId());
        }
        this.updateById(spuDO);

        List<String> spuImgList = new ArrayList<>();
        if (dto.getSpuImgList() == null) {
            spuImgList.add(dto.getImg());
        } else {
            spuImgList = dto.getSpuImgList();
        }
        spuImgService.getBaseMapper().hardDeleteImgsBySpuId(spuDO.getId());
        spuDetailImgService.getBaseMapper().hardDeleteImgsBySpuId(spuDO.getId());
        this.insertSpuImgList(spuImgList, spuDO.getId());
        if (dto.getSpuDetailImgList() != null) {
            this.insertSpuDetailImgList(dto.getSpuDetailImgList(), spuDO.getId());
        }
        this.updateSpuKey(spuDO.getId(), dto.getSpecKeyIdList());
    }

    /**
     * 更新spu_key表
     * @param spuId spu id
     * @param newSpecKeyIdList 前端传递过来的 spu_key id列表
     */
    private void updateSpuKey(Long spuId, List<Integer> newSpecKeyIdList) {
        QueryWrapper<SpuKeyDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SpuKeyDO::getSpuId, spuId);
        List<SpuKeyDO> exists = spuKeyService.getBaseMapper().selectList(wrapper);
        List<Integer> existsIds = new ArrayList<>();
        List<SpuKeyDO> newSpuKeyList = new ArrayList<>();
        for (SpuKeyDO exist: exists) {
            existsIds.add(exist.getId().intValue());
        }
        for (Integer specKeyId: newSpecKeyIdList) {
            SpuKeyDO spuKeyDO = new SpuKeyDO();
            spuKeyDO.setSpecKeyId(specKeyId);
            spuKeyDO.setSpuId(spuId.intValue());
            newSpuKeyList.add(spuKeyDO);
        }
        spuKeyService.removeByIds(existsIds);
        spuKeyService.saveBatch(newSpuKeyList);
    }

    private void insertSpuImgList(List<String> spuImgList, Long spuId) {
        List<SpuImgDO> spuImgDOList = spuImgList.stream().map(s -> {
            SpuImgDO spuImgDO = new SpuImgDO();
            spuImgDO.setImg(s);
            spuImgDO.setSpuId(spuId);
            return spuImgDO;
        }).collect(Collectors.toList());

        spuImgService.saveBatch(spuImgDOList);
    }

    private void insertSpuDetailImgList(List<String> spuDetailImgList, Long spuId) {

        List<SpuDetailImgDO> spuDetailImgDOList = new ArrayList<>();
        for (Integer i = 0; i < spuDetailImgList.size(); i++) {
            SpuDetailImgDO spuDetailImgDO = new SpuDetailImgDO();
            spuDetailImgDO.setImg(spuDetailImgList.get(i))
                    .setSpuId(spuId)
                    .setIndex(i.longValue());
            spuDetailImgDOList.add(spuDetailImgDO);
        }

        spuDetailImgService.saveBatch(spuDetailImgDOList);

    }

    private void insertSpuKeyList(List<Integer> spuKeyIdList, Long spuId) {
        List<SpuKeyDO> spuKeyDOList = spuKeyIdList.stream().map(s -> {
            SpuKeyDO spuKeyDO = new SpuKeyDO();
            spuKeyDO.setSpuId(spuId.intValue())
                    .setSpecKeyId(s);
            return spuKeyDO;
        }).collect(Collectors.toList());
        spuKeyService.saveBatch(spuKeyDOList);
    }

    public void delete(Integer id) {
        SpuDO exist = this.getById(id);
        if (exist == null) {
            throw new NotFoundException(70000);
        }
        this.getBaseMapper().deleteById(id);
    }

}
