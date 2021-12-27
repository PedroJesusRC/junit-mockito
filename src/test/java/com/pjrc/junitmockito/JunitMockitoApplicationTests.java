package com.pjrc.junitmockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pjrc.junitmockito.dto.FruityviceDto;
import com.pjrc.junitmockito.dto.NutritionsDto;
import com.pjrc.junitmockito.service.FruityviceService;

@SpringBootTest
class JunitMockitoApplicationTests {
	
	@Autowired
	FruityviceService fruityviceService;
	
	@BeforeEach
	public void setUp() {
		
	}
	
	@AfterEach
	public void tearDown() {
		
	}
	
	@Test
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
	public void fruitAssertEqual() {
		// Por buenas prácticas solo recomiendan un assert por método. Ya que si el primer método no pasa el test, saldría de la función
		
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
	}

	@Test
	void contextLoads() {
	}

}
