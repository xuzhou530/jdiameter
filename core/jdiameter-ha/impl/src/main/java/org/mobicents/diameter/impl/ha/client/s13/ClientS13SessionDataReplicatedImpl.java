/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
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

package org.mobicents.diameter.impl.ha.client.s13;

import org.jboss.cache.Fqn;
import org.jdiameter.api.s13.ClientS13Session;
import org.jdiameter.client.api.IContainer;
import org.jdiameter.client.impl.app.s13.IClientS13SessionData;
import org.jdiameter.common.api.app.s13.S13SessionState;
import org.mobicents.cluster.MobicentsCluster;
import org.mobicents.diameter.impl.ha.common.s13.S13SessionDataReplicatedImpl;
import org.mobicents.diameter.impl.ha.data.ReplicatedSessionDatasource;

/**
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 */
public class ClientS13SessionDataReplicatedImpl extends S13SessionDataReplicatedImpl implements IClientS13SessionData {

	/**
	 * @param nodeFqn
	 * @param mobicentsCluster
	 * @param iface
	 */
	public ClientS13SessionDataReplicatedImpl(Fqn<?> nodeFqn, MobicentsCluster mobicentsCluster, IContainer container) {
		super(nodeFqn, mobicentsCluster, container);

		if (super.create()) {
			setAppSessionIface(this, ClientS13Session.class);
			setS13SessionState(S13SessionState.IDLE);
		}
	}

	/**
	 * @param sessionId
	 * @param mobicentsCluster
	 * @param iface
	 */
	public ClientS13SessionDataReplicatedImpl(String sessionId, MobicentsCluster mobicentsCluster, IContainer container) {
		this(Fqn.fromRelativeElements(ReplicatedSessionDatasource.SESSIONS_FQN, sessionId), mobicentsCluster, container);
	}

}
