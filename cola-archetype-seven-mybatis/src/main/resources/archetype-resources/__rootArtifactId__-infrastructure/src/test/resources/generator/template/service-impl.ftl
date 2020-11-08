package ${basePackage}.impl;

import ${basePackage}.mapper.${modelNameUpperCamel}Mapper;
import ${basePackage}.databaseobject.${modelNameUpperCamel};
import ${basePackage}.${modelNameUpperCamel}Repository;
import com.wingto.space.common.mybatis.core.AbstractRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by ${author} on ${date}.
 */
@Service
public class ${modelNameUpperCamel}RepositoryImpl extends AbstractRepository<${modelNameUpperCamel},${idType}> implements ${modelNameUpperCamel}Repository {
    @Resource
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

}
