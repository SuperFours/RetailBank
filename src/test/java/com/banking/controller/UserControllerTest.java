package com.banking.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.WebRequest;

import com.banking.constant.AppConstant;
import com.banking.dto.RegisterRequestDto;
import com.banking.dto.RegisterResponseDto;
import com.banking.exception.CustomExceptionHandler;
import com.banking.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

	@InjectMocks
	UserController userController;

	@Mock
	UserService userService;

	RegisterRequestDto requestDto = new RegisterRequestDto();
	RegisterResponseDto responseDto = new RegisterResponseDto();
	private MockMvc mockMvc;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

		MockitoAnnotations.initMocks(this);

		requestDto.setPanNumber("PG672H6726");
		requestDto.setAddress1("Cbe");
		requestDto.setAddress2("Gandhipuram");
		requestDto.setDob("1992-04-23");
		requestDto.setFirstName("Moorthy");
		requestDto.setLastName("Govindasamy");
		requestDto.setPhone("8675958381");
		requestDto.setPinCode(641666);
	}

	@Test
	public void testRegisterUser() {
		responseDto.setStatus(AppConstant.SUCCESS);
		responseDto.setMessage(AppConstant.REGISTER_SUCCESS_MESSAGE);

		when(userService.registerUser(requestDto)).thenReturn(responseDto);

		ResponseEntity<RegisterResponseDto> response = userController.registerUser(requestDto);
		assertEquals(AppConstant.SUCCESS, response.getBody().getStatus());
		assertEquals(HttpStatus.OK.value(), response.getBody().getStatusCode());
		assertEquals(AppConstant.REGISTER_SUCCESS_MESSAGE, response.getBody().getMessage());
	}

	@Test
	public void testRegisterUserForFailure() {
		responseDto.setStatus(AppConstant.FAILURE);
		when(userService.registerUser(requestDto)).thenReturn(responseDto);

		ResponseEntity<RegisterResponseDto> response = userController.registerUser(requestDto);
		assertEquals(AppConstant.FAILURE, response.getBody().getStatus());
	}

	@Test
	public void testInvalidData() throws Exception {

		WebRequest webrequest = null;
		requestDto.setPhone("7883483GG");

		// MvcResult for mockmvc performed
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/users").content(asJsonString(requestDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();
		assertThat(result).isNotNull();

		new CustomExceptionHandler().handleException(result.getResolvedException(), webrequest);
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static String asJsonString(final Object obj) throws Exception {
		return new ObjectMapper().writeValueAsString(obj);
	}
}
