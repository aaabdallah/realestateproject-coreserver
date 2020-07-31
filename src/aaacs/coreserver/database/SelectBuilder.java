package aaacs.coreserver.database;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

public class SelectBuilder
{
	private StringBuffer select, where, group, having, order;
	private int limit = -1, offset = -1;

	// The 'from' element needs care to avoid duplicate tables, so simple searching is important. 
	private ArrayList<Object> from;

	public SelectBuilder()
	{
	}

	public SelectBuilder(String s, String f, String w, String g, String h, String o, int limit, int offset)
	{
		addSelect(s);
		addFrom(f);
		addWhere(w, true, false);
		addGroup(g);
		addHaving(h, true);
		addOrder(o);
		addLimit(limit);
		addOffset(offset);
	}

	public SelectBuilder(String s, SelectBuilder f, String w, String g, String h, String o, int limit, int offset)
	{
		addSelect(s);
		addFrom(f);
		addWhere(w, true, false);
		addGroup(g);
		addHaving(h, true);
		addOrder(o);
		addLimit(limit);
		addOffset(offset);
	}

	public void addSelect(String s)
	{
		if (s != null)
		{
			if (select == null)
				select = new StringBuffer("SELECT ");
			else
				select.append(", ");
			select.append(s);
		}
	}

	public void addFrom(String f)
	{
		addFrom(f, false);
	}

	public void addFrom(String f, boolean forceDuplicate)
	{
		if (f != null)
		{
			if (from == null)
				from = new ArrayList<Object>();

			StringTokenizer tokenizer = new StringTokenizer(f, ",");
			while (tokenizer.hasMoreTokens())
			{
				String table = tokenizer.nextToken();
				if (forceDuplicate || !from.contains(table.trim()))
					from.add(table.trim());
			}
		}
	}
	
	public void addFrom(SelectBuilder s)
	{
		if (s != null)
		{
			if (from == null)
				from = new ArrayList<Object>();
			//from.add("( " + s.toString() + " )");
			from.add(s);
		}		
	}

	public void addWhere(String w, boolean and)
	{
		addWhere(w, and, false);
	}

	public void addWhere(String w, boolean and, boolean recursive)
	{
		if (w != null)
		{
			if (where == null)
				where = new StringBuffer(" WHERE ");
			else
				if (and) where.append(" AND "); 
				else where.append(" OR ");
			where.append(w);
			
			if (from != null && from.size() > 0)
			{
				for (int j=0; j<from.size(); j++)
				{
					if (from.get(j) instanceof SelectBuilder)
						((SelectBuilder) from.get(j)).addWhere(w, and, recursive);
				}
			}
		}
	}

	public void addGroup(String g)
	{
		if (g != null)
		{
			if (group == null)
				group = new StringBuffer(" GROUP BY ");
			else
				group.append(", ");
			group.append(g);
		}
	}

	public void addHaving(String h, boolean and)
	{
		if (h != null)
		{
			if (having == null)
				having = new StringBuffer(" HAVING ");
			else
				if (and) having.append(" AND "); 
				else having.append(" OR ");
			having.append(h);
		}
	}

	public void addOrder(String o)
	{
		if (o != null)
		{
			if (order == null)
				order = new StringBuffer(" ORDER BY ");
			else
				order.append(", ");
			order.append(o);
		}
	}
	
	/** A limit of -1 means no limit */
	public void addLimit(int limit)
	{
		this.limit = limit;
	}
	
	public void addOffset(int offset)
	{
		if (offset >= 0)
			this.offset = offset;
	}
	
	public String toString()
	{
		StringBuffer fromAsString = new StringBuffer("");
		
		if (from != null && from.size() > 0)
		{
			fromAsString.append(" FROM ");
			
			for (int j=0; j<from.size(); j++)
			{
				if (j>0) fromAsString.append(", ");
				
				if (from.get(j) instanceof SelectBuilder)
					fromAsString.append("( ").append(((SelectBuilder) from.get(j)).toString()).append(" )");
				else
					fromAsString.append((String) from.get(j));
			}
		}
		
		return 
			(select != null ? select.toString() : "") +
			fromAsString.toString() +
			(where != null ? where.toString() : "") +
			(group != null ? group.toString() : "") +
			(having != null ? having.toString() : "") +
			(order != null ? order.toString() : "") +
			(limit >= 0 ? " LIMIT " + limit : "") +
			(offset >= 0 ? " OFFSET " + offset : "");
	}
	
	private String extractName(String compoundName, boolean stripSurroundingQuotes)
	{
		if (compoundName == null) return null;
		compoundName = compoundName.trim();
		
		// return the alias, if any
		int i = compoundName.toLowerCase().indexOf(" as ");
		if (i > 0)
			compoundName = compoundName.substring(i + 4);
		
		if (stripSurroundingQuotes &&
			compoundName.charAt(0) == '"' && compoundName.charAt(compoundName.length() - 1) == '"')
				compoundName = compoundName.substring(1, compoundName.length()-1);

		return compoundName;
	}

	public List<String> getColumnNames(boolean stripSurroundingQuotes)
	{
		if (select == null) return null;
		
		Vector<String> columnNames = new Vector<String>();
		
		// offset due to "SELECT "
		int i = 7, j = 7;
		while (true)
		{
			j = select.indexOf(",", i);
			if (j < 0)
			{
				if (i < select.length())
					columnNames.add(extractName(select.substring(i, select.length()).trim(), stripSurroundingQuotes));
				break;
			}
			String name = extractName(select.substring(i, j).trim(), stripSurroundingQuotes);
			columnNames.add(name);
			i = j + 1;
		}
		return columnNames;
	}

	public static void main(String args[])
	{
		SelectBuilder query = new SelectBuilder("primarykey, name", "bttlrs", "name='CCE'", "name", "name='CCE'", "name", 10, -1);
		query.addFrom("outofstockevents");
		SelectBuilder query2 = new SelectBuilder("primarykey AS pk, name", "bttlrs", "name='CCE'", "name", "name='CCE'", "name", 15, 10);
		query2.addFrom(query);
		query2.addFrom("bttlrs, bttlrs");
		query2.addFrom("bttlrs, outofstockevents");
		query2.addFrom("bttlrs, outofstockevents, bttlrs");
		query2.addWhere("name != 'NONE'", true, true);
		System.gc();
		System.out.println(query2);
		
		System.out.println(query2.getColumnNames(true).toString());
	}
}
