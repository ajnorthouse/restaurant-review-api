package com.cognixia.jump.restaurant.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.restaurant.model.User;
import com.cognixia.jump.restaurant.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc()
class UserControllerTest {

	private final String URI_FRONT = "http://localhost:8080/api/user";
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService service;
	
	@InjectMocks
	private UserController controller;
	
	@Test
	@WithMockUser(authorities = "USER")
	void testGetAllUsers() throws Exception {
		
		String uri = URI_FRONT;
		
		User[] userArr = {
				new User(1L, "user1", "password1", "User Userson", 0),
				new User(2L, "user2", "password2", "User Son of User", 0),
				new User(3L, "user3", "password3", "User Sr", 0),
				new User(4L, "user4", "password4", "User Jr", 0),
				new User(5L, "user5", "password5", "User III", 0),
				new User(6L, "user6", "password6", "User IV", 0),
				new User(7L, "user7", "password7", "User V The User Strikes Back", 0)
		};
		
		List<User> users = Arrays.asList(userArr);
		
		when(service.getAllUsers()).thenReturn(users);
		
		mockMvc.perform(get(uri))
				       .andDo(print())
                       .andExpect(status().isOk())
                       .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                       .andExpect(jsonPath("$.length()").value(users.size()))
                       .andExpect(jsonPath("$[0].userId").value(users.get(0).getUserId()))
                       .andExpect(jsonPath("$[0].username").value(users.get(0).getUsername()))
					   .andExpect(jsonPath("$[0].password").value(users.get(0).getPassword()))
					   .andExpect(jsonPath("$[0].name").value(users.get(0).getName()))
					   .andExpect(jsonPath("$[0].reviewCount").value(users.get(0).getReviewCount()))
        			   .andExpect(jsonPath("$[1].userId").value(users.get(1).getUserId()))
        			   .andExpect(jsonPath("$[1].username").value(users.get(1).getUsername()))
        			   .andExpect(jsonPath("$[1].password").value(users.get(1).getPassword()))
        			   .andExpect(jsonPath("$[1].name").value(users.get(1).getName()))
        			   .andExpect(jsonPath("$[1].reviewCount").value(users.get(1).getReviewCount()))
        			   .andExpect(jsonPath("$[2].userId").value(users.get(2).getUserId()))
        			   .andExpect(jsonPath("$[2].username").value(users.get(2).getUsername()))
        			   .andExpect(jsonPath("$[2].password").value(users.get(2).getPassword()))
        			   .andExpect(jsonPath("$[2].name").value(users.get(2).getName()))
        			   .andExpect(jsonPath("$[2].reviewCount").value(users.get(2).getReviewCount()))
        			   .andExpect(jsonPath("$[3].userId").value(users.get(3).getUserId()))
        			   .andExpect(jsonPath("$[3].username").value(users.get(3).getUsername()))
        			   .andExpect(jsonPath("$[3].password").value(users.get(3).getPassword()))
        			   .andExpect(jsonPath("$[3].name").value(users.get(3).getName()))
        			   .andExpect(jsonPath("$[3].reviewCount").value(users.get(3).getReviewCount()))
        			   .andExpect(jsonPath("$[4].userId").value(users.get(4).getUserId()))
        			   .andExpect(jsonPath("$[4].username").value(users.get(4).getUsername()))
        			   .andExpect(jsonPath("$[4].password").value(users.get(4).getPassword()))
        			   .andExpect(jsonPath("$[4].name").value(users.get(4).getName()))
        			   .andExpect(jsonPath("$[4].reviewCount").value(users.get(4).getReviewCount()))
        			   .andExpect(jsonPath("$[5].userId").value(users.get(5).getUserId()))
        			   .andExpect(jsonPath("$[5].username").value(users.get(5).getUsername()))
        			   .andExpect(jsonPath("$[5].password").value(users.get(5).getPassword()))
        			   .andExpect(jsonPath("$[5].name").value(users.get(5).getName()))
        			   .andExpect(jsonPath("$[5].reviewCount").value(users.get(6).getReviewCount()))
        			   .andExpect(jsonPath("$[6].userId").value(users.get(6).getUserId()))
        			   .andExpect(jsonPath("$[6].username").value(users.get(6).getUsername()))
        			   .andExpect(jsonPath("$[6].password").value(users.get(6).getPassword()))
        			   .andExpect(jsonPath("$[6].name").value(users.get(6).getName()))
        			   .andExpect(jsonPath("$[6].reviewCount").value(users.get(6).getReviewCount()));
		
		verify(service, times(1)).getAllUsers();
		verifyNoMoreInteractions(service);
	}

	@Test
	@WithMockUser(authorities = "USER")
	void testGetUserById() throws Exception {
		
		long id = 1L;
		String uri = URI_FRONT + "/id/" + id;
		
		User user = new User(id, "user", "password", "User Userson", 0);
		
		when(service.getUserById(id)).thenReturn(user);
		
		mockMvc.perform(get(uri))
				       .andDo(print())
                       .andExpect(status().isOk())
                       .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                       .andExpect(jsonPath("$.userId").value(user.getUserId()))
                       .andExpect(jsonPath("$.username").value(user.getUsername()))
					   .andExpect(jsonPath("$.password").value(user.getPassword()))
					   .andExpect(jsonPath("$.name").value(user.getName()))
					   .andExpect(jsonPath("$.reviewCount").value(user.getReviewCount()));
		
		verify(service, times(1)).getUserById(id);
		verifyNoMoreInteractions(service);
	}

	@Test
	@WithMockUser(authorities = "USER")
	void testGetUserByUserName() throws Exception {
		
		String username = "user";
		String uri = URI_FRONT + "/username/" + username;
		
		User user = new User(1L, username, "password", "User Userson", 0);
		
		when(service.getUserByUserName(username)).thenReturn(user);
		
		mockMvc.perform(get(uri))
				       .andDo(print())
                       .andExpect(status().isOk())
                       .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                       .andExpect(jsonPath("$.userId").value(user.getUserId()))
                       .andExpect(jsonPath("$.username").value(user.getUsername()))
					   .andExpect(jsonPath("$.password").value(user.getPassword()))
					   .andExpect(jsonPath("$.name").value(user.getName()))
					   .andExpect(jsonPath("$.reviewCount").value(user.getReviewCount()));
		
		verify(service, times(1)).getUserByUserName(username);
		verifyNoMoreInteractions(service);
	}

	@Test
	void testCreateUser() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testUpdateUser() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testDeleteUser() {
		fail("Not yet implemented"); // TODO
	}

}
