package ${basePackage}.impl;

import ${basePackage}.mapper.${modelNameUpperCamel}Mapper;
import ${basePackage}.databaseobject.${modelNameUpperCamel};
import ${basePackage}.${modelNameUpperCamel}Repository;
import com.seven.cola.mybatis.core.AbstractRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * Created by ${author} on ${date}.
 */
@Service
public class ${modelNameUpperCamel}RepositoryImpl extends AbstractRepository<${modelNameUpperCamel},${idType}> implements ${modelNameUpperCamel}Repository {
    @Resource
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

}
