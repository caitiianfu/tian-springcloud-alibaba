package com.unicom.search.dao;

import com.unicom.search.entity.EsProduct;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 搜索系统中的商品管理自定义Dao
 * Created by macro on 2018/6/19.
 */
@Mapper
public interface EsProductDao {
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}
