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
package org.apache.jackrabbit.jcr2spi.security;

import org.apache.jackrabbit.spi.ItemId;
import org.apache.jackrabbit.spi.NodeId;
import org.apache.jackrabbit.name.Path;

import javax.jcr.ItemNotFoundException;
import javax.jcr.NoSuchWorkspaceException;
import javax.jcr.RepositoryException;

/**
 * The <code>AccessManager</code> can be queried to determines whether permission
 * is granted to perform a specific action on a specific item.
 */
public interface AccessManager {

    /**
     * predefined action constants
     */
    public String READ_ACTION = "read";
    public String REMOVE_ACTION = "remove";
    public String ADD_NODE_ACTION = "add_node";
    public String SET_PROPERTY_ACTION = "set_property";

    public String[] READ = new String[] {READ_ACTION};
    public String[] REMOVE = new String[] {REMOVE_ACTION};
    // TODO public String[] WRITE = new String[0];

    /**
     * Determines whether the specified <code>permissions</code> are granted
     * on the item with the specified path.
     *
     * @param parentId The node id the existing ancestor.
     * @param relPath The relative path pointing to the non-existing target item.
     * @param actions An array of actions that need to be checked.
     * @return <code>true</code> if the actions are granted; otherwise <code>false</code>
     * @throws ItemNotFoundException if the target item does not exist
     * @throws RepositoryException if another error occurs
     */
    // TODO: method can be removed, if jcr2spi uses spi-ids as well
    boolean isGranted(NodeId parentId, Path relPath, String[] actions) throws ItemNotFoundException, RepositoryException;

    /**
      * Determines whether the specified <code>permissions</code> are granted
      * on the item with the specified path.
      *
      * @param itemId The id of an existing target item.
      * @param actions An array of actions that need to be checked.
      * @return <code>true</code> if the actions are granted; otherwise <code>false</code>
      * @throws ItemNotFoundException if the target item does not exist
      * @throws RepositoryException if another error occurs
      */
     boolean isGranted(ItemId itemId, String[] actions) throws ItemNotFoundException, RepositoryException;


    /**
     * Returns true if the existing item with the given <code>ItemId</code> can
     * be read.
     *
     * @param id The id of an existing target item.
     * @return
     * @throws ItemNotFoundException
     * @throws RepositoryException
     */
    boolean canRead(ItemId id) throws ItemNotFoundException, RepositoryException;

    // TODO need for canWrite(ItemId ?)

    /**
     * Returns true if the existing item with the given <code>ItemId</code> can
     * be removed.
     *
     * @param id The id of an existing target item.
     * @return
     * @throws ItemNotFoundException
     * @throws RepositoryException
     */
    boolean canRemove(ItemId id) throws ItemNotFoundException, RepositoryException;

    /**
     * Determines whether the subject of the current context is granted access
     * to the given workspace.
     *
     * @param workspaceName name of workspace
     * @return <code>true</code> if the subject of the current context is
     * granted access to the given workspace; otherwise <code>false</code>.
     * @throws NoSuchWorkspaceException if a workspace with the given name does not exist.
     * @throws RepositoryException if another error occurs
     */
    boolean canAccess(String workspaceName) throws NoSuchWorkspaceException, RepositoryException;
}
