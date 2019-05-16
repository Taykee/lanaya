package cn.lanaya.common.validate;

import cn.lanaya.common.exception.BusinessException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

public class ValidatorUtils {
    /**
     *
     * @param str
     * @param msg
     */
    public static void notBlank(String str, String msg){
        if(StringUtils.isBlank(str)){
            throw new BusinessException(msg);
        }
    }

    /**
     *
     * @param str
     * @param msg
     */
    public static void isBlank(String str, String msg){
        if(StringUtils.isNotBlank(str)){
            throw new BusinessException(msg);
        }
    }

    /**
     *
     * @param obj
     * @param msg
     */
    public static void isNull(Object obj, String msg){
        if(obj == null){
            throw new BusinessException(msg);
        }
    }

    /**
     *
     * @param obj
     * @param msg
     */
    public static void notNull(Object obj, String msg){
        if(obj != null){
            throw new BusinessException(msg);
        }
    }

    /**
     *
     * @param col
     * @param msg
     * @param <T>
     */
    public static <T> void notEmpty(Collection<T> col, String msg){
        if(CollectionUtils.isEmpty(col)){
            throw new BusinessException(msg);
        }
    }

    /**
     *
     * @param col
     * @param msg
     * @param <T>
     */
    public static <T> void isEmpty(Collection<T> col, String msg){
        if(CollectionUtils.isNotEmpty(col)){
            throw new BusinessException(msg);
        }
    }
}
