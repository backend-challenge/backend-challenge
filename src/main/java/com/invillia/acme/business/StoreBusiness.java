package com.invillia.acme.business;

import com.invillia.acme.model.db.Store;
import com.invillia.acme.model.dto.StoreDto;
import com.invillia.acme.model.filter.StoreFilter;
import com.invillia.acme.repository.StoreRepository;
import com.invillia.acme.repository.specification.StoreSpecification;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Component
public class StoreBusiness {

	@Autowired
	private StoreRepository repository;

	public Store createStore(StoreDto dto) {
		Store entity = new Store();
		BeanUtils.copyProperties(dto, entity, "id");
		return repository.save(entity);
	}

	public Store updateStore(Integer id, StoreDto dto) {
		repository.findById(id).orElseThrow(
				() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Loja não encontrada")
		);
		Store entity = new Store();
		BeanUtils.copyProperties(dto, entity, "id");
		entity.setId(id);
		return repository.save(entity);
	}

	public List<Store> getStoreByFilter(String name, String address) {
		StoreFilter filter = StoreFilter
				.builder()
				.name(name)
				.address(address)
				.build();
		Specification<Store> storeSpecification = StoreSpecification.getFilter(filter);
		return repository.findAll(storeSpecification);
	}

}
