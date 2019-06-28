package org.mengyun.tcctransaction.sample.order.domain.repository;

import org.apache.log4j.Logger;
import org.mengyun.tcctransaction.sample.order.domain.entity.Product;
import org.mengyun.tcctransaction.sample.order.infrastructure.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by twinkle.zhou on 16/11/10.
 */
@Repository
public class ProductRepository {

    private static final Logger logger = Logger.getLogger(ProductRepository.class);

    @Autowired
    ProductDao productDao;

    public Product findById(long productId){
        return productDao.findById(productId);
    }

    public List<Product> findByShopId(long shopId){
        try {
            return productDao.findByShopId(shopId);
        }catch (Exception e) {
            logger.error("查询数据失败", e);
            throw new RuntimeException(e);
        }

    }
}
