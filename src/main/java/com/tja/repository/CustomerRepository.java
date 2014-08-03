package com.tja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tja.domain.Customer;
import com.tja.domain.EmailAddress;

/**
 * Repository默认是不开启事务的， 所以接口中的方法要添加事务标识；
 * 但是：  Repository的子接口CrudRepository和PagingAndSortingRepository是开启事务的.
 * @author Thinkpad
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	@Query("SELECT c FROM Customer c where c.firstName=?1 and c.lastName=?2")
	Customer findByFullName(String firstName,String lastName);
	
	Customer findByEmailAddress(EmailAddress emailAddress);
}
