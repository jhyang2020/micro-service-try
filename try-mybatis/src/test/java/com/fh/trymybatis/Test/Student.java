package com.fh.trymybatis.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author jhYang
 * @Date 2022/7/10 0010 12:00
 * @Discription todo
 */
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String idNo;
    private String name;
    private int age;
    private String addr;

}
