package com.DDIS.post.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

        @Bean
        public ModelMapper modelMapper() {
            ModelMapper modelMapper = new ModelMapper();

            modelMapper.getConfiguration()
                    .setMatchingStrategy(MatchingStrategies.STRICT)   // 필드명 정확히 일치
                    .setSkipNullEnabled(true);                          // null 필드는 매핑 제외

            return modelMapper;
        }

}
