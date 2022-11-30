package br.com.rogeriogregorio.blogPessoal.model.response;

import java.time.Instant;

public class Response {

	private Instant timestamp;
	private Integer status;
	private Object body;
	private String path;
	
	public Response() {
		
	}
	
	public Response(Instant timestamp, Integer status, Object body, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.body = body;
		this.path = path;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "Response [timestamp=" + timestamp + ", status=" + status + ", body=" + body + ", path=" + path + "]";
	}
}
