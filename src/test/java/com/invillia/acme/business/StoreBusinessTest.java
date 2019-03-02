package com.invillia.acme.business;

import com.invillia.acme.model.dto.StoreDto;
import com.invillia.acme.repository.StoreRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class StoreBusinessTest {

	@InjectMocks
	private StoreBusiness business;

	@Mock
	private StoreRepository repository;

	@Test
	public void testCreateStore(StoreDto dto) {

	}

}
