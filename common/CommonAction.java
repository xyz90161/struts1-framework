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
 * 共用Action物件 for JSON格式
 * @author equalhsiao
 * 2018/03/20
 * @param <T>
 * T 為要回傳的物件 可給值或不給
 */
public abstract class CommonAction<T> extends Action implements ICommonAction{
    //最後回傳的JSON物件 也可以直接在doProcess內給它賦值
    protected JSONObject json = new JSONObject();
    //回覆物件 最終回將其rc,rm取出後放入json物件內回傳
    protected CommonResponse<T> resp = null;
    //DB連線設定
    protected DataSource ds = DataSourceManager.getOapsDataSource();
    protected Connection conn = null;
    
    //取得DB連線
    private Connection getConnection(){
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //原Action override 方法  包裝要執行的doProcess方法
    public  ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response){
        resp = new CommonResponse<T>();
        try{
            //OAPS DB連線錯誤
            try{
                this.conn = this.getConnection();
            }catch(Exception e){
                throw new OAPSException(OAPSEnum.OAPS_DB_CONNECTION_ERROR);
            }
            json =  doProcess(mapping, form, request, response,conn);
        }catch(Exception exception){
          //錯誤處理 會將Enum物件內的errorCode,errorMessage取出放入json物件內的rc,rm中
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
    //用於取代原execute方法已供改寫
    public abstract  JSONObject doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response,Connection conn) throws Exception;

    //共用錯誤處理
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
                System.out.println("系統錯誤");
                resp.setRc("9999");
                resp.setRm("系統錯誤:" + exception.getMessage());
            }
        }
    }
}
