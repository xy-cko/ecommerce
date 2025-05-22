package com.company.ecommerce.repository;

import com.company.ecommerce.entity.Product;
import com.company.ecommerce.entity.User;
import io.jmix.core.repository.JmixDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JmixDataRepository<Product, UUID> {
    List<Product> findByVendor(User vendor);
}