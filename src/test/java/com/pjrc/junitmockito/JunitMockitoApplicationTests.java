package com.pjrc.junitmockito;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.pjrc.junitmockito.dto.FruityviceDto;
import com.pjrc.junitmockito.dto.NutritionsDto;
import com.pjrc.junitmockito.service.FruityviceService;

@SpringBootTest
class JunitMockitoApplicationTests {
	
	@Autowired
	FruityviceService fruityviceService;
	
	@BeforeAll
	public static void beforeAllTests() {
		// Se ejecuta solo una vez y antes de que todos los test se hayan ejecutado.
	}
	
	@AfterAll
	public static void afterAlltests() {
		// Se ejecutar solo una vez y después de que todos los test se hayan ejecutado.
	}
	
	@BeforeEach
	public void setUp() {
		// Se ejecuta antes de cada test.
	}
	
	@AfterEach
	public void tearDown() {
		// Se ejecuta después de cada test.
	}
	
	@Test
	@DisplayName("Método para validar que lo que obtenemos no es null") // Para ponerle nombre/descripción a los test
	public void fruitNotNull() {
		assertNotNull(fruityviceService.getFruityvice("banana"), "Fruityvice must be not null");
	}
	
	@Test
	public void nutritionsNotNull() {
		assertNotNull(fruityviceService.getFruityvice("banana").getNutritions(), "Nutritions must be not null");
	}
	
	@Test
	public void numNutritionsEquals() {
		assertEquals(136.4, fruityviceService.getNumNutritions("banana"));
	}
	
	@Test
	public void numNutritionsExpectedExceptionEquals() {
		assertThrows(WebClientResponseException.class, () -> fruityviceService.getNumNutritions("ban"), "No existe la fruta ban");
	}
	
	@Test
	public void fruitAssertEqual() {
		// Por buenas prácticas solo recomiendan un assert por método. Ya que si el primer método no pasa el test, saldría de la función.
		// Si necesitas tener varios assert en un mismo método y evitar lo anterior, puedes usar el assertAll. 
		
		// 1. SetUp
		NutritionsDto nutritions = new NutritionsDto(22, 1, 0.2, 96, 17.2);
		FruityviceDto fruityvice = new FruityviceDto("Musa", "Banana", 1, "Musaceae", "Zingiberales", nutritions);
		
		// 2. Action
		FruityviceDto fruityviceResult = fruityviceService.getFruityvice("banana");
		
		// 3. Assert
		assertEquals(fruityvice.getFamily(), fruityviceResult.getFamily(), "Ha fallado el equals del getFamily()");
		assertEquals(fruityvice.getGenus(), fruityviceResult.getGenus());
		assertEquals(fruityvice.getId(), fruityviceResult.getId());
		assertEquals(fruityvice.getName(), fruityviceResult.getName());
		assertEquals(fruityvice.getOrder(), fruityviceResult.getOrder());
		assertEquals(fruityvice.getNutritions(), fruityviceResult.getNutritions());
		assertEquals(fruityvice.getNutritions().getCalories(), fruityviceResult.getNutritions().getCalories());
		assertEquals(fruityvice.getNutritions().getCarbohydrates(), fruityviceResult.getNutritions().getCarbohydrates());
		assertEquals(fruityvice.getNutritions().getFat(), fruityviceResult.getNutritions().getFat());
		assertEquals(fruityvice.getNutritions().getProtein(), fruityviceResult.getNutritions().getProtein());
		assertEquals(fruityvice.getNutritions().getSugar(), fruityviceResult.getNutritions().getSugar());
		
		assertNotSame(fruityvice, fruityviceResult);
		
		// Ejemplo de assertAll
		assertAll(
				()-> assertEquals(fruityvice.getGenus(), fruityviceResult.getGenus()),
				()-> assertEquals(fruityvice.getId(), fruityviceResult.getId()),
				()-> assertEquals(fruityvice.getName(), fruityviceResult.getName())
		);
	}

	@Test
	@Disabled("Se ha deshabilitado este test como prueba de la etiqueta") // Es útil por si el método a testear tiene un bug.
	void contextLoads() {
	}
	
	// Test Parametrizados.
	@ParameterizedTest(name = "{index} => a={0}, b={1}, sum={2}")
	@MethodSource("addProviderData")
	public void addParameterizedTest(int a, int b, int sum) {
		assertEquals(sum, a+b);
	}
	
	private static Stream<Arguments> addProviderData(){
		// Esto nos lo podríamos traer de una fuente de datos como una BD
		return Stream.of(
				Arguments.of(6,2,8),
				Arguments.of(-6,-2,-8),
				Arguments.of(6,-2,4)
				);
	}
	

}
