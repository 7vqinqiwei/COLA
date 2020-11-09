package ${package}.entity;

import com.wayne.atom.base.model.StringMetaModel;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;


/**
 * @author qinqw
 */
@Entity
@Table(name = "tb_user")
public class UserEntity extends StringMetaModel {

    @Id
    private String id;

    private String name;

}
