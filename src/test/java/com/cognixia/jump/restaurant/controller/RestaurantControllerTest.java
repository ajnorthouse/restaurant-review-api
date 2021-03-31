package com.cognixia.jump.restaurant.controller;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.cognixia.jump.restaurant.exception.ResourceNotFoundException;
import com.cognixia.jump.restaurant.model.Restaurant;
import com.cognixia.jump.restaurant.service.RestaurantService;
import com.cognixia.jump.restaurant.controller.RestaurantController;
import com.fasterxml.jackson.databind.ObjectMapper;
@ExtendWith(SpringExtension.class)
@WebMvcTest(RestaurantController.class)
//@AutoConfigureMockMvc
public class RestaurantControllerTest {
private final String STARTING_URI = "http://localhost:8080/api";
	// autowire the MVC mock container to hold our simulation
	@Autowired
	private MockMvc mockMvc;
	// this service exists in different context than runtime context
	@MockBean
	private RestaurantService service;
	// put the mock pieces together, within the class we are testing
	@InjectMocks
	private RestaurantController controller;
	@Test
	void testGetAllCars() throws Exception {
		String uri = STARTING_URI + "/car";
		Restaurant[] restaurants = {
				new Restaurant(1L, "PfChang", "101 PfChang drive, Dallas,TX","chinese",4), 
				new Restaurant(2L, "OliveGarden", "200 OliveGarden dr, Dallas, TX", "italian",4)};
		List<Restaurant> allres = Arrays.asList(restaurants);
		when(service.getAllRestaurants()).thenReturn(allres);
		mockMvc.perform(get(uri))
		.andDo(print())
		.andExpect( status().isOk() )
		.andExpect( content().contentType(MediaType.APPLICATION_JSON_VALUE) )
		.andExpect( jsonPath("$.length()").value(allres.size()) )
		.andExpect( jsonPath("$[0].id").value(allres.get(0).getRestaurantId()) )
		.andExpect( jsonPath("$[0].name").value(allres.get(0).getName()) )
		.andExpect( jsonPath("$[0].address").value(allres.get(0).getAddress()) )
		.andExpect( jsonPath("$[0].cuisine").value(allres.get(0).getCuisine()) )
		.andExpect( jsonPath("$[0].averageRating").value(allres.get(0).getAverageRating()) )
		.andExpect( jsonPath("$[1].id").value(allres.get(1).getRestaurantId()) )
		.andExpect( jsonPath("$[1].name").value(allres.get(1).getName()) )
		.andExpect( jsonPath("$[1].address").value(allres.get(1).getAddress()) )
		.andExpect( jsonPath("$[1].cuisine").value(allres.get(1).getCuisine()) )
		.andExpect( jsonPath("$[1].averageRating").value(allres.get(1).getAverageRating()) );
		verify(service, times(1)).getAllRestaurants();
		verifyNoMoreInteractions(service);
	}
	@Test
	void testGetRestaurant() throws Exception {
		long id = 1;
		String uri = STARTING_URI + "/car/{id}";
		Restaurant res = new Restaurant(1L, "PfChang", "101 PfChang drive, Dallas,TX","chinese",4);
		when( service.getRestaurantById(id) ).thenReturn(res);
		mockMvc.perform( get(uri, id) )
				.andExpect( status().isOk() )
				.andExpect( content().contentType(MediaType.APPLICATION_JSON_VALUE) )
				.andExpect( jsonPath("$.id").value(res.getRestaurantId() ) )
				.andExpect( jsonPath("$.name").value(res.getName()) )
				.andExpect( jsonPath("$.address").value(res.getAddress()) )
				.andExpect( jsonPath("$.cuisine").value(res.getCuisine()) )
				.andExpect( jsonPath("$.averageRating").value(res.getAverageRating()));
		verify(service, times(1)).getRestaurantById(id);
		verifyNoMoreInteractions(service);
	}
	// test our exception handling
	@Test
	void testGetRestaurantNotFound() throws Exception {
		long id = 1;
		String uri = STARTING_URI + "/restaurant/" + id;
		when( service.getRestaurantById(id) )
			.thenThrow(new ResourceNotFoundException("Restaurant with id = " + id + " could not be found"));
		mockMvc.perform( get(uri, id) )
			.andExpect( status().isNotFound() );
		verify(service, times(1)).getRestaurantById(id);
		verifyNoMoreInteractions(service);
	}
	// json - string conversions
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}