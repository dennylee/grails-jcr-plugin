package com.p4f.jcr.components

import com.p4f.jcr.JcrNode
import com.ea.core.page.constants.CqConstants
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Field
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Node

@Node(jcrType = CqConstants.NT_UNSTRUCTURED, extend = JcrNode.class, discriminator = false)
class Text extends JcrNode implements Serializable {
    private static final long serialVersionUID = 1L;

    @Field(jcrName = "text")
    private String text

    @Field(jcrName = "textIsRich")
    private String textIsRich

    String getText() {
        return text
    }

    void setText(String text) {
        this.text = text
    }

    String getTextIsRich() {
        return textIsRich
    }

    void setTextIsRich(String textIsRich) {
        this.textIsRich = textIsRich
    }
}
