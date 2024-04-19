package com.alibaba.cola.biz.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 业务常用DTO
 * 主键使用泛型，系统要么使用Long，要么使用String
 * @author qi.wei
 * @date 2024/4/19 22:46
 */
@Data
public class BizDTO<K> {

    @Schema(description = "主键ID", required = true)
    private K id;

    @Schema(description = "创建用户", required = true)
    private String createBy;

    @Schema(description = "创建时间", required = true)
    private LocalDateTime createTime;

    @Schema(description = "更新用户", required = true)
    private String updateBy;

    @Schema(description = "更新时间", required = true)
    private LocalDateTime updateTime;

    @Schema(description = "删除状态(0:正常 1:已删除)", required = true)
    private Integer delFlag;


}
