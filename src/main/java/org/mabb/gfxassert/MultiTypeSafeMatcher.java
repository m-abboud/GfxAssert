/*
 * Copyright (C) Matthew Abboud 2016
 *
 * GfxAssert is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GfxAssert is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GfxAssert. If not, see <http://www.gnu.org/licenses/>.
 */

package org.mabb.gfxassert;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.internal.ReflectiveTypeFinder;

/**
 * Based off hamcrest's TypeSafeMatcher but for multiple types, dunno if usefull actually
 * was thinking for graphics type things so could have same matcher for images and jpanels or something
 */
public abstract class MultiTypeSafeMatcher<T> extends BaseMatcher<T> {
    private static final ReflectiveTypeFinder TYPE_FINDER = new ReflectiveTypeFinder("matchesSafelyMultiType", 1, 0);

    final private Class<?> expectedMainType;
    private T convertedType;

    protected MultiTypeSafeMatcher() {
        this(TYPE_FINDER);
    }

    protected MultiTypeSafeMatcher(Class<?> expectedType) {
        this.expectedMainType = expectedType;
    }

    protected MultiTypeSafeMatcher(ReflectiveTypeFinder typeFinder) {
        this.expectedMainType = typeFinder.findExpectedType(getClass());
    }

    protected abstract Class[] expectedTypes();

    protected abstract boolean matchesSafely(T item);

    protected abstract T convertToMainType(Object item);

    private boolean matchesSafelyMultiType(Object item) {
        return matchesSafely(getConvertedType(item));
    }

    protected void describeMismatchSafely(T item, Description mismatchDescription) {
        super.describeMismatch(item, mismatchDescription);
    }

    @SuppressWarnings({"unchecked"})
    public final boolean matches(Object item) {
        if (isInstanceOfConvertableTypes(item))
            item = convertToMainType(item);

        return item != null
                && expectedMainType.isInstance(item)
                && matchesSafely((T) item);
    }

    protected T getConvertedType(Object item) {
        if (convertedType != null)
            convertedType = convertToMainType(item);

        return convertedType;
    }

    private boolean isInstanceOfConvertableTypes(Object item) {
        for (Class typeOn : expectedTypes())
            if (typeOn.isInstance(item))
                return true;

        return false;
    }

    @SuppressWarnings("unchecked")
    final public void describeMismatch(Object item, Description description) {
        if (item == null) {
            super.describeMismatch(item, description);
        } else if (!isInstanceOfConvertableTypes(item)) {
            describeItemMismatch(item, description);
        } else {
            describeMismatchSafely((T) item, description);
        }
    }

    protected void describeItemMismatch(Object item, Description description) {
        description.appendText("was a ")
                .appendText(item.getClass().getName())
                .appendText(" (")
                .appendValue(item)
                .appendText(")");
    }
}

