package net.vidageek.mirror.matcher;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;

import org.junit.Test;

public class GetterMatcherTest {

	@Test
	public void testGetter() throws Exception {
		Method method = Bean.class.getMethod("isValid");
		assertTrue(new GetterMatcher().accepts(method));
		method = Bean.class.getMethod("getSomeFlag");
		assertTrue(new GetterMatcher().accepts(method));
		method = Bean.class.getMethod("getName");
		assertTrue(new GetterMatcher().accepts(method));
	}

	@Test
	public void testNotGetter() throws Exception {
		Method method = Bean.class.getMethod("getSomething");
		assertFalse(new GetterMatcher().accepts(method));
		method = Bean.class.getMethod("getSomeOther", String.class);
		assertFalse(new GetterMatcher().accepts(method));
		method = Bean.class.getMethod("getSomeStrange", String.class);
		assertFalse(new GetterMatcher().accepts(method));
		method = Bean.class.getMethod("setValid", boolean.class);
		assertFalse(new GetterMatcher().accepts(method));
		method = Bean.class.getMethod("isSomeOtherFlag");
		assertFalse(new GetterMatcher().accepts(method));
	}

	private static class Bean {
		private boolean valid;
		private Boolean someFlag;
		private String name;

		public Boolean isSomeOtherFlag() {
			return someFlag;
		}

		public boolean isValid() {
			return valid;
		}

		public void setValid(boolean valid) {
			this.valid = valid;
		}

		public Boolean getSomeFlag() {
			return someFlag;
		}

		public void setSomeFlag(Boolean someFlag) {
			this.someFlag = someFlag;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void getSomething() {
		}

		public String getSomeOther(String thing) {
			return thing;
		}

		public void getSomeStrange(String x) {

		}

	}
}
