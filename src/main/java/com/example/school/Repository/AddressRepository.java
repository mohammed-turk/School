package com.example.school.Repository;

import com.example.school.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
 Address findAddressById(Integer id);
}
