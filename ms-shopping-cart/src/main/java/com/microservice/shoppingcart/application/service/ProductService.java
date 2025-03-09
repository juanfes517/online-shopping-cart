package com.microservice.shoppingcart.application.service;

import com.microservice.shoppingcart.application.dto.response.ProductResponseDTO;
import com.microservice.shoppingcart.application.exception.NotFoundException;
import com.microservice.shoppingcart.application.port.input.ProductServicePort;
import com.microservice.shoppingcart.application.port.input.ShoppingCartServicePort;
import com.microservice.shoppingcart.application.port.output.SelectedProductPersistencePort;
import com.microservice.shoppingcart.domain.model.SelectedProduct;
import com.microservice.shoppingcart.domain.model.ShoppingCart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService implements ProductServicePort {

    private final SelectedProductPersistencePort selectedProductPersistencePort;
    private final ShoppingCartServicePort shoppingCartService;

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        // TODO: Integrate with ms-inventory
        return List.of();
    }

    @Override
    public SelectedProduct changeAmount(String productCode, int amount) {
        SelectedProduct selectedProduct = selectedProductPersistencePort.findByProductCode(productCode)
                .orElseThrow(() -> new NotFoundException("The product was not found"));

        selectedProduct.setAmount(amount);
        selectedProduct.getShoppingCart().calculateTotalPrice();

        return selectedProductPersistencePort.save(selectedProduct);
    }

    @Override
    public ShoppingCart addProductToShoppingCart(Long shoppingCartId, SelectedProduct selectedProduct) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(shoppingCartId);

        selectedProduct.setShoppingCart(shoppingCart);
        selectedProduct.setId(null);
        shoppingCart.getSelectedProducts().add(selectedProduct);
        shoppingCart.calculateTotalPrice();

        selectedProductPersistencePort.save(selectedProduct);

        return shoppingCart;
    }

    @Override
    public ShoppingCart removeProductFromShoppingCart(Long shoppingCartId, SelectedProduct selectedProduct) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(shoppingCartId);
        selectedProductPersistencePort.deleteByProductCode(selectedProduct.getProductCode());

        shoppingCart.getSelectedProducts().stream()
                .filter(product -> product.getProductCode().equals(selectedProduct.getProductCode()))
                .findFirst()
                .ifPresentOrElse(product -> shoppingCart.getSelectedProducts().remove(product),
                        () -> {
                            throw new NotFoundException("The product was not found");
                        });

        shoppingCart.calculateTotalPrice();

        return shoppingCart;
    }
}
