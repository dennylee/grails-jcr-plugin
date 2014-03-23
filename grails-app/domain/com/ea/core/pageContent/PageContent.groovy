package com.ea.core.pageContent

import com.p4f.jcr.components.Header

//@Node(jcrType = CqConstants.CQ_PAGECONTENT, jcrMixinTypes = JcrConstants.JCR_CONTENT)
class PageContent {
//    @Field(path=true)
    private String path

//    @Field(jcrName = CqConstants.SLING_RESOURCETYPE)
    private String resourceType

//    @Field(jcrName = CqConstants.JCR_TITLE)
    private String jcrTitle

//    @Bean(jcrType = "nt:unstructured", jcrOnParentVersion="IGNORE")
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
