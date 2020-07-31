package aaacs.coreserver.ejb.beans.stateless;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.transaction.UserTransaction;

import aaacs.coreserver.commons.communication.ActionRequest;
import aaacs.coreserver.commons.communication.ActionResponse;
import aaacs.coreserver.commons.communication.ErrorReport;
import aaacs.coreserver.commons.exceptions.CoreServerException;
import aaacs.coreserver.ejb.beans.BaseSessionBean;
import aaacs.coreserver.ejb.beans.stateless.interfaces.CoreServerDispatcher;

@Stateless
@Remote(CoreServerDispatcher.class)
@TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
abstract public class CoreServerDispatcherBean extends BaseSessionBean implements CoreServerDispatcher
{
	// ----- Instance members -------------------------------------------------
	@Resource private UserTransaction userTran;

	abstract protected boolean actionRequiresLoggedInUser(ActionRequest request);
	abstract protected List<ErrorReport> dispatch(ActionRequest request, 
		ActionResponse response)
		throws CoreServerException;

	/**
	 * Executes any action requested and returns a response.
	 * 
	 * Why use ActionRequest/ActionResponse? I thought about that...
	 * 
	 * ActionResponse/ActionRequest:
	 * + supports future non-RMI interfaces
	 * - requires dynamic type checking
	 * + easily send back lots of parameters
	 * + uniform interface for invoking methods (ARp execAction(ARq))
	 * ++ stable interface across version or method changes
	 * 
	 * Java parameters:
	 * + very simple
	 * - return parameters need to be added to method call
	 * - interface for invoking methods is complicated
	 * -- exposed to version or method changes
	 */
	public ActionResponse executeAction(ActionRequest request)
	{
		ActionResponse response = new ActionResponse();
		List<ErrorReport> errors = null;
		ErrorReport error = null;

		try
		{
			//boolean requiresLoggedInUser =
			//	(Boolean) actionRequiresLoggedInUserMethod.invoke(null, request);
			error = checkBeforeExecuteAction(request, response,
				actionRequiresLoggedInUser(request));
				//aaacs.rex.ejb.beans.support.Dispatcher.actionRequiresLoggedInUser(request));

			if (error != null)
			{
				response.addErrorReport(error);
				return response;
			}

			// mark the time we received the request
			request.markTimeReceived();

			// begin transaction
			userTran.begin();

			errors = dispatch(request, response);
				//(List<ErrorReport>) dispatchMethod.invoke(null, this, request, response);
				//aaacs.rex.ejb.beans.support.Dispatcher.dispatch(request, response);
			if (errors != null) 
			{
				response.addErrorReports(errors); // could do this in the action...?
				response.clearParameters();
			}

			userTran.commit();
		}
		catch (Exception e)
		{
			// There are certain exceptions that occur that really indicate
			// an *underlying* exception occurred:
			// 1. A last-minute validation check failed (see 
			//    BaseEntity.prePersistUpdate()).
			//    This is because entity lifecycle callback methods can only
			//    throw runtime exceptions, thereby masking a validation
			//    exception.
			// 2. A method that this CoreServerDispatcher invoked in another session 
			//    bean threw an exception. Again a similar explanation: a
			//    System Exception (non-Application Exception) in a session
			//    bean is to be wrapped up in a javax.ejb.EJBException or
			//    a subclass thereof. This masks the real exception, and what
			//    is worse - at least in the Sun 1 9.0 container - the 
			//    underlying exception is totally stripped away when the
			//    EJBException is sent back to this bean. Hence in the other
			//    beans, you will find a bunch of wrapper exceptions that
			//    do nothing, all to avoid this default behavior.
			response.clearParameters();
			error = new ErrorReport("error.System.CoreServer", ErrorReport.Source.UNSPECIFIED,
				"action.UnspecifiedErrorType", request);
			error.addItem("action.Unexpected", null, null, e);
			response.addErrorReport(error);
/*
Look at http://forum.java.sun.com/thread.jspa?threadID=5140406
try 
{
	System.out.println("\n\n\n*******************************************\n\n\n\n");
	e.printStackTrace();
	System.out.println("\n\n\n*******************************************\n\n\n\n");
	PrintWriter pw = new PrintWriter(new FileOutputStream("Z:/exception.txt",false));
	Throwable t = e;
	while (t != null)
	{
		pw.println(t.getClass().getName() + ": " + t.getMessage());
		StackTraceElement[] stack = t.getStackTrace();
		for (StackTraceElement element : stack)
			pw.println("\t" + element.toString());
		pw.println();
		t = t.getCause();
	}
	//e.printStackTrace(pw);
	pw.close();
} catch(Exception ee) {}*/
			pst(e);
			try { if (transactionInProgress(userTran)) userTran.rollback(); }
			catch (Exception rbe) { msg("Rollback Exception!"); pst(rbe); }
		}

		// localize the response... ? or leave to delegate layer?
		// scramble all primary/foreign keys
		// scramble the login token's userid - already done?

		// if localization is to be done, then do it now right here
		return response;
	}
}
