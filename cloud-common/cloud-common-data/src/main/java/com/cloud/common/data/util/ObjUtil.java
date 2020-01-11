package com.cloud.common.data.util;


import com.cloud.common.data.user.SystemService;
import com.cloud.common.entity.BaseEntity;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

/**
 * 对象操作
 * @author Aijm
 * @since 2019/8/24
 */
@UtilityClass
public class ObjUtil {


    private final SystemService systemService = SpringUtil.getBean(SystemService.class);

    /**
     * 树形 第一个节点的id 当去除第一个节点时
     */
    public final Long ROOT_ID = 1L;
    /**
     * 树形 第一个节点的pid
     */
    public final Long ROOT_PID = 0L;

    /**
     * 插入之前执行方法，需要手动调用
     */
    public <T extends BaseEntity> void preInsert(T entity){

        Long userId = systemService.getUserId();
        if (userId  != null) {
            entity.setCreateBy(userId);
            entity.setUpdateBy(userId);
        }
        entity.setId(IdUtils.getNextId());
        LocalDateTime now = LocalDateTime.now();
        entity.setCreateDate(now);
        entity.setUpdateDate(now);
        entity.setDelFlag(T.DEL_FLAG_NORMAL);
    }

    /**
     * 更新之前执行方法，需要手动调用
     */
    public <T extends BaseEntity> void preUpdate(T entity){

        Long userId = systemService.getUserId();
        if (userId  != null) {
            entity.setUpdateBy(userId);
        }
        LocalDateTime now = LocalDateTime.now();
        entity.setUpdateDate(now);
    }


}
