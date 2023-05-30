package com.alibaba.cola.adapter.ext.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

/**
 * Describe: 用于定义进度基本类
 * @author qi.wei
 */
public class ProgressDto implements Serializable {

    public static final Logger log = LoggerFactory.getLogger(ProgressDto.class);

    /**
     * 当前步骤序号
     */
    private AtomicInteger stepSeq = new AtomicInteger(0);

    /**
     * 当前步骤结束时间戳
     */
    private AtomicLong startTime = new AtomicLong(System.currentTimeMillis());

    /**
     * 总耗时，单位毫秒
     */
    private AtomicLong   totalTime = new AtomicLong(0);

    /**
     * 进度信息
     */
    private List<ProgressItemDto> progressList = new LinkedList<>();


    public void addSuccessMsg(String phase, String step) {
        addMsg(phase, step, "成功");
    }

    public void addMsg(String phase, String step, String msg) {
        long cu = System.currentTimeMillis();
        long costTime = cu - startTime.getAndSet(cu);
        totalTime.getAndAdd(costTime);

        ProgressItemDto dto = new ProgressItemDto();
        dto.setSeq(stepSeq.incrementAndGet());
        dto.setPhase(phase);
        dto.setStep(step);
        dto.setMsg(msg);
        dto.setCreated(new Date(cu));
        dto.setCostTime(costTime);

        progressList.add(dto);

    }

    public static class ProgressItemDto {

        /**
         * 步骤序号
         */
        private Integer seq;
        /**
         * 阶段
         */
        private String phase;
        /**
         * 步骤
         */
        private String step;
        /**
         * 成功或者出错的信息
         */
        private String msg;
        /**
         * 执行时间
         */
        private Date created;
        /**
         * 当前步骤结束耗时,单位毫秒
         */
        private Long costTime;

        public ProgressItemDto() {

        }

        public ProgressItemDto(Integer seq, String phase, String step, String msg, Date created, Long costTime) {
            this.seq = seq;
            this.phase = phase;
            this.step = step;
            this.msg = msg;
            this.created = created;
            this.costTime = costTime;
        }

        public Integer getSeq() {
            return seq;
        }

        public void setSeq(Integer seq) {
            this.seq = seq;
        }

        public String getPhase() {
            return phase;
        }

        public void setPhase(String phase) {
            this.phase = phase;
        }

        public String getStep() {
            return step;
        }

        public void setStep(String step) {
            this.step = step;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Date getCreated() {
            return created;
        }

        public void setCreated(Date created) {
            this.created = created;
        }

        public Long getCostTime() {
            return costTime;
        }

        public void setCostTime(Long costTime) {
            this.costTime = costTime;
        }
    }


    public static void addProgressMsg(String phase, String step) {
        ProgressDto progressDto = RequestContext.get().getProgressDto();
        progressDto.addSuccessMsg(phase, step);
    }

    /**
     * 定义代码模板，记录操作的进度信息,如果出错则抛出异常，增加异常信息记录
     *
     * @param phase    阶段
     * @param step     步骤
     * @param supplier 函数
     * @param <T>      返回值
     * @return 函数的返回值
     */
    public static <T> Optional<T> addProgressMsg(String phase, String step, Supplier<T> supplier) {
        ProgressDto progressDto = RequestContext.get().getProgressDto();
        try {
            T t = supplier.get();
            progressDto.addSuccessMsg(phase, step);
            return Optional.ofNullable(t);
        } catch (Exception ex) {
            progressDto.addMsg(phase, step, "出错了,原因:"+ex.getMessage());

            progressDto.getProgressList().forEach(progressItemDto -> {
                log.error(String.format("%d , 阶段：%s,步骤：%s, 内容:%s 耗时：%d",progressItemDto.getSeq(),
                        progressItemDto.getPhase(),progressItemDto.getStep(),progressItemDto.getMsg()
                        ,progressItemDto.getCostTime()));
            });
            log.error(String.format( "总耗时：%d,总步骤：%d",progressDto.getTotalTime().get(),progressDto.getStepSeq().get()));
            throw ex;
        }

    }

    public AtomicInteger getStepSeq() {
        return stepSeq;
    }

    public void setStepSeq(AtomicInteger stepSeq) {
        this.stepSeq = stepSeq;
    }

    public AtomicLong getStartTime() {
        return startTime;
    }

    public void setStartTime(AtomicLong startTime) {
        this.startTime = startTime;
    }

    public AtomicLong getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(AtomicLong totalTime) {
        this.totalTime = totalTime;
    }

    public List<ProgressItemDto> getProgressList() {
        return progressList;
    }

    public void setProgressList(List<ProgressItemDto> progressList) {
        this.progressList = progressList;
    }
}
