package com.nomoreft.axonmsa.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    /*
    없으면 Axon jacson Messaging에서 Retieve Serializer를 못찾는다고 한다.
    .lenientDeserialization(): Lenient(관대한) 역직렬화를 활성화합니다. 이 옵션을 활성화하면, Serializer는 알 수 없는 필드나 유형 등을 만났을 때도 역직렬화 과정을 계속 진행합니다.
    .defaultTyping(): defaultTyping을 활성화합니다. 이 옵션을 활성화하면, Serializer는 객체 유형 정보를 JSON에 포함시켜 역직렬화 과정에서 유형 정보를 복원할 수 있게 합니다.
     */
    @Qualifier("messageSerializer")
    @Bean
    public Serializer messageSerializer(ObjectMapper mapper) {
        return JacksonSerializer.builder()
                .objectMapper(mapper)
                .lenientDeserialization()
                .defaultTyping()
                .build();
    }
}
