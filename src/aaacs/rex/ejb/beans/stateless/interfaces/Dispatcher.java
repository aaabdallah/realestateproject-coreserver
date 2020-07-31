package aaacs.rex.ejb.beans.stateless.interfaces;

import javax.ejb.Remote;

import aaacs.coreserver.commons.communication.ActionRequest;
import aaacs.coreserver.commons.communication.ActionResponse;

// Note: it does NOT extend CoreServerDispatcher in order to minimize
// what needs to be included with *client* applications.
@Remote
public interface Dispatcher
{
	public ActionResponse executeAction(ActionRequest request);
}
