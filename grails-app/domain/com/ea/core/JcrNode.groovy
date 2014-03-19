package com.ea.core

import com.ea.core.page.constants.CqConstants
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Field
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Node

/**
 * Base representation of a JCR Node containing common attributes among
 * all nodes.  Probably contains most of the "jcr:*" attributes.
 */
@Node(isAbstract = true, discriminator = false)
class JcrNode {
    @Field(path = true)
    String path

    @Field(jcrName = CqConstants.JCR_UUID)
    String uuid

    @Field(jcrName = CqConstants.JCR_TITLE)
    String jcrTitle

    @Field(jcrName = CqConstants.JCR_CREATED)
    String jcrCreated

    @Field(jcrName = CqConstants.JCR_CREATEDBY)
    String jcrCreatedBy

    @Field(jcrName = CqConstants.JCR_PRIMARY_TYPE)
    String jcrPrimaryType

    @Field(jcrName = CqConstants.JCR_DESCRIPTION)
    String jcrDescription

    @Field(jcrName = CqConstants.JCR_LASTMODIFIED)
    String jcrLastModified

    @Field(jcrName = CqConstants.JCR_LASTMODIFIEDBY)
    String jcrLastModifiedBy

    @Field(jcrName = CqConstants.SLING_RESOURCETYPE)
    String slingResourceType
}
