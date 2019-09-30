package com.redhat.demo.aml.repository;

import com.redhat.demo.aml.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByCustId(Long custId);

    @Query("select distinct custId from TransactionEntity")
    List<Long> getDistinctCustId();
}
