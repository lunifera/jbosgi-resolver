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
package org.jboss.osgi.resolver.v2.spi;

import org.jboss.osgi.resolver.v2.XIdentityCapability;
import org.osgi.framework.Version;
import org.osgi.framework.wiring.BundleRevision;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.osgi.framework.resource.ResourceConstants.IDENTITY_NAMESPACE;
import static org.osgi.framework.resource.ResourceConstants.IDENTITY_TYPE_ATTRIBUTE;
import static org.osgi.framework.resource.ResourceConstants.IDENTITY_VERSION_ATTRIBUTE;

/**
 * The abstract implementation of a {@link XIdentityCapability}.
 *
 * @author thomas.diesler@jboss.com
 * @since 02-Jul-2010
 */
public class AbstractIdentityCapability extends AbstractBundleCapability implements XIdentityCapability {

    private final String symbolicName;
    private final Version version;
    private final String type;

    protected AbstractIdentityCapability(BundleRevision brev, Map<String, Object> atts, Map<String, String> dirs) {
        super(brev, IDENTITY_NAMESPACE, atts, dirs);
        this.symbolicName = (String) atts.get(IDENTITY_NAMESPACE);
        this.version = (Version) atts.get(IDENTITY_VERSION_ATTRIBUTE);
        this.type = (String) atts.get(IDENTITY_TYPE_ATTRIBUTE);
    }

    @Override
    protected List<String> getMandatoryAttributes() {
        return Arrays.asList(IDENTITY_NAMESPACE, IDENTITY_VERSION_ATTRIBUTE, IDENTITY_TYPE_ATTRIBUTE);
    }

    @Override
    public String getSymbolicName() {
        return symbolicName;
    }

    @Override
    public Version getVersion() {
        return version;
    }

    @Override
    public String getType() {
        return type;
    }
}