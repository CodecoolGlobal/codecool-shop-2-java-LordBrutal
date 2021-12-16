package com.codecool.shop.service;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class CartService {
    private final OrderDao orderDao;
    private final ProductDao productDataStore;

    public CartService(OrderDao orderDao, ProductDao productDataStore) {
        this.orderDao = orderDao;
        this.productDataStore = productDataStore;
    }

    public String addProductsToCart(String items) {
        Gson gson = new Gson();
        Type merchantListType = new TypeToken<List<CartItem>>() {
        }.getType();

        List<CartItem> cartItems = gson.fromJson(String.valueOf(items), merchantListType);

        int total = 0;
        for (CartItem cartItem : cartItems) {
            Product product = productDataStore.find(cartItem.getId());
            int finalPrice = cartItem.getPiece() * Integer.parseInt(String.valueOf(product.getDefaultPrice()));
            total += finalPrice;
            cartItem.setSumItemPrice(finalPrice);
            cartItem.setPrice(Integer.parseInt(String.valueOf(product.getDefaultPrice())));
            cartItem.setName(product.getName());
        }

        orderDao.setTotalPrice(total);
        orderDao.setCartItems(cartItems);

        return gson.toJson(orderDao);
    }
}
