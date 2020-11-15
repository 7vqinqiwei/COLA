package ${package}.entity;

import com.wayne.atom.base.model.StringMetaModel;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;


/**
 * @author qinqw
 */
@Entity
@Table(name = UserEntity.TABLE_NAME)
public class UserEntity extends StringMetaModel {

    public final static String TABLE_NAME = "tb_user";

    @Id
    private String id;

    private String name;

}
