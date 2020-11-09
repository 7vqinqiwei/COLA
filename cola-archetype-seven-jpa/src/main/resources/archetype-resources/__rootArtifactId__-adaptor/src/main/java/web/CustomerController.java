#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import ${package}.api.CustomerServiceI;
import ${package}.api.UserServiceI;
import ${package}.dto.CustomerAddCmd;
import ${package}.dto.CustomerListByNameQry;
import ${package}.dto.data.CustomerDTO;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.web.bind.annotation.*;



import javax.annotation.Resource;

/**
 * Customer Web Adaptor
 *
 * Controller
 *
 * @author Frank Zhang
 * @date 2020-10-27 8:03 PM
 */
@RestController
public class CustomerController {

    @Resource
    private CustomerServiceI customerService;

    @GetMapping(value = "/hello_world")
    public String helloWorld(){
        return "Hello, welcome to COLA world!";
    }

    @Resource
    private UserServiceI userServiceI;

    @GetMapping(value = "/test")
    public Response test(){
        return SingleResponse.of(userServiceI.findAll());
    }

    @GetMapping(value = "/customer")
    public MultiResponse<CustomerDTO> listCustomerByName(@RequestParam(required = false) String name){
        CustomerListByNameQry customerListByNameQry = new CustomerListByNameQry();
        customerListByNameQry.setName(name);
        return customerService.listByName(customerListByNameQry);
    }

    @PostMapping(value = "/customer")
    public Response addCustomer(@RequestBody CustomerAddCmd customerAddCmd){
        return customerService.addCustomer(customerAddCmd);
    }
}
