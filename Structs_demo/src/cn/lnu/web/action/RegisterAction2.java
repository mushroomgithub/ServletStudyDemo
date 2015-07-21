package cn.lnu.web.action;

import java.io.FileOutputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;

import cn.lnu.web.formbean.RegisterBean;

public class RegisterAction2 extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		DynaActionForm dynaForm=(DynaActionForm) form;
		
		System.out.println(dynaForm.getString("username"));
		System.out.println(dynaForm.getString("password"));
		System.out.println(dynaForm.getString("email"));
		return null;
	}
	
}
