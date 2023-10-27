package com.fh.tryutil.util;
import java.util.*;

/**
 * @Author jhYang
 * @Descriprition
 * @Date 2020/9/18
 */
public class SortUtils {
    public static List<Map<String,Object>> sortListByMapValuesSort(List<Map<String,Object>> sortList,String sort){
        List<Map<String,Object>> dismensionList = new ArrayList<>();
        Collections.sort(dismensionList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
               int count = 0;
                try {
                    double a = Double.parseDouble(o2.get(sort)+"");
                    double b = Double.parseDouble(o1.get(sort)+"");
                    count = Double.compare(a,b);
                } catch (NumberFormatException e) {
                    count = 0;
                }

                return count;
            }
        });
       return dismensionList;
    }
}
