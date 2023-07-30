package com.nomoreft.service;

import com.nomoreft.axonmsa.queries.FindAllOrderedProductsQuery;
import com.nomoreft.axonmsa.queries.OrderUpdatesQuery;
import com.nomoreft.axonmsa.queries.TotalProductsShippedQuery;
import com.nomoreft.axonmsa.queries.entity.Order;
import com.nomoreft.querymodel.dto.OrderResponse;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderQueryService {

    private final QueryGateway queryGateway;

    public OrderQueryService(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    public CompletableFuture<List<OrderResponse>> findAllOrders() {
        return queryGateway.query(new FindAllOrderedProductsQuery(), ResponseTypes.multipleInstancesOf(Order.class))
                .thenApply(r -> r.stream()
                        .map(OrderResponse::from).toList());
    }

    public Flux<OrderResponse> allOrdersStreaming(){
        Publisher<Order> orderPublisher = queryGateway.streamingQuery(new FindAllOrderedProductsQuery(), Order.class);
        return Flux.from(orderPublisher)
                .map(OrderResponse::from);
    }

    public Integer totalShipped(String productId) {
        return queryGateway.scatterGather(new TotalProductsShippedQuery(productId), ResponseTypes.instanceOf(Integer.class), 10L, java.util.concurrent.TimeUnit.SECONDS)
                .reduce(0, Integer::sum);
    }

    public Flux<OrderResponse> orderUpdates(String orderId){
        return subscriptionQuery(new OrderUpdatesQuery(orderId), ResponseTypes.instanceOf(Order.class)).map(OrderResponse::from);
    }

    /*
    Q,R은 메서드 제네릭 타입으로 Q - Query, R - ResultType으로 주로 쓰인다. T는 type
    queryGateway.subscriptionQuery 메서드를 호출하여 구독 쿼리를 실행하고,
    SubscriptionQueryResult를 반환합니다. initialResult() 메서드는 초기 결과를 Flux 스트림으로 반환하고,
    updates() 메서드는 이후의 업데이트를 스트림으로 반환합니다.
    이 두 스트림은 concatWith 메서드를 사용하여 연결됩니다. doFinally 메서드는 구독이 종료되면 자원을 정리합니다.
     */
    private <Q, R> Flux<R> subscriptionQuery(Q query, ResponseType<R> resultType) {
        SubscriptionQueryResult<R, R> result = queryGateway.subscriptionQuery(query, resultType, resultType);
        return result.initialResult()
                .concatWith(result.updates())
                .doFinally(signal -> result.close());
    }

}
