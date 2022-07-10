package com.fh.trymybatis.Test;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Author jhYang
 * @Date 2022/7/10 0010 11:51
 * @Discription todo
 */
@Builder
@Slf4j
public class TestStream {
    public static void main(String[] args) {
        List<Student> list = Arrays.asList(
                  new Student("1", "zhangsan", 21, "北京")
                , new Student("2", "李四", 22, "北京")
                , new Student("3", "zhangsan", 23, "北京")
                , new Student("4", "李四", 24, "上海"));
        testFilter(list);
    }


    public static void testFilter(List<Student> list){
        List<Student> collect = list.stream()
                .filter(student -> student.getAge() == 23)
                .collect(Collectors.toList());
        log.info("【输出过滤出年龄为23的：{}】",collect);
        List<Student> collect1 = list.stream()
                .filter(student -> {
                    if(student.getAge() < 22){
                        return true;
                    } else {
                        //注意此处 if 的全判断都要有
                        return false;
                    }
                }).collect(Collectors.toList());
        log.info("【输出过滤出年龄为小于22的：{}】",collect1);

    }
}
