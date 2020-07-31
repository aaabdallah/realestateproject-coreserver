/* *************************************************************************** 
 * THIS FILE WAS AUTOMATICALLY GENERATED. DO NOT MODIFY IT DIRECTLY OR ELSE  * 
 * YOUR CHANGES WILL BE OVERWRITTEN AND LOST.                                * 
 * Copyright Ahmed A. Abd-Allah, 2006                                        * 
 * ***************************************************************************/

package aaacs.dtos;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;

import aaacs.coreserver.ejb.entities.BaseEntity;
import aaacs.coreserver.ejb.entities.support.FieldValidator;
import aaacs.coreserver.exceptions.CoreServerException;


public class RoleDTO implements Serializable
{
	/**
	 * Serialization Version Number
	 */
	private static final long serialVersionUID = 1000L;

	// ----- Instance Members -----
	private String name;
	private String description;

	public RoleDTO() {}

	// ----- Getters & Setters -----
	public String getName()
	{
		return name;
	}
	public void setName(String inputParameter)
	{
		modify();
		name = inputParameter;
	}

	public String getDescription()
	{
		return description;
	}
	public void setDescription(String inputParameter)
	{
		modify();
		description = inputParameter;
	}

}

