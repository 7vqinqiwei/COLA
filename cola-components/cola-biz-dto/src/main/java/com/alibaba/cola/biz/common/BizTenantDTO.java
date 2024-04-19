package com.alibaba.cola.biz.common;

import com.alibaba.cola.dto.DTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 租户相关DTO
 *
 * @author qi.wei
 * @date 2024/4/19 22:43
 */
@Data
public class BizTenantDTO<K> extends BizDTO<K> {

    @Schema(description = "租户ID")
    private Long tenantId;

}
