package com.foyatech.oaps.struts.Action.common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foyatech.oaps.dto.DataSourceManager;
import com.foyatech.oaps.struts.Action.common.exception.CommonException;
import com.foyatech.oaps.struts.Action.common.exception.OAPSException;
import com.foyatech.oaps.struts.Action.common.exception.OAPSException.OAPSEnum;
/**
 * �@��Action���� for JSON�榡
 * @author equalhsiao
 * 2018/03/20
 * @param <T>
 * T ���n�^�Ǫ����� �i���ȩΤ���
 */
public abstract class CommonAction<T> extends Action implements ICommonAction{
    //�̫�^�Ǫ�JSON���� �]�i�H�����bdoProcess���������
    protected JSONObject json = new JSONObject();
    //�^�Ъ��� �̲צ^�N��rc,rm���X���Jjson���󤺦^��
    protected CommonResponse<T> resp = null;
    //DB�s�u�]�w
    protected DataSource ds = DataSourceManager.getOapsDataSource();
    protected Connection conn = null;
    
    //���oDB�s�u
    private Connection getConnection(){
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //��Action override ��k  �]�˭n���檺doProcess��k
    public  ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response){
        resp = new CommonResponse<T>();
        try{
            //OAPS DB�s�u���~
            try{
                this.conn = this.getConnection();
            }catch(Exception e){
                throw new OAPSException(OAPSEnum.OAPS_DB_CONNECTION_ERROR);
            }
            json =  doProcess(mapping, form, request, response,conn);
        }catch(Exception exception){
          //���~�B�z �|�NEnum���󤺪�errorCode,errorMessage���X��Jjson���󤺪�rc,rm��
            errorHandler(exception,response);
        }finally{
            try {
                response.setCharacterEncoding("UTF-8");
                try {
                    json.put("rc", resp.getRc());
                    json.put("rm", resp.getRm());
                    T data = resp.getData();
                    System.out.println("data:"+data);
                    if(data != null){
                        String userJson = null;
                        try {
                           ObjectMapper mapper = new ObjectMapper();
                           userJson = mapper.writeValueAsString(resp.getData());
                        } catch (JsonProcessingException e) {
                           e.printStackTrace();
                        }
                        json.put("data", new JSONObject(userJson));
                    }
                    json.write(response.getWriter());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    //�Ω���N��execute��k�w�ѧ�g
    public abstract  JSONObject doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response,Connection conn) throws Exception;

    //�@�ο��~�B�z
    public void errorHandler(Exception ex,HttpServletResponse response) {
        try{
            throw ex;
        } catch (Exception exception) {
            json = new JSONObject();
            if (exception instanceof CommonException) {
                CommonException commonEx = (CommonException) exception;
                resp.setRc(commonEx.getErrorCode());
                resp.setRm(commonEx.getErrorMessage());
            } else {
                System.out.println("�t�ο��~");
                resp.setRc("9999");
                resp.setRm("�t�ο��~:" + exception.getMessage());
            }
        }
    }
}
