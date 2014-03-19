package com.ea.core.page

import com.ea.core.components.Text
import com.ea.core.page.constants.CqConstants
import org.apache.jackrabbit.JcrConstants
import org.apache.jackrabbit.ocm.manager.beanconverter.impl.InlineBeanConverterImpl
import org.apache.jackrabbit.ocm.manager.beanconverter.impl.ReferenceBeanConverterImpl
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Bean
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Node
import com.ea.core.JcrNode

/**
 * Demo of a character page
 */
@Node(jcrType = CqConstants.CQ_PAGECONTENT, jcrMixinTypes = JcrConstants.JCR_CONTENT, extend = JcrNode.class, discriminator = false)
class CharacterPage extends JcrNode {

    @Bean(jcrType = CqConstants.NT_UNSTRUCTURED, jcrOnParentVersion="IGNORE")
    private Text contentText

    // accessor methods must exist for reflection to take place for @Bean
    Text getContentText() {
        return contentText
    }

    void setContentText(Text contentText) {
        this.contentText = contentText
    }
}
