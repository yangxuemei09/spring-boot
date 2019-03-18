package com.tangdi.common.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailVo {
    /**
     * 收件人，支持多个收件人，用逗号分隔
     */
    private List<String> tos;

    private String subject;

    private String content;
}
