package com.invillia.acme.model.filter;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StoreFilter {

    private String name;
    private String address;
}
