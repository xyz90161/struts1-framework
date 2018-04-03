package com.foyatech.oaps.struts.Action.common;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.json.JSONObject;

public interface ICommonAction {

    public JSONObject doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response,Connection conn) throws Exception;
}
