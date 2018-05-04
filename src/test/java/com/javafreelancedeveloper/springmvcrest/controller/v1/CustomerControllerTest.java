package com.javafreelancedeveloper.springmvcrest.controller.v1;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import com.javafreelancedeveloper.springmvcrest.service.CustomerService;

public class CustomerControllerTest extends AbstractRestControllerTest {

	public static final String FIRST_NAME1 = "Joe";
	public static final String LAST_NAME1 = "Macmillan";
	public static final String CUSTOMER_URL1 = "joemac@hcf.com";
	public static final long ID1 = 1L;
	public static final String FIRST_NAME2 = "Cameron";
	public static final String LAST_NAME2 = "Howe";
	public static final String CUSTOMER_URL2 = "camh@hcf.com";
	public static final long ID2 = 2L;
	@Mock
	private CustomerService customerService;
	@InjectMocks
	private CustomerController customerController;
	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	public void testListCustomers() throws Exception {
		CustomerDTO customer1 = new CustomerDTO();
		customer1.setFirstName(FIRST_NAME1);
		customer1.setLastName(LAST_NAME1);
		customer1.setCustomerUrl(CUSTOMER_URL1);
		customer1.setId(ID1);
		CustomerDTO customer2 = new CustomerDTO();
		customer2.setFirstName(FIRST_NAME2);
		customer2.setLastName(LAST_NAME2);
		customer2.setCustomerUrl(CUSTOMER_URL2);
		customer2.setId(ID2);
		List<CustomerDTO> customers = Arrays.asList(customer1, customer2);
		when(customerService.listCustomers()).thenReturn(customers);
		mockMvc.perform(get("/api/v1/customers/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.customers", hasSize(2)));
	}

	@Test
	public void testGetCustomerById() throws Exception {
		CustomerDTO customer1 = new CustomerDTO();
		customer1.setFirstName(FIRST_NAME1);
		customer1.setLastName(LAST_NAME1);
		customer1.setCustomerUrl(CUSTOMER_URL1);
		customer1.setId(ID1);
		when(customerService.getCustomerById(ID1)).thenReturn(customer1);
		mockMvc.perform(get("/api/v1/customers/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME1)));
	}

	@Test
	public void testCreateNewCustomer() throws Exception {
		// given
		CustomerDTO customer = new CustomerDTO();
		customer.setFirstName("Gordon");
		customer.setLastName("Clark");
		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setFirstName(customer.getFirstName());
		returnDTO.setLastName(customer.getLastName());
		returnDTO.setCustomerUrl("/api/v1/customers/1");
		when(customerService.saveCustomer(customer)).thenReturn(returnDTO);
		// when/then
		mockMvc.perform(post("/api/v1/customers/").contentType(MediaType.APPLICATION_JSON).content(asJsonString(customer)))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.firstName", equalTo("Gordon")))
				.andExpect(jsonPath("$.customerUrl", equalTo("/api/v1/customers/1")));
	}
	
	@Test
    public void testUpdateCustomer() throws Exception {
        //given
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName("Gordon");
		customer.setLastName("Clark");
		customer.setId(1L);

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstName(customer.getFirstName());
        returnDTO.setLastName(customer.getLastName());
        returnDTO.setId(customer.getId());
        returnDTO.setCustomerUrl("/api/v1/customers/1");

        when(customerService.saveCustomer(any(CustomerDTO.class))).thenReturn(returnDTO);

        //when/then
        mockMvc.perform(put("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Gordon")))
                .andExpect(jsonPath("$.lastName", equalTo("Clark")))
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.customerUrl", equalTo("/api/v1/customers/1")));
    }
}
