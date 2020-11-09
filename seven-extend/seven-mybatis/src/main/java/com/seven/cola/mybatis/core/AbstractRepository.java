package com.seven.cola.mybatis.core;


import com.alibaba.cola.exception.Exceptions;
import com.seven.cola.mybatis.exception.MybatisErrorCodeEnum;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 基于通用MyBatis Mapper插件的Service接口的实现
 * @author qqw
 */
public abstract class AbstractRepository<T,ID> implements Repository<T,ID> {

    @Autowired
    protected Mapper<T> mapper;

    /**
     * 当前泛型真实类型的Class
     */
    private final Class<T> modelClass;

    private final String COMMA = ",";

    private final String SINGLE_QUOTE = "'";

    @SuppressWarnings({"unchecked"})
    public AbstractRepository() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    @Override
    public void insert(T model) {
        mapper.insertSelective(model);
    }

    @Override
    public void insertAll(List<T> models) {
        mapper.insertList(models);
    }

    @Override
    public void deleteById(ID id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(List<ID> ids) {
        if (null != ids && ids.size() > 0) {
            if (ids.size() == 1) {
                mapper.deleteByPrimaryKey(ids.get(0));
            }
            ids = ids.stream().filter(Objects::nonNull).collect(Collectors.toList());
            StringBuilder deleteIds = new StringBuilder();
            if (ids.get(0) instanceof String) {
                for (ID id : ids) {
                    deleteIds.append(SINGLE_QUOTE).append(id).append(SINGLE_QUOTE).append(COMMA);
                }
            } else {
                for (ID id : ids) {
                    deleteIds.append(id).append(COMMA);
                }
            }
            if (deleteIds.length() >0) {
                String realIds = deleteIds.substring(0,deleteIds.length()-1);
                mapper.deleteByIds(realIds);
            }
        }
    }

    @Override
    public void update(T model) {
        mapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public T findById(ID id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public T findBy(String fieldName, Object value) throws TooManyResultsException {
        try {
            T model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(model, value);
            return mapper.selectOne(model);
        } catch (ReflectiveOperationException e) {
            Exceptions.throwSysException(MybatisErrorCodeEnum.REFLECTIVE_OPERATION_ERROR, e);
        }
        return null;
    }

    @Override
    public List<T> findByIds(List<ID> ids) {
        List<T> list = new ArrayList();
        if (null != ids && ids.size() > 0) {
            if (ids.size() == 1) {
                list.add(mapper.selectByPrimaryKey(ids.get(0)));
                return list;
            }
            ids = ids.stream().filter(Objects::nonNull).collect(Collectors.toList());
            StringBuilder stringBuilder = new StringBuilder();
            if (ids.get(0) instanceof String) {
                for (ID id : ids) {
                    stringBuilder.append(SINGLE_QUOTE).append(id).append(SINGLE_QUOTE).append(COMMA);
                }
            } else {
                for (ID id : ids) {
                    stringBuilder.append(id).append(COMMA);
                }
            }
            if (stringBuilder.length() >0) {
                String realIds = stringBuilder.substring(0,stringBuilder.length()-1);
                list.addAll(mapper.selectByIds(realIds));
            }
        }
        return list;
    }

    @Override
    public List<T> findByCondition(Condition condition) {
        return mapper.selectByCondition(condition);
    }

    @Override
    public List<T> findAll() {
        return mapper.selectAll();
    }
}
