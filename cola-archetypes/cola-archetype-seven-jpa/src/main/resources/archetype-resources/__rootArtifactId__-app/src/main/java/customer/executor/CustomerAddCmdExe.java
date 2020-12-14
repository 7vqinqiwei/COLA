#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.customer.executor;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BizException;
import ${package}.dto.CustomerAddCmd;
import ${package}.dto.data.ErrorCode;
import org.springframework.stereotype.Component;

/**
 * addCmdExe
 * @author Frank Zhang
 * @date 2020-10-27 8:03 PM
 */
@Component
public class CustomerAddCmdExe{

    public Response execute(CustomerAddCmd cmd) {
        //The flow of usecase is defined here.
        //The core ablility should be implemented in Domain. or sink to Domian gradually
        if("ConflictCompanyName".equals(cmd.getCustomerDTO().getCompanyName())){
            throw new BizException(ErrorCode.B_CUSTOMER_companyNameConflict, "公司名冲突");
        }
        return Response.buildSuccess();
    }

}
