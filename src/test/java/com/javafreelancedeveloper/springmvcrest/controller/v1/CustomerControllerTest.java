package com.javafreelancedeveloper.springmvcrest.controller.v1;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.javafreelancedeveloper.springmvcrest.api.v1.model.CustomerDTO;
import com.javafreelancedeveloper.springmvcrest.controller.RestResponseEntityExceptionHandler;
import com.javafreelancedeveloper.springmvcrest.exception.ResourceNotFoundException;
import com.javafreelancedeveloper.springmvcrest.service.CustomerService;

public class CustomerControllerTest extends AbstractRestControllerTest {

	public static final String FIRST_NAME1 = "Joe";
	public static final String LAST_NAME1 = "Macmillan";
	public static final long ID1 = 1L;
	public static final String FIRST_NAME2 = "Cameron";
	public static final String LAST_NAME2 = "Howe";
	public static final long ID2 = 2L;
	@Mock
	private CustomerService customerService;
	@InjectMocks
	private CustomerController customerController;
	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
	}

	@Test
	public void testListCustomers() throws Exception {
		String url1 = makeCustomerUrl(1L);
		String url2 = makeCustomerUrl(2L);
		CustomerDTO customer1 = new CustomerDTO();
		customer1.setFirstName(FIRST_NAME1);
		customer1.setLastName(LAST_NAME1);
		customer1.setCustomerUrl(url1);
		customer1.setId(ID1);
		CustomerDTO customer2 = new CustomerDTO();
		customer2.setFirstName(FIRST_NAME2);
		customer2.setLastName(LAST_NAME2);
		customer2.setCustomerUrl(url2);
		customer2.setId(ID2);
		List<CustomerDTO> customers = Arrays.asList(customer1, customer2);
		when(customerService.listCustomers()).thenReturn(customers);
		mockMvc.perform(get("/api/v1/customers/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.customers", hasSize(2)));
	}

	@Test
	public void testGetCustomerById() throws Exception {
		String url = makeCustomerUrl(1L);
		CustomerDTO customer1 = new CustomerDTO();
		customer1.setFirstName(FIRST_NAME1);
		customer1.setLastName(LAST_NAME1);
		customer1.setCustomerUrl(url);
		customer1.setId(ID1);
		when(customerService.getCustomerById(ID1)).thenReturn(customer1);
		mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME1)));
	}

	@Test
	public void testCreateNewCustomer() throws Exception {
		// given
		String url = makeCustomerUrl(1L);
		CustomerDTO customer = new CustomerDTO();
		customer.setFirstName("Gordon");
		customer.setLastName("Clark");
		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setFirstName(customer.getFirstName());
		returnDTO.setLastName(customer.getLastName());
		returnDTO.setCustomerUrl(url);
		when(customerService.saveCustomer(customer)).thenReturn(returnDTO);
		// when/then
		mockMvc.perform(post("/api/v1/customers/").contentType(MediaType.APPLICATION_JSON).content(asJsonString(customer)))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.firstName", equalTo("Gordon")))
				.andExpect(jsonPath("$.customerUrl", equalTo(url)));
	}

	@Test
	public void testUpdateCustomer() throws Exception {
		// given
		String url = makeCustomerUrl(1L);
		CustomerDTO customer = new CustomerDTO();
		customer.setFirstName("Gordon");
		customer.setLastName("Clark");
		customer.setId(1L);
		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setFirstName(customer.getFirstName());
		returnDTO.setLastName(customer.getLastName());
		returnDTO.setId(customer.getId());
		returnDTO.setCustomerUrl(url);
		when(customerService.saveCustomer(any(CustomerDTO.class))).thenReturn(returnDTO);
		// when/then
		mockMvc.perform(put(url).contentType(MediaType.APPLICATION_JSON).content(asJsonString(customer))).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", equalTo("Gordon"))).andExpect(jsonPath("$.lastName", equalTo("Clark")))
				.andExpect(jsonPath("$.id", equalTo(1))).andExpect(jsonPath("$.customerUrl", equalTo(url)));
	}

	@Test
	public void testPatchCustomer() throws Exception {
		// given
		String url = makeCustomerUrl(1L);
		CustomerDTO customer = new CustomerDTO();
		customer.setFirstName("Fred");
		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setFirstName(customer.getFirstName());
		returnDTO.setLastName("Flintstone");
		returnDTO.setCustomerUrl(url);
		when(customerService.patchCustomer(any(CustomerDTO.class))).thenReturn(returnDTO);
		mockMvc.perform(patch(url).contentType(MediaType.APPLICATION_JSON).content(asJsonString(customer))).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", equalTo("Fred"))).andExpect(jsonPath("$.lastName", equalTo("Flintstone")))
				.andExpect(jsonPath("$.customerUrl", equalTo("/api/v1/customers/1")));
	}

	@Test
	public void testDeleteCustomer() throws Exception {
		String url = makeCustomerUrl(1L);
		mockMvc.perform(delete(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		verify(customerService).deleteCustomer(anyLong());
	}

	@Test
	public void testNotFoundException() throws Exception {
		when(customerService.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);
		mockMvc.perform(get(CustomerController.BASE_URL + "/222").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	public String makeCustomerUrl(Long id) {
		return CustomerController.BASE_URL + "/" + id;
	}
}
