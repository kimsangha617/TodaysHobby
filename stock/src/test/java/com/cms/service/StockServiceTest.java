package com.cms.service;

import com.cms.domain.Sku;
import com.cms.domain.Stock;
import com.cms.exception.stock.StockNotEnoughException;
import com.cms.exception.stock.StockNotFoundException;
import com.cms.repository.SkuRepository;
import com.cms.repository.StockRepository;
import com.cms.type.StockStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.InstanceOfAssertFactories.THROWABLE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    @InjectMocks
    private StockService sut;
    @Mock
    StockRepository stockRepository;

    @Mock
    SkuRepository skuRepository;

    @AfterEach
    void tearDown() {
        stockRepository.deleteAll();
        skuRepository.deleteAll();
    }

    @DisplayName("재고를 추가한다")
    @Test
    void increaseQuantityFromStock_thenReturnRemainStock() {
        Stock stock = new Stock(1L, 5, StockStatus.IN_STOCK, 1L, 1L);
        sut.addStock(1L, 10);
        assertThat(stock.getQuantity()).isEqualTo(15);
    }

    @DisplayName("재고를 감소한다")
    @Test
    void decreaseQuantityFromStock_thenReturnRemainStock() {
        //given
        Long stockId = 1L;
        Stock stock = new Stock(stockId, 10, StockStatus.IN_STOCK, 1L, 1L);
        given(stockRepository.findByIdForUpdate(stockId)).willReturn(Optional.of(stock));

        //when
        sut.decreaseStock(1L, 5);

        //then
        assertThat(stock.getQuantity()).isEqualTo(5);
        then(stockRepository).should().findByIdForUpdate(stockId);
    }

    @DisplayName("재고보다 많은 재고를 감소하면 예외가 발생한다")
    @Test
    void decreaseQuantityMoreThanStock_thenReturnException() {
        //given
        Long stockId = 1L;
        Stock stock = new Stock(stockId, 10, StockStatus.IN_STOCK, 1L, 1L);
        given(stockRepository.findByIdForUpdate(stockId)).willReturn(Optional.of(stock));

        //when
        Throwable t = catchThrowable(() -> sut.decreaseStock(stockId, 20));

        //then
        assertThat(t)
                .isInstanceOf(StockNotEnoughException.class);
        then(stockRepository).should().findByIdForUpdate(stockId);
    }

    @DisplayName("재고를 찾을 수 없는 경우 예외가 발생한다")
    @Test
    void findStockById_thenReturnStockNotFoundException() {
        Long stockId = 2L;
        //given
        given(stockRepository.findByIdForUpdate(stockId)).willReturn(Optional.empty());

        //when
        Throwable t = catchThrowable( () -> sut.decreaseStock(stockId, 20));

        //then
        assertThat(t)
                .isInstanceOf(StockNotFoundException.class);
        then(stockRepository).should().findByIdForUpdate(stockId);
    }
}