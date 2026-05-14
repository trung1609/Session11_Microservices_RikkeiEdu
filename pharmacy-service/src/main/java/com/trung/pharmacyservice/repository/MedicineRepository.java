package com.trung.pharmacyservice.repository;

import com.trung.pharmacyservice.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository <Medicine, Long>{

}
