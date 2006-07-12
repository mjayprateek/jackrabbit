/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.jackrabbit.jcr2spi.operation;

import org.apache.jackrabbit.spi.NodeId;

import javax.jcr.RepositoryException;
import javax.jcr.AccessDeniedException;
import javax.jcr.ItemExistsException;
import javax.jcr.UnsupportedRepositoryOperationException;
import javax.jcr.version.VersionException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.NoSuchNodeTypeException;

/**
 * <code>ResolveMergeConflict</code>...
 */
public class ResolveMergeConflict extends AbstractOperation {

    private final NodeId nodeId;
    private final NodeId versionId;
    private final boolean resolveDone;

    private ResolveMergeConflict(NodeId nodeId, NodeId versionId, boolean resolveDone) {
        this.nodeId = nodeId;
        this.versionId = versionId;
        this.resolveDone = resolveDone;

        addAffectedItemId(nodeId);
        addAffectedItemId(versionId);
    }

    //----------------------------------------------------------< Operation >---
    /**
     * @see Operation#accept(OperationVisitor)
     */
    public void accept(OperationVisitor visitor) throws RepositoryException, ConstraintViolationException, AccessDeniedException, ItemExistsException, NoSuchNodeTypeException, UnsupportedRepositoryOperationException, VersionException {
        visitor.visit(this);
    }

    //----------------------------------------< Access Operation Parameters >---
    public NodeId getNodeId() {
        return nodeId;
    }

    public NodeId getVersionId() {
        return versionId;
    }

    public boolean resolveDone() {
        return resolveDone;
    }

    //------------------------------------------------------------< Factory >---
    /**
     *
     * @param nodeId
     * @param versionId
     * @param resolveDone
     */
    public static Operation create(NodeId nodeId, NodeId versionId, boolean resolveDone) {
        ResolveMergeConflict up = new ResolveMergeConflict(nodeId, versionId, resolveDone);
        return up;
    }
}