package cn.lanaya.common.office;

import org.apache.poi.ss.usermodel.Row;

import java.util.Map;

public interface CalculateRow<T> {

    /**
     * @param beanMap
     * @param type
     * @return
     */
    T getBean(Map<String, String> beanMap, int type);

    /**
     * @param row
     * @param type
     * @return
     */
    int getRowType(Row row, int type);
}
