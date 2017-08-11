package com.lovecws.mumu.common.template.velocity;

import org.apache.velocity.tools.Scope;
import org.apache.velocity.tools.config.DefaultKey;
import org.apache.velocity.tools.config.ValidScope;

/**
 * Created by Administrator on 2017/8/11/011.
 */
@DefaultKey("mumu")
@ValidScope(Scope.APPLICATION)
public class MumuUtilTag {

    public boolean contains(String names,String name){
        if(names==null||name==null){
            return false;
        }
        return names.contains(name);
    }
}
