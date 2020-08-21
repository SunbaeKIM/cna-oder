package shop;

import shop.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    OrderRepository orderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverShipped_UpdateStatus(@Payload Shipped shipped){

        if(shipped.isMe()){
            // 배송상태에 따른 주문서비스의 배송 상태값 업데이트. Delivery 서비스의 Shipped 이벤트에 Listening 하는 코드 작성)
            Optional<Order> orderOptional = orderRepository.findById(shipped.getOrderId());
            Order order = orderOptional.get();
            order.setStatus(shipped.getStatus());

            orderRepository.save(order);
        }
    }

}

