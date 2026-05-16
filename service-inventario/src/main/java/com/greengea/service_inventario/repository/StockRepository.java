package com.greengea.service_inventario.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.greengea.service_inventario.model.Stock;


public interface StockRepository extends JpaRepository<Stock, Long>{

}
