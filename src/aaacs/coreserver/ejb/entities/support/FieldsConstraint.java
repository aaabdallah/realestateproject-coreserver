/*
 * Copyright (C) National Biorepository Project, KFSHRC, 2003
 * Created on Oct 27, 2003 by aaa
 *
 */
package aaacs.coreserver.ejb.entities.support;

import java.util.Hashtable;
import java.util.Stack;

/**
 * @author aaa
 * Created on Oct 27, 2003 at 4:44:51 PM.
 *
 * <p>A very primitive class for expressing simple constraints on fields. This is
 * used within the code that deals with Permissions. Note that the operators
 * allowed in this class make some very strict assumptions about the types of
 * the operands.</p>
 * <p>Important: after allowing the database to include null values, this
 * class needs to be updated to allow checking against null and short-circuiting.
 * </p>
 */
public class FieldsConstraint
{
	// ----- Static members ---------------------------------------------------
	// This was written before Java introduced enums...
	public static final String _EQ_ = "_eq_";
	public static final String _GT_ = "_gt_";
	public static final String _GTE_ = "_gte_";
	public static final String _LT_ = "_lt_";
	public static final String _LTE_ = "_lte_";

	public static final String _AND_ = "_and_";
	public static final String _OR_ = "_or_";
	public static final String _NOT_ = "_not_";

	// ----- Instance members -------------------------------------------------
	private String constraint; // The constraint is expressed in POSTFIX.

	// Example: "admin 33 _lt_ dom 44 _gt_ _and_";

	/**
	 * 
	 */
	public FieldsConstraint()
	{
		this("");
	}

	public FieldsConstraint(String inConstraint)
	{
		constraint = inConstraint;	
	}
	
	/**
	 * @return
	 */
	public String getConstraint()
	{
		return constraint;
	}

	/**
	 * @param string
	 */
	public void setConstraint(String string)
	{
		if (string != null)
			constraint = string.trim();
	}
	
	public void clear()
	{
		constraint = "";
	}

	public String toString()
	{
		return constraint;
	}

	public boolean isEmpty()
	{
		return constraint.equals("");
	}
	
	public boolean isValid()
	{
		if (isEmpty())
			return false;
		return true;
	}
	
	public boolean evaluate(Hashtable<String, Object> parameters)
	{
		Stack<Object> stack;
		int i = 0;
		Object x, y;
		
		if (constraint == null)
			return false;
		constraint = constraint.trim();
		if (isEmpty())
			return false;

		String tokens[] = constraint.split("[\\s]+");
		if (tokens.length <= 0)
			return false;

		try // remember: the constraint is supposed to be a POSTFIX expression.
		{
			stack = new Stack<Object>();
			while (i < tokens.length)
			{
				if (isRecognizedUnaryFunction(tokens[i]))
				{
					// pop y (top of stack)
					y = stack.pop();					
					// check if y references a parameter
					if (parameters != null && parameters.containsKey(y))
						y = parameters.get(y);

					stack.push(new Boolean(applyUnaryFunction(tokens[i], y)));
				}
				else if (isRecognizedBinaryFunction(tokens[i]))
				{
					// pop y (top of stack)
					y = stack.pop();
					// check if y references a parameter
					if (parameters != null && parameters.containsKey(y))
						y = parameters.get(y);

					// pop x (next operand on stack)
					x = stack.pop();
					// check if x references a parameter
					if (parameters != null && parameters.containsKey(x))
						x = parameters.get(x);

					stack.push(new Boolean(applyBinaryFunction(tokens[i], x, y)));
				}
				else
				{
					stack.push(tokens[i]);
				}
				i++;
			}
			
			// There should only be ONE element left on the stack now: a single Boolean.
			if (stack.size() == 1)
			{
				Object result = stack.pop();
				if (result.getClass().equals(Boolean.class))
				{
					return ((Boolean) result).booleanValue();
				}
			}
			return false;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	private boolean isRecognizedUnaryFunction(String token)
	{
		if (token.equalsIgnoreCase("not"))
			return true;
		return false;
	}

	private boolean isRecognizedBinaryFunction(String token)
	{
		if (token.equalsIgnoreCase(_AND_) || token.equalsIgnoreCase(_OR_) ||
			token.equalsIgnoreCase(_EQ_) ||
			token.equalsIgnoreCase(_GT_) || token.equalsIgnoreCase(_GTE_) ||
			token.equalsIgnoreCase(_LT_) || token.equalsIgnoreCase(_LTE_))
			return true;
		return false;
	}

	private boolean applyUnaryFunction(String token, Object y)
	{
		if (token.equalsIgnoreCase(_NOT_))
			return !((Boolean) y).booleanValue();  

		throw new UnsupportedOperationException("Unrecognized unary function");
	}

	private boolean applyBinaryFunction(String token, Object x, Object y)
	{
		if (token.equalsIgnoreCase(_AND_))
			return ((Boolean) x).booleanValue() && ((Boolean) y).booleanValue();  
		else if (token.equalsIgnoreCase(_OR_))
			return ((Boolean) x).booleanValue() || ((Boolean) y).booleanValue();
		else if (token.equalsIgnoreCase(_EQ_))
			return Double.valueOf(x.toString()).doubleValue() == Double.valueOf(y.toString()).doubleValue();
		else if (token.equalsIgnoreCase(_GT_))
			return Double.valueOf(x.toString()).doubleValue() > Double.valueOf(y.toString()).doubleValue();
		else if (token.equalsIgnoreCase(_GTE_))
			return Double.valueOf(x.toString()).doubleValue() >= Double.valueOf(y.toString()).doubleValue();
		else if (token.equalsIgnoreCase(_LT_))
			return Double.valueOf(x.toString()).doubleValue() < Double.valueOf(y.toString()).doubleValue();
		else if (token.equalsIgnoreCase(_LTE_))
			return Double.valueOf(x.toString()).doubleValue() <= Double.valueOf(y.toString()).doubleValue();

		throw new UnsupportedOperationException("Unrecognized binary function");
	}
}
