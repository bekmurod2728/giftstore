package uz.giftstore.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.giftstore.dto.res.ResBasketDto;
import uz.giftstore.dto.res.ResProductDto;
import uz.giftstore.dto.res.ResReactionDto;
import uz.giftstore.entity.Basket;

import java.util.List;
import java.util.Optional;


public interface BasketService {

    ResponseEntity<ResBasketDto> createBasket(Long productId, HttpServletRequest request );

    ResponseEntity<Boolean> deleteBasket(Long basketId);
    ResponseEntity<List<ResProductDto>> getUserBasketProducts(HttpServletRequest request);
    ResponseEntity<List<ResReactionDto>> getTopBasketProducts(Integer page, Integer size);
    ResponseEntity<List<ResReactionDto>> findMostBasketProducts(Integer days,Integer page, Integer size);

    ResponseEntity<ResBasketDto> getBasket(Long basketId);

    Basket getBasketEntity(Long basketId);

    Optional<Basket> findBasketEntity(Long basketId);


    ResponseEntity<Page<ResBasketDto>> getAll();

    Page<Basket> getAllEntity();



}
