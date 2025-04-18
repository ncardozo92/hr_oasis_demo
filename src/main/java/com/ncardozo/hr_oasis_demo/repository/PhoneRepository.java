package com.ncardozo.hr_oasis_demo.repository;

import com.ncardozo.hr_oasis_demo.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
