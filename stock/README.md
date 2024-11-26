    3000 -> 재고는 100개
    100개가 1명씩 소진되고 이후 요청분은
    재고를 확인해서 없는경우 실패를 보내야함.
    나이키 포스 / 흰색 - SkuId : 10
    skuId / Qty
    10  / 100
    
    100 <- 30개  09:00:01.000 (70)
    70 <- 20개  09:00:02.000 (50)
    50 <- 100개  09:00:03.000 (???)
    50개 남은 상황에서 Controller -> Service -> DB
    Service에서 DB를 조회해서 ( 09:00:03.000 ) <--- Stock 50개 남아있음.
    ( 09:00:03.000 )에는 Stock 50개 남아있음. <--- 100개의 Request의 개별 Request의 결과는 모두 성공(50개)
    잔여 수량이 있냐? <----- OK 응답.
    있으면 재고차감을 하면됨.
    ( Issue 발생 ) 잔여 재고는 50개인데 100개가 재고차감을 하는 상황이 발생.

    Controller -> Service -> DB
    Stock stock = findStockBySkuId(skuId);
     if(stock.qty > 0){
      stock.decrease()
     }

    Example ( 재고가 3개 남은 상황에서 )
    Request 1 -> 09:00:03.001 -> Transaction End (09:00:03.002) -> 남은재고( 2개 )
    Request 2 -> 09:00:03.003(2개의 재고가 보임) -> Transaction End (09:00:03.025) -> 남은재고 ( 1개 )
    Request 3 -> 09:00:03.005(2개의 재고가 보임) -> Transaction End (09:00:03.028) -> 남은재고 ( 0개 )
    Request 4 -> 09:00:03.011(2개의 재고가 보임) -> Transaction End (09:00:03.030) -> 남은재고 ( -1개 ) or Exception
    Request 5 -> 09:00:03.031(0개의 재고가 보임) -> Validation 에 걸림.


    Example ( 재고가 3개 남은 상황에서 ) - Optimistic Lock 적용시
    Request 1 -> 09:00:03.001 -> Transaction End (09:00:03.002) -> 남은재고( 2개 ) -> Version 증가 ( Version 1 )
    Request 2 -> 09:00:03.003(2개의 재고가 보임)(영속성 데이터 조회시 Version 1 ) -> Transaction End (09:00:03.025) -> 남은재고 ( 1개 ) -> Version 증가 ( Version 2 )
    Request 3 -> 09:00:03.005(2개의 재고가 보임)(영속성 데이터 조회시 Version 1 ) -> Transaction End (09:00:03.028) -> OptimisticLock 발생
    Request 4 -> 09:00:03.011(2개의 재고가 보임)(영속성 데이터 조회시 Version 1 ) -> Transaction End (09:00:03.030) -> OptimisticLock 발생
    Request 5 -> 09:00:03.031(1개의 재고가 보임)(영속성 데이터 조회시 Version 2 ) -> Transaction End (09:00:03.050) -> 남은재고 ( 0개 ) -> Version 증가 ( Version 3 )

    Pessimistic Lock
    순서보장 -> 다른 추가적인 방법을 사용해야함.
    선착순 구매
    수강신청
    공연 예매
    아이폰 16 예약판매 <--
    손흥민 vs 믠핸 ---> Queue (Kakfa)

    상품 + 옵션 1개의 skuId;
    나이키 포스 / 흰색 / 270 / skuId: 1
    나이키 포스 / 검정색 / 270 / skuId: 2
    나이키 포스 / 빨간색 / 270 / skuId: 3
    나이키 포스 / 흰 / 275 / skuId: 4
    나이키 포스 / 빨간색 / 275 / skuId: 5