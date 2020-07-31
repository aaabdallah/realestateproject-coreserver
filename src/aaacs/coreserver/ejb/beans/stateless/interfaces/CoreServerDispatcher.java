package aaacs.coreserver.ejb.beans.stateless.interfaces;

import javax.ejb.Remote;

import aaacs.coreserver.commons.communication.ActionRequest;
import aaacs.coreserver.commons.communication.ActionResponse;

@Remote
public interface CoreServerDispatcher
{
	public ActionResponse executeAction(ActionRequest request);
}
