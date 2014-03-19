package com.ea.core

import com.ea.core.page.constants.CqConstants
import org.apache.jackrabbit.JcrConstants
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Field
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Node

@Node(jcrType = CqConstants.NT_UNSTRUCTURED, isAbstract = true)
abstract class UnstructuredNode {
    @Field(path=true)
    private String path

    @Field(jcrName = CqConstants.SLING_RESOURCETYPE)
    private String resourceType

    @Field(jcrName = CqConstants.JCR_TITLE)
    private String jcrTitle

    String getPath() {
        return path
    }

    void setPath(String path) {
        this.path = path
    }

    String getResourceType() {
        return resourceType
    }

    void setResourceType(String resourceType) {
        this.resourceType = resourceType
    }

    String getJcrTitle() {
        return jcrTitle
    }

    void setJcrTitle(String jcrTitle) {
        this.jcrTitle = jcrTitle
    }
}
