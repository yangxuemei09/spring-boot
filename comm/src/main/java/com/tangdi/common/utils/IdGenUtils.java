package com.tangdi.common.utils;

import java.util.UUID;

/**
 * @author ron
 * @date 2018/12/28 19:38
 */
public class IdGenUtils {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
