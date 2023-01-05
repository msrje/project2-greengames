package com.green.nowon.domain.entity.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressEntityRepsoitory extends JpaRepository<AddressEntity, Long>{

}
