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
		Preconditions.checkArgument(member != null, "member cannot be null");
		this.provider = provider;
		this.member = member;
	}

	public AllMemberHandler reflectAll() {
		return new DefaultAllMemberHandler(provider, member);
	}

	public MemberHandler reflect() {
		return new DefaultMemberHandler(provider, member);
	}
}
