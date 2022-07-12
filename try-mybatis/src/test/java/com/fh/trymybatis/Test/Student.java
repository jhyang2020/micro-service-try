package com.fh.trymybatis.Test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author jhYang
 * @Date 2022/7/10 0010 12:00
 * @Discription todo
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String idNo;
    private String name;
    private int age;
    private String addr;

}
