package com.tja;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.tja.config.ApplicationConfig;
import com.tja.domain.Address;
import com.tja.domain.Customer;
import com.tja.domain.EmailAddress;
import com.tja.repository.CustomerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes =ApplicationConfig.class)
@WebAppConfiguration    //如果项目里引入了spring mvc， 必须加上这个注解，表示测试环境使用的applicationContext是web类型的
public class CustomerRepositoryTests {

	private static  final String TEST_EMAIL = "xiao0-test@163.com";
	
	@Autowired
	CustomerRepository customerRepository;

	
	@Test
	public void saveAndFindCustomerByEmailAddress() {
		Customer customer = new Customer();
		customer.setFirstName("xia1-test");
		customer.setLastName("tang");
		
		EmailAddress emailAddress = new EmailAddress();
		emailAddress.setEmail(TEST_EMAIL);
		customer.setEmailAddress(emailAddress);
		
		Address address = new Address();
		address.setCite("hunan changsha");
		address.setStreet("wbdd");
		address.setCustomer(customer);
		
		Set<Address> addresses = new HashSet<Address>();
		addresses.add(address);
		
		customer.setAddresses(addresses);
		
		Customer result = customerRepository.save(customer);
		Assert.assertNotNull(result.getId());
				
		Customer c = customerRepository.findByEmailAddress(emailAddress);
		Assert.assertEquals(c.getFirstName(), "xia1-test");
		
		Customer c2 = customerRepository.findByFullName("xia1-test", "tang");
		Assert.assertNotNull(c2);
		
		//clear
		customerRepository.delete(c);
	}
}
