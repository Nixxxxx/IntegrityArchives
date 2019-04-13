package com.iotlab.integrityarchives.util;

import java.util.HashMap;
import java.util.Map;

public class OperationResult {
    /**
     * 用于说明数据操作是否成功
     * @param success
     * @param description
     * @return
     */
    public static Map<String, Object> returnOperationResult(boolean success, String description){
        Map<String, Object> map = new HashMap<>();
        if(success) {
            map.put("success", true);
            map.put("msg", description + "成功");
        }else {
            map.put("success", false);
            map.put("msg", description + "失败");
        }
        return map;
    }
}
