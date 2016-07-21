package by.htp3.hotel.service.exception;

public class ServiceRegistrException extends ServiceException{
	private static final long serialVersionUID = 1L;

	public ServiceRegistrException(String message) {
		super(message);
	}

	public ServiceRegistrException(String message, Exception e) {
		super(message, e);
	}
}
