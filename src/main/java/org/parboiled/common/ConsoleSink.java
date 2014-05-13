/*
 * Copyright (C) 2009-2011 Mathias Doenitz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.parboiled.common;

import com.github.parboiled1.grappa.cleanup.WillBeRemoved;
import com.github.parboiled1.grappa.misc.SinkAdapter;
import com.github.parboiled1.grappa.misc.SystemOutCharSink;

/**
 * Deprecated!
 *
 * @deprecated use {@link SystemOutCharSink} instead
 *
 * @see SinkAdapter
 */
@Deprecated
@WillBeRemoved(version = "1.1")
public class ConsoleSink
    implements Sink<String>
{
    @Override
    public void receive(final String value)
    {
        System.out.print(value);
    }
}
