package by.htp3.hotel.service.exception;

public class ServiceDeleteRoomException extends ServiceException {
	private static final long serialVersionUID = 1L;

	public ServiceDeleteRoomException(String message) {
		super(message);
	}

	public ServiceDeleteRoomException(String message, Exception e) {
		super(message, e);
	}
}
