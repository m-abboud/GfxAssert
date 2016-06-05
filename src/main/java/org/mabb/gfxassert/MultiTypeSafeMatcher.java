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
            description.appendText("was a ")
                    .appendText(item.getClass().getName())
                    .appendText(" (")
                    .appendValue(item)
                    .appendText(")");
        } else {
            describeMismatchSafely((T) item, description);
        }
    }
}

