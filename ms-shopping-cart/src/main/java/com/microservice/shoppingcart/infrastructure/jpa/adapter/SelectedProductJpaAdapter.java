package com.microservice.shoppingcart.infrastructure.jpa.adapter;

import com.microservice.shoppingcart.application.port.output.SelectedProductPersistencePort;
import com.microservice.shoppingcart.domain.model.SelectedProduct;
import com.microservice.shoppingcart.infrastructure.jpa.entity.SelectedProductEntity;
import com.microservice.shoppingcart.infrastructure.jpa.repository.SelectedProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SelectedProductJpaAdapter implements SelectedProductPersistencePort {

    private final SelectedProductRepository selectedProductRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<SelectedProduct> findById(Long id) {
        return selectedProductRepository.findById(id)
                .map(selectedProduct -> modelMapper.map(selectedProduct, SelectedProduct.class));
    }

    @Override
    public SelectedProduct save(SelectedProduct selectedProduct) {
        SelectedProductEntity selectedProductEntity = selectedProductRepository.save(
                modelMapper.map(selectedProduct, SelectedProductEntity.class));

        return modelMapper.map(selectedProductEntity, SelectedProduct.class);
    }
}
