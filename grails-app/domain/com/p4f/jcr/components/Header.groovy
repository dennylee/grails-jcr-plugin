package com.p4f.jcr.components

import com.ea.core.UnstructuredNode
import com.ea.core.page.constants.CqConstants
import org.apache.jackrabbit.JcrConstants
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Field
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Node

//@Node(jcrType = CqConstants.NT_UNSTRUCTURED)
class Header  {
//    @Field(path=true)
    private String path

//    @Field(jcrName = "imageReference")
    private String imageReference

//    @Field(jcrName = "text")
    private String text

    String getImageReference() {
        return imageReference
    }

    void setImageReference(String imageReference) {
        this.imageReference = imageReference
    }

    String getText() {
        return text
    }

    void setText(String text) {
        this.text = text
    }

    String getPath() {
        return path
    }

    void setPath(String path) {
        this.path = path
    }
}
