package com.invillia.acme.business;

import com.invillia.acme.model.db.Store;
import com.invillia.acme.model.dto.StoreDto;
import com.invillia.acme.repository.StoreRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class StoreBusinessTest {

	private static final String NAME = "name";
	private static final String ADDRESS = "name";
	private static final Integer ID = 1;
	@InjectMocks
	private StoreBusiness business;

	@Mock
	private StoreRepository repository;

	@Test
	public void testCreateStore() {
		business.createStore(this.getStoreDto());
		ArgumentCaptor<Store> argumentCaptor = ArgumentCaptor.forClass(Store.class);
		Mockito.verify(repository).save(argumentCaptor.capture());
		Store result = argumentCaptor.getValue();

		Assert.assertEquals(NAME, result.getName());
		Assert.assertEquals(ADDRESS, result.getAddress());
	}

	@Test
	public void testUpdateStore() {
		Mockito.when(repository.findById(ID)).thenReturn(Optional.of(new Store()));
		business.updateStore(ID, this.getStoreDto());
		ArgumentCaptor<Store> argumentCaptor = ArgumentCaptor.forClass(Store.class);
		Mockito.verify(repository).save(argumentCaptor.capture());
		Store result = argumentCaptor.getValue();

		Assert.assertEquals(NAME, result.getName());
		Assert.assertEquals(ADDRESS, result.getAddress());
	}

	@Test(expected = HttpClientErrorException.class)
	public void testUpdateStoreNotFound() {
		Mockito.when(repository.findById(ID)).thenReturn(Optional.empty());
		business.updateStore(ID, this.getStoreDto());
	}

	@Test
	public void testGetStoreByFilter() {
		business.getStoreByFilter(NAME, ADDRESS);
		Mockito.verify(repository).findAll((Specification<Store>) Mockito.any());
	}

	private StoreDto getStoreDto() {
		StoreDto dto = new StoreDto();
		dto.setAddress(ADDRESS);
		dto.setName(NAME);
		return dto;

	}

}
