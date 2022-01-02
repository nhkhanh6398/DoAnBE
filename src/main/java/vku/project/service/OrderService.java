package vku.project.service;

import vku.project.dto.DtoOrder;
import vku.project.entity.Account;
import vku.project.entity.Product;

public interface OrderService {
    void order(DtoOrder dtoOrder);
}
