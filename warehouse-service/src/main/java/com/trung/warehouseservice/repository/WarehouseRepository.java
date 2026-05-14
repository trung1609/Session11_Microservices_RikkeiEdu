package com.trung.warehouseservice.repository;

import com.trung.warehouseservice.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Warehouse w SET w.stock = w.stock - :quantity " +
            "WHERE w.medicineId = :medicineId AND w.stock >= :quantity")
    int decreaseStock(@Param("medicineId") String medicineId, @Param("quantity") Integer quantity);
}
