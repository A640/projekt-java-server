package foodwarehouse.web.response.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import foodwarehouse.core.data.complaint.Complaint;
import foodwarehouse.core.data.order.Order;
import foodwarehouse.core.data.productBatch.ProductBatch;
import foodwarehouse.web.response.complaint.CustomerComplaintResponse;
import foodwarehouse.web.response.payment.PaymentResponse;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public record CustomerOrderResponse(
        @JsonProperty(value = "order", required = true)         OrderDataResponse orderDataResponse,
        @JsonProperty(value = "products", required = true)       List<OrderProductResponse> orderProductsResponse,
        @JsonProperty(value = "payment", required = true)       PaymentResponse paymentState,
        @JsonProperty(value = "delivery", required = true)      OrderDeliveryResponse orderDeliveryResponse,
        @JsonProperty(value = "complaints", required = true)    List<CustomerComplaintResponse> complaintsResponse) {

        public static CustomerOrderResponse from(Order order, List<ProductBatch> products, List<Complaint> complaints, List<Integer> quantity) {

                List<OrderProductResponse> orderProductResponses = new LinkedList<>();

                for(int i = 0; i < products.size(); i++) {
                        orderProductResponses.add(OrderProductResponse.fromProductBatch(products.get(i), quantity.get(i)));
                }

                return new CustomerOrderResponse(
                        OrderDataResponse.fromOrder(order),
                        orderProductResponses,
                        PaymentResponse.fromPayment(order.payment()),
                        OrderDeliveryResponse.fromDelivery(order.delivery()),
                        complaints.stream().map(CustomerComplaintResponse::fromComplaint).collect(Collectors.toList()));
        }
}
