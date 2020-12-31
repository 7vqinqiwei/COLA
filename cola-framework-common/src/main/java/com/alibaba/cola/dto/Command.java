package com.alibaba.cola.dto;

import com.alibaba.cola.extension.BizScenario;

import java.io.Serializable;

/**
 * Command stands for a request from Client.
 * According CommandExecutor will help to handle the business logic. This is a classic Command Pattern
 *
 * @author fulan.zjf 2017年10月27日 下午12:28:24
 */
public abstract class Command extends DTO{

    private static final long serialVersionUID = 1L;

    private BizScenario bizScenario;

    private Long timestamp;

    private Serializable operator;

	public BizScenario getBizScenario() {
		return bizScenario;
	}

	public void setBizScenario(BizScenario bizScenario) {
		this.bizScenario = bizScenario;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Serializable getOperator() {
		return operator;
	}

	public void setOperator(Serializable operator) {
		this.operator = operator;
	}
}
