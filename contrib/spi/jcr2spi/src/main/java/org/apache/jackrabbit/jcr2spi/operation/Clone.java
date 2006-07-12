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

import org.apache.jackrabbit.jcr2spi.state.ItemStateValidator;
import org.apache.jackrabbit.jcr2spi.ManagerProvider;
import org.apache.jackrabbit.name.Path;

import javax.jcr.RepositoryException;
import javax.jcr.ItemExistsException;
import javax.jcr.AccessDeniedException;
import javax.jcr.NoSuchWorkspaceException;
import javax.jcr.UnsupportedRepositoryOperationException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.lock.LockException;
import javax.jcr.version.VersionException;

/**
 * <code>Clone</code>...
 */
public class Clone extends AbstractCopy {

    private final boolean removeExisting;

    private Clone(Path srcPath, Path destPath, String srcWorkspaceName,
                  boolean removeExisting, ManagerProvider srcMgrProvider,
                  ItemStateValidator validator)
        throws RepositoryException {
        super(srcPath, destPath, srcWorkspaceName, srcMgrProvider, validator);
        this.removeExisting = removeExisting;
    }

    //----------------------------------------------------------< Operation >---
    /**
     *
     * @param visitor
     */
    public void accept(OperationVisitor visitor) throws NoSuchWorkspaceException, LockException, ConstraintViolationException, AccessDeniedException, ItemExistsException, UnsupportedRepositoryOperationException, VersionException, RepositoryException {
        visitor.visit(this);
    }

    //----------------------------------------< Access Operation Parameters >---
    public boolean isRemoveExisting() {
        return removeExisting;
    }

    //------------------------------------------------------------< Factory >---

    public static Operation create(Path srcPath, Path destPath,
                                   String srcWorkspaceName, boolean removeExisting,
                                   ManagerProvider srcMgrProvider,
                                   ItemStateValidator validator)
        throws RepositoryException, ConstraintViolationException, AccessDeniedException,
        ItemExistsException, VersionException {

        Clone cl = new Clone(srcPath, destPath, srcWorkspaceName, removeExisting, srcMgrProvider, validator);
        return cl;
    }
}