package com.ea.core.pageContent

import com.ea.core.components.Header
import com.ea.core.page.constants.CqConstants
import org.apache.jackrabbit.JcrConstants
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Bean
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Field
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Node
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Collection

@Node(jcrType = CqConstants.CQ_PAGECONTENT, jcrMixinTypes = JcrConstants.JCR_CONTENT)
class PageContent {
    @Field(path=true)
    private String path

    @Field(jcrName = CqConstants.SLING_RESOURCETYPE)
    private String resourceType

    @Field(jcrName = CqConstants.JCR_TITLE)
    private String jcrTitle

    @Bean(jcrType = "nt:unstructured", jcrOnParentVersion="IGNORE")
    private Header header

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

    Header getHeader() {
        return header
    }

    void setHeader(Header header) {
        this.header = header
    }
}
