package com.invillia.acme.repository.specification;

import com.invillia.acme.model.db.Store;
import com.invillia.acme.model.filter.StoreFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class StoreSpecification {

    public static Specification<Store> getFilter(StoreFilter filter) {
        return Specification
                .where(likeAddress(filter.getAddress())
                        .and(likeName(filter.getName())));
    }

    private static Specification<Store> likeAddress(String address) {
        return (root, query, cb) -> {
            if (StringUtils.isEmpty(address)) {
                return  null;
            }
            return cb.like(cb.upper(root.get("name")), "%" + address.toUpperCase() + "%");
        };
    }

    private static Specification<Store> likeName(String name) {
        return (root, query, cb) -> {
            if (StringUtils.isEmpty(name)) {
                return null;
            }
            return cb.like(cb.upper(root.get("name")), "%" + name.toUpperCase() + "%");
        };
    }
}
