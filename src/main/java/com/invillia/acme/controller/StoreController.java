package com.invillia.acme.controller;

import com.invillia.acme.business.StoreBusiness;
import com.invillia.acme.model.db.Store;
import com.invillia.acme.model.dto.StoreDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {

	@Autowired
	private StoreBusiness business;

	@ApiOperation("Cria uma nova Loja")
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Store> createStore(@RequestBody StoreDto dto) {
		return ResponseEntity.ok(business.createStore(dto));
	}

	@ApiOperation("Atualiza uma loja já existente")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Store> updateStore(@RequestBody StoreDto dto, @PathVariable(value = "id") Integer id) {
		return ResponseEntity.ok(business.updateStore(id, dto));
	}

	@ApiOperation("Lista as lojas de acordo com os filtros")
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	public ResponseEntity<List<Store>> getStoreByFilter(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "address", required = false) String address) {
		return getStoreByFilter(name, address);
	}
}
