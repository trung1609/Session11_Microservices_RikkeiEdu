package com.trung.pharmacyservice.repository;

import com.trung.pharmacyservice.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MedicineRepository extends JpaRepository <Medicine, Long>{

    @Transactional
    @Modifying
    @Query("UPDATE Medicine m SET m.quantity = m.quantity - :quantity WHERE m.id = :id")
    Medicine updateQuantity(Long id, Integer quantity);
}
