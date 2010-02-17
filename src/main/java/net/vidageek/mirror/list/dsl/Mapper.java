package net.vidageek.mirror.list.dsl;

/**
 * @author jonasabreu
 * 
 */
public interface Mapper<F, T> {

	public T map(final F element);

}
