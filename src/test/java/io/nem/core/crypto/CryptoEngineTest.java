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

package io.nem.core.crypto;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Test;

public abstract class CryptoEngineTest {

    @Test
    public void canGetCurve() {
        // Act:
        final Curve curve = this.getCryptoEngine().getCurve();

        // Assert:
        Assert.assertThat(curve, IsInstanceOf.instanceOf(Curve.class));
    }

    @Test
    public void canCreateDsaSigner() {
        // Act:
        final CryptoEngine engine = this.getCryptoEngine();
        final DsaSigner signer = engine.createDsaSigner(KeyPair.random(engine));

        // Assert:
        Assert.assertThat(signer, IsInstanceOf.instanceOf(DsaSigner.class));
    }

    @Test
    public void canCreateKeyGenerator() {
        // Act:
        final KeyGenerator keyGenerator = this.getCryptoEngine().createKeyGenerator();

        // Assert:
        Assert.assertThat(keyGenerator, IsInstanceOf.instanceOf(KeyGenerator.class));
    }

    @Test
    public void canCreateKeyAnalyzer() {
        // Act:
        final KeyAnalyzer keyAnalyzer = this.getCryptoEngine().createKeyAnalyzer();

        // Assert:
        Assert.assertThat(keyAnalyzer, IsInstanceOf.instanceOf(KeyAnalyzer.class));
    }

    @Test
    public void canCreateBlockCipher() {
        // Act:
        final CryptoEngine engine = this.getCryptoEngine();
        final BlockCipher blockCipher = engine.createBlockCipher(KeyPair.random(engine), KeyPair.random(engine));

        // Assert:
        Assert.assertThat(blockCipher, IsInstanceOf.instanceOf(BlockCipher.class));
    }

    protected abstract CryptoEngine getCryptoEngine();
}
