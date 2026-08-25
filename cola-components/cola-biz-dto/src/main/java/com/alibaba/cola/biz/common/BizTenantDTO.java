package com.alibaba.cola.biz.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 租户相关DTO
 *
 * @author qi.wei
 * @date 2024/4/19 22:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BizTenantDTO<K> extends BizDTO<K> {

    @Schema(description = "租户ID")
    private Long tenantId;

}
