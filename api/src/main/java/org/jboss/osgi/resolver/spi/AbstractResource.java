/*
 * JBoss, Home of Professional Open Source
 * Copyright 2005, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.osgi.resolver.spi;

import org.jboss.osgi.resolver.XAttachmentSupport;
import org.jboss.osgi.resolver.XResource;
import org.osgi.framework.resource.Capability;
import org.osgi.framework.resource.Requirement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The abstract implementation of an {@link org.jboss.osgi.resolver.XResource}.
 *
 * This is the resolver representation of a {@link org.osgi.framework.Bundle}.
 * 
 * @author thomas.diesler@jboss.com
 * @since 02-Jul-2010
 */
class AbstractResource extends AbstractElement implements XResource {

    private final Map<String, List<Capability>> capabilities = new HashMap<String, List<Capability>>();
    private final Map<String, List<Requirement>> requirements = new HashMap<String, List<Requirement>>();
    private XAttachmentSupport attachments;

    void addCapability(Capability cap) {
        String namespace = cap.getNamespace();
        getCaplist(namespace).add(cap);
        getCaplist(null).add(cap);
    }

    void addRequirement(Requirement req) {
        String namespace = req.getNamespace();
        getReqlist(namespace).add(req);
        getReqlist(null).add(req);
    }

    @Override
    public List<Capability> getCapabilities(String namespace) {
        return Collections.unmodifiableList(getCaplist(namespace));
    }

    @Override
    public List<Requirement> getRequirements(String namespace) {
        return Collections.unmodifiableList(getReqlist(namespace));
    }

    @Override
    public <T> T addAttachment(Class<T> clazz, T value) {
        if (attachments == null)
            attachments = new AttachmentSupporter();

        return attachments.addAttachment(clazz, value);
    }

    @Override
    public <T> T getAttachment(Class<T> clazz) {
        if (attachments == null)
            return null;

        return attachments.getAttachment(clazz);
    }

    @Override
    public <T> T removeAttachment(Class<T> clazz) {
        if (attachments == null)
            return null;

        return attachments.removeAttachment(clazz);
    }

    private List<Capability> getCaplist(String namespace) {
        List<Capability> caplist = capabilities.get(namespace);
        if (caplist == null) {
            caplist = new ArrayList<Capability>();
            capabilities.put(namespace, caplist);
        }
        return caplist;
    }

    private List<Requirement> getReqlist(String namespace) {
        List<Requirement> reqlist = requirements.get(namespace);
        if (reqlist == null) {
            reqlist = new ArrayList<Requirement>();
            requirements.put(namespace, reqlist);
        }
        return reqlist;
    }
}