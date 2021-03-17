package com.huawei.parkinglot.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TimeFactory {
    public LocalDateTime now(){
        return LocalDateTime.now();
    }
}
