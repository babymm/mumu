package com.jeedcp.services.support;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jasig.services.persondir.IPersonAttributes;
import org.jasig.services.persondir.support.AttributeNamedPersonImpl;
import org.jasig.services.persondir.support.StubPersonAttributeDao;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 自定义账号属性处理类
 * @author sheungxin
 *
 */
public class AccoutAttributeDao extends StubPersonAttributeDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public IPersonAttributes getPerson(String loginName) {
        String sql = "";
        if (loginName.length()==11&&LoginNameUtils.isMobile(loginName)) {
            sql = "select * from sys_user where mobile=? and del_flag=0";
        } else if(LoginNameUtils.isEmail(loginName)){
            sql = "select * from sys_user where email=? and del_flag=0";
        }else {
            sql = "select * from sys_user where login_name=? and del_flag=0";
        }
        final Map<String, Object> values = jdbcTemplate.queryForMap(sql, loginName);

        Map<String, List<Object>> attributes = new HashMap<String, List<Object>>();
        attributes.put("id",
                Collections.singletonList((Object) values.get("id")));
        attributes.put("name",Collections.singletonList((Object)values.get("name")));
        return new AttributeNamedPersonImpl(attributes);
    }
}