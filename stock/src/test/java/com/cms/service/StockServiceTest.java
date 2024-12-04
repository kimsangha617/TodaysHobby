package com.cms.service;

import com.cms.infrastructure.stock.entity.StockEntity;
import com.cms.common.exception.SkuNotFoundException;
import com.cms.domain.stock.service.StockService;
import com.cms.exception.stock.NegativeQuantityException;
import com.cms.exception.stock.StockNotEnoughException;
import com.cms.exception.stock.StockNotFoundException;
import com.cms.repository.StubSkuJpaRepository;
import com.cms.repository.StubStockJpaRepository;
import com.cms.domain.stock.type.StockStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    private StockService sut;
    StubStockJpaRepository stockRepository;
    StubSkuJpaRepository skuRepository;

//    @Mock
//    SkuRepository skuRepository;

    @BeforeEach
    void setUp() {
        stockRepository = new StubStockJpaRepository();
        skuRepository = new StubSkuJpaRepository();
//        sut = new StockService(stockRepository, skuRepository);
    }

    @AfterEach
    void tearDown() {
//        stockRepository.deleteAll();
//        skuRepository.deleteAll();
    }

    @DisplayName("재고를 추가한다")
    @Test
    void increaseQuantityFromStock_thenReturnRemainStock() {
        StockEntity stockEntity = new StockEntity(1L, 5, StockStatus.IN_STOCK, 1L, 1L);
        sut.addStock(1L, 10);
        assertThat(stockEntity.getQuantity()).isEqualTo(15);
    }

    @DisplayName("재고를 감소한다")
    @Test
    void decreaseQuantityFromStock_thenReturnRemainStock() {
        //given
        Long stockId = 1L;
        StockEntity stockEntity = new StockEntity(stockId, 10, StockStatus.IN_STOCK, 1L, 1L);
//        given(stockRepository.findByIdForUpdate(stockId)).willReturn(Optional.of(stockEntity));

        //when
        sut.decreaseStock(1L, 5);

        //then
        assertThat(stockEntity.getQuantity()).isEqualTo(5);
//        then(stockRepository).should().findByIdForUpdate(stockId);
    }

    @DisplayName("재고보다 많은 재고를 감소하면 예외가 발생한다")
    @Test
    void decreaseQuantityMoreThanStock_thenReturnException() {
        //given
        Long stockId = 1L;
        StockEntity stockEntity = new StockEntity(stockId, 10, StockStatus.IN_STOCK, 1L, 1L);
//        given(stockRepository.findByIdForUpdate(stockId)).willReturn(Optional.of(stockEntity));

        //when
        Throwable t = catchThrowable(() -> sut.decreaseStock(stockId, 20));

        //then
        assertThat(t)
                .isInstanceOf(StockNotEnoughException.class);
//        then(stockRepository).should().findByIdForUpdate(stockId);
    }

    @DisplayName("재고를 찾을 수 없는 경우 예외가 발생한다")
    @Test
    void findStockById_thenReturnStockNotFoundException() {
        //given
        Long nonExistingItemId = 2L;
        Long stockQuantity = 10L;
//        given(stockRepository.findByIdForUpdate(nonExistingItemId)).willReturn(Optional.empty());

        //when
        Throwable t = catchThrowable(() -> sut.decreaseStock(nonExistingItemId, 20));

        //then
        assertThat(t)
                .isInstanceOf(StockNotFoundException.class);
//        then(stockRepository).should().findByIdForUpdate(nonExistingItemId);
    }

    @DisplayName("유효하지 않은 productId 일 경우 예외가 발생한다")
    @Test
    void findProductByNotExistsId_thenReturnProductNotFoundException() {
        //given
        final Long nonExistingItemId = 2L;
        final int stockQuantity = 10;

        //when, then
        assertThatThrownBy(() -> sut.decreaseStock(nonExistingItemId, stockQuantity))
                .isInstanceOf(SkuNotFoundException.class);
    }

    @DisplayName("차감하는 quantity가 음수일 경우 예외가 발생한다")
    @Test
    void decreaseNegativeQuantity_thenReturnNegativeQuantityException() {
        //given
        final Long nonExistingItemId = 2L;
        final int stockQuantity = 10;

        //when, then
        assertThatThrownBy(() -> sut.decreaseStock(nonExistingItemId, stockQuantity))
                .isInstanceOf(NegativeQuantityException.class);
    }
}