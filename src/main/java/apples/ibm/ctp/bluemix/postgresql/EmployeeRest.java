package apples.ibm.ctp.bluemix.postgresql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeRest
{
    private static final Logger log = LoggerFactory.getLogger(EmployeeRest.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping ("/allemps")
    public List<Employee> allEmps ()
    {
        List<Employee> emps = new ArrayList<Employee>();

        jdbcTemplate.query(
                "SELECT id, first_name, last_name FROM employee",
                (rs, rowNum) -> new Employee(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"))
        ).forEach(customer -> emps.add(customer));

        return emps;

    }
}
