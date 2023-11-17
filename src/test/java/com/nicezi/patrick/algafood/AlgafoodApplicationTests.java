package com.nicezi.patrick.algafood;

import static org.junit.jupiter.api.Assertions.*;
import com.nicezi.patrick.algafood.domain.model.GastronomyStyle;
import com.nicezi.patrick.algafood.domain.service.GastronomyStyleService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class AlgafoodApplicationTests {

	@Autowired
	private GastronomyStyleService gastronomyStyleService;

	@Test
	void contextLoads() {
	}

	@Test
	void createGastronomyStyleWithSuccess(){
		final var gastronomyStyle = new GastronomyStyle();
		gastronomyStyle.setName("Chinesa");

		final var newGastronomyStyle = gastronomyStyleService.save(gastronomyStyle);

		assertNotNull(newGastronomyStyle);
		assertNotNull(newGastronomyStyle.getId());
	}

}
