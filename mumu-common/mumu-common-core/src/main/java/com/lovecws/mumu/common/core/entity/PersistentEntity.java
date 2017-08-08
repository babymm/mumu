package com.lovecws.mumu.common.core.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 实体化对象
 * @author ganliang
 */
public abstract class PersistentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(o, this, false);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, false);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
