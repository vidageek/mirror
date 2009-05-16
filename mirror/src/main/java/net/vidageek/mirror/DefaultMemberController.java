package net.vidageek.mirror;

import java.lang.reflect.AnnotatedElement;

import net.vidageek.mirror.dsl.MemberController;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.reflect.DefaultAllMemberHandler;
import net.vidageek.mirror.reflect.DefaultMemberHandler;
import net.vidageek.mirror.reflect.dsl.AllMemberHandler;
import net.vidageek.mirror.reflect.dsl.MemberHandler;

/**
 * Class to provide reflection features to AccessibleObjects
 * 
 * @author dnfeitosa
 */
public final class DefaultMemberController implements MemberController {

    private final AnnotatedElement member;

    private final ReflectionProvider provider;

    public DefaultMemberController(final ReflectionProvider provider, final AnnotatedElement member) {
        if (member == null) {
            throw new IllegalArgumentException("Argument member cannot be null");
        }
        this.provider = provider;
        this.member = member;
    }

    /**
     * This part of the DSL controls reflection of a list of reflection
     * elements.
     * 
     * @return An object to control reflection of lists of elements.
     */
    public AllMemberHandler reflectAll() {
        return new DefaultAllMemberHandler(provider, member);
    }

    /**
     * This part of the DSL controls reflection of single reflection elements.
     * 
     * @return An object to control reflection of single elements.
     */
    public MemberHandler reflect() {
        return new DefaultMemberHandler(provider, member);
    }
}
