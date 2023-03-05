package com.ctbcbank.lab1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data                   // getter(), setter(), toString(), equals()...
@Slf4j                  // Logger的使用
@Builder                // 鏈式添加參數 Builder Pattern
@AllArgsConstructor     // 全部參數建構式
@NoArgsConstructor      // 無參數建構式
public class LombokPOJO {

    // 用 @Slf4j 來代替以下這行
    // private static final Logger log = LoggerFactory.getLogger(LombokPOJO.class);

    private String name;
    private Integer age;
}
