/* *************************************************************************** 
 * THIS FILE WAS AUTOMATICALLY GENERATED. DO NOT MODIFY IT DIRECTLY OR ELSE  * 
 * YOUR CHANGES WILL BE OVERWRITTEN AND LOST.                                * 
 * Copyright Ahmed A. Abd-Allah, 2006                                        * 
 * ***************************************************************************/

package aaacs.rex.ejb.beans.stateless.interfaces;

import java.util.List;
import javax.ejb.Local;
import aaacs.coreserver.commons.communication.ActionRequest;
import aaacs.coreserver.commons.communication.ActionResponse;
import aaacs.coreserver.commons.communication.ErrorReport;
import aaacs.coreserver.commons.exceptions.CSWrapperException;

@Local
public interface CoreServerBridge
{
	/*************************************************************************
	 * Method: "action.LookupTableFromCache", v1 (2 tasks)
	 * Task 1: Undetermined
	 *   Input: String action.LookupTableFromCache.TableName (tableName)
	 *   Input: Map<String, Object> action.LookupTableFromCache.SearchParameters (searchParameters)
	 * Task 2: Custom code
	 *   Output: Map action.LookupTableFromCache.Table (results)
	 *************************************************************************/
	public List<ErrorReport> lookupTableFromCache(ActionRequest request, ActionResponse response)
		throws CSWrapperException;

	/*************************************************************************
	 * Method: "action.LookupTableColumnFromCache", v1 (2 tasks)
	 * Task 1: Undetermined
	 *   Input: String action.LookupTableColumnFromCache.TableName (tableName)
	 *   Input: String action.LookupTableColumnFromCache.ColumnName (columnName)
	 *   Input: Map<String, Object> action.LookupTableColumnFromCache.SearchParameters (searchParameters)
	 * Task 2: Custom code
	 *   Output: Array action.LookupTableColumnFromCache.Column (results)
	 *************************************************************************/
	public List<ErrorReport> lookupTableColumnFromCache(ActionRequest request, ActionResponse response)
		throws CSWrapperException;

}

