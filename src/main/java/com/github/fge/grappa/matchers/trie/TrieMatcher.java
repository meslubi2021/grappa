/*
 * Copyright (C) 2014 Francis Galiegue <fgaliegue@gmail.com>
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

package com.github.fge.grappa.matchers.trie;

import com.github.fge.grappa.matchers.MatcherType;
import com.github.fge.grappa.matchers.base.AbstractMatcher;
import com.github.fge.grappa.parsers.BaseParser;
import com.google.common.annotations.Beta;
import com.github.fge.grappa.run.context.MatcherContext;

import javax.annotation.concurrent.Immutable;
import java.util.Collection;
import java.util.Objects;

/**
 * A matcher for a fixed number of strings, using a <a
 * href="https://en.wikipedia.org/wiki/Trie">trie</a>
 *
 * <p>This is called by {@link BaseParser#longestString(Collection)} and {@link
 * BaseParser#longestStringIgnoreCase(Collection)}.</p>
 *
 * @since 1.0.0-beta.6
 */
@Immutable
@Beta
public final class TrieMatcher
    extends AbstractMatcher
{
    private final Trie trie;

    public TrieMatcher(final Trie trie)
    {
        super("trie(" + Objects.requireNonNull(trie).getNrWords()
            + " strings)");
        this.trie = trie;
    }

    @Override
    public MatcherType getType()
    {
        return MatcherType.TERMINAL;
    }

    /**
     * Tries a match on the given MatcherContext.
     *
     * @param context the MatcherContext
     * @return true if the match was successful
     */
    @Override
    public <V> boolean match(final MatcherContext<V> context)
    {
        /*
         * Since the trie knows about the length of its possible longest match,
         * extract that many characters from the buffer. Remind that .extract()
         * will adjust illegal indices automatically.
         */
        final int maxLength = trie.getMaxLength();
        final int index = context.getCurrentIndex();
        final String input = context.getInputBuffer()
            .extract(index, index + maxLength);

        /*
         * We now just have to trie and search... (pun intended)
         */
        final int ret = trie.search(input, false);
        if (ret == -1)
            return false;

        /*
         * and since the result, when positive, is the length of the match,
         * advance the index in the buffer by that many positions.
         */
        context.advanceIndex(ret);
        return true;
    }
}
