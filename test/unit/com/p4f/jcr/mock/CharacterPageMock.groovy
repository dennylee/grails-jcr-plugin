package com.p4f.jcr.mock

import com.ea.core.page.constants.CqConstants
import com.p4f.jcr.JcrNode
import com.p4f.jcr.components.Text
import org.apache.jackrabbit.JcrConstants
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Bean
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Node

@Node(jcrType = CqConstants.CQ_PAGECONTENT, jcrMixinTypes = JcrConstants.JCR_CONTENT, extend = JcrNode.class, discriminator = false)
class CharacterPageMock extends JcrNode implements Serializable{
    private static final long serialVersionUID = 1L;

    @Bean(jcrType = CqConstants.NT_UNSTRUCTURED, jcrOnParentVersion="IGNORE")
    private Text contentText

    // accessor methods must exist for reflection to take place for @Bean
    Text getContentText() {
        return contentText
    }

    void setContentText(Text contentText) {
        this.contentText = contentText
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof CharacterPageMock)) return false

        CharacterPageMock that = (CharacterPageMock) o

        if (contentText != that.contentText) return false

        return true
    }

    int hashCode() {
        return contentText.hashCode()
    }
}
