package com.lexrizen.UserRegistration;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.lexrizen.UserRegistration.model.Message;
import com.lexrizen.UserRegistration.model.UserDetail;
import com.lexrizen.UserRegistration.service.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationApplicationTests {


	@Spy
	@InjectMocks
	UserService userservice;
	
	@Test
	public void verifygetMemberType() {
		when(userservice.getMemberType(80000.00)).thenReturn("Platinum");
		
		
	}

	@Test
	public void verifyGenerateId(){

		when(userservice.generateId("0102452157")).thenReturn("");
		
	}

	@Test
	public void verifyDataTest(){
		UserDetail user = new UserDetail();
		user.setFirstName("John");
		user.setLastName("Smith");
		user.setPhone("0123456789");
		
		when(userservice.validateUser(user)).thenReturn(new Message());
	}


}

