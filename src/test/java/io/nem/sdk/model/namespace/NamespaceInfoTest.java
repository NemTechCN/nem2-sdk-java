/*
 * Copyright 2018 NEM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.nem.sdk.model.namespace;

import io.nem.sdk.model.account.PublicAccount;
import io.nem.sdk.model.blockchain.NetworkType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class NamespaceInfoTest {

    @Test
    void createANamespaceInfoViaConstructor() {
        NamespaceId namespaceId = new NamespaceId(new BigInteger("-8884663987180930485"));
        NamespaceInfo namespaceInfo = new NamespaceInfo(true,
                0,
                "5A3CD9B09CD1E8000159249B",
                NamespaceType.RootNamespace,
                1,
                Arrays.asList(namespaceId),
                new NamespaceId(new BigInteger("0")),
                new PublicAccount("B4F12E7C9F6946091E2CB8B6D3A12B50D17CCBBF646386EA27CE2946A7423DCF", NetworkType.MIJIN_TEST),
                new BigInteger("1"),
                new BigInteger("-1"));

        assertEquals(true, namespaceInfo.isActive());
        assertTrue(namespaceInfo.getIndex() == 0);
        assertEquals("5A3CD9B09CD1E8000159249B", namespaceInfo.getMetaId());
        assertTrue(namespaceInfo.getType() == NamespaceType.RootNamespace);
        assertTrue(namespaceInfo.getDepth() == 1);
        assertEquals(namespaceId, namespaceInfo.getLevels().get(0));
        Assertions.assertEquals(new PublicAccount("B4F12E7C9F6946091E2CB8B6D3A12B50D17CCBBF646386EA27CE2946A7423DCF", NetworkType.MIJIN_TEST), namespaceInfo.getOwner());
        assertEquals(new BigInteger("1"), namespaceInfo.getStartHeight());
        assertEquals(new BigInteger("-1"), namespaceInfo.getEndHeight());
    }

    @Test
    void shouldReturnRootNamespaceId() {
        NamespaceInfo namespaceInfo = createRootNamespaceInfo();
        assertEquals(new BigInteger("-8884663987180930485"), namespaceInfo.getId().getId());
    }

    @Test
    void shouldReturnSubNamespaceId() {
        NamespaceInfo namespaceInfo = createSubNamespaceInfo();
        assertEquals(new BigInteger("-1087871471161192663"), namespaceInfo.getId().getId());
    }

    @Test
    void shouldReturnRootTrueWhenNamespaceInfoIsFromRootNamespace() {
        NamespaceInfo namespaceInfo = createRootNamespaceInfo();
        assertTrue(namespaceInfo.isRoot());
    }

    @Test
    void shouldReturnRootFalseWhenNamespaceInfoIsFromSubNamespace() {
        NamespaceInfo namespaceInfo = createSubNamespaceInfo();
        assertFalse(namespaceInfo.isRoot());
    }

    @Test
    void shouldReturnSubNamespaceFalseWhenNamespaceInfoIsFromRootNamespace() {
        NamespaceInfo namespaceInfo = createRootNamespaceInfo();
        assertFalse(namespaceInfo.isSubnamespace());
    }

    @Test
    void shouldReturnSubNamespaceTrueWhenNamespaceInfoIsFromSubNamespace() {
        NamespaceInfo namespaceInfo = createSubNamespaceInfo();
        assertTrue(namespaceInfo.isSubnamespace());
    }

    @Test
    void shouldReturnParentNamespaceIdWhenNamespaceInfoIsFromSubNamespace() {
        NamespaceInfo namespaceInfo = createSubNamespaceInfo();
        assertEquals(new BigInteger("-3087871471161192663"), namespaceInfo.parentNamespaceId().getId());
    }

    @Test
    void shouldParentNamespaceIdThrowErrorWhenNamespaceInfoIsFromRootNamespace() {
        NamespaceInfo namespaceInfo = createRootNamespaceInfo();
        assertThrows(Error.class, ()->{namespaceInfo.parentNamespaceId();}, "Is A Root Namespace");
    }


    NamespaceInfo createRootNamespaceInfo() {
        return new NamespaceInfo(true,
                0,
                "5A3CD9B09CD1E8000159249B",
                NamespaceType.RootNamespace,
                1,
                Arrays.asList(new NamespaceId(new BigInteger("-8884663987180930485"))),
                new NamespaceId(new BigInteger("0")),
                new PublicAccount("B4F12E7C9F6946091E2CB8B6D3A12B50D17CCBBF646386EA27CE2946A7423DCF", NetworkType.MIJIN_TEST),
                new BigInteger("1"),
                new BigInteger("-1"));
    }

    NamespaceInfo createSubNamespaceInfo() {
        return new NamespaceInfo(true,
                0,
                "5A3CD9B09CD1E8000159249B",
                NamespaceType.SubNamespace,
                1,
                Arrays.asList(new NamespaceId(new BigInteger("-3087871471161192663")), new NamespaceId(new BigInteger("-1087871471161192663"))),
                new NamespaceId(new BigInteger("-3087871471161192663")),
                new PublicAccount("B4F12E7C9F6946091E2CB8B6D3A12B50D17CCBBF646386EA27CE2946A7423DCF", NetworkType.MIJIN_TEST),
                new BigInteger("1"),
                new BigInteger("-1"));
    }
}

