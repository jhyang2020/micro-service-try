package com.fh.trymybatis.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author jhYang
 * @Descriprition
 * @Date 2022/7/13
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class Test {

    @RequestMapping("getStr")
    public ResponseEntity getStr() {
        return ResponseEntity.ok("测试成功...");
    }

}
