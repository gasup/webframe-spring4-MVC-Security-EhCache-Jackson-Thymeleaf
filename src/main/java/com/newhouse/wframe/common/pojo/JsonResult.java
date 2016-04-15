/**
 * 
 */
package com.newhouse.wframe.common.pojo;

import com.fasterxml.jackson.annotation.JsonView;
import com.newhouse.wframe.core.entity.User.ListUserView;

/**
 * @author share_me
 *
 */
public class JsonResult {
	
	public interface JsonResultView{};
	
	static final String PRODUCT_VERSION = "1.0";
	
	@JsonView(JsonResultView.class)
	protected	Boolean		success = false;
	
	@JsonView(JsonResultView.class)
	protected	String		errorMessage;
	
	@JsonView(JsonResultView.class)
	protected	String		exceptionMessage;
	
	@JsonView(JsonResultView.class)
	protected	Object		data;
	
	@JsonView(JsonResultView.class)
	protected	int			error;
	
	@JsonView(JsonResultView.class)
	protected   String		version;
	
	
	public JsonResult(){
		this.InitFail();
	}
	
	public JsonResult(Object obj){
		this.data = obj;
		setSuccess(true);
	}
	
	public void setExceptionHtml(Exception e){
		if(e != null){
			//String errHtml = "服务器发生异常";
			String errHtml = "System error：<br/>\n";
			errHtml += e.toString() + "<br/>\n";
			for (int i=0; i<e.getStackTrace().length; i++){
				errHtml += e.getStackTrace()[i].toString() + "<br/>\n";
				if(i >= 10){
					errHtml += "......<br/>\n";
					break;
				}
			}
			this.setExceptionMessage(errHtml);
		}
	}
	
	/**
	 * @return the success
	 */
	public Boolean getSuccess() {
		return success;
	}
	public Boolean getIsSuccess() {
		return success;
	}
	public Boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	/**
	 * @return the msg
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setErrorMessage(String msg) {
		this.errorMessage = msg;
	}
	
	public void setFailMsg(String msg)
	{
		setSuccess(false);
		setErrorMessage(msg);
	}
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	
	public void InitFail(){
		this.setFailMsg("Unknow error!");
	}

	/**
	 * @return the exceptionMessage
	 */
	public String getExceptionMessage() {
		return exceptionMessage;
	}

	/**
	 * @param exceptionMessage the exceptionMessage to set
	 */
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		this.version = PRODUCT_VERSION;
		return version;
	}
	
	public String buildJson(String data){
		String format = "{\"success\":%b, \"version\":\"%s\", \"data\":%s, \"errorMessage\":\"%s\"}";
		return String.format(format, this.success, this.getVersion(), data, this.errorMessage);
	}
}
