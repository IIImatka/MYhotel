package by.htp3.hotel.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp3.hotel.command.Command;
import by.htp3.hotel.command.impl.AddNewRoom;
import by.htp3.hotel.command.impl.AddNewRoomForm;
import by.htp3.hotel.command.impl.ChangeLanguage;
import by.htp3.hotel.command.impl.DeletRoom;
import by.htp3.hotel.command.impl.GetFreeRooms;
import by.htp3.hotel.command.impl.Registration;
import by.htp3.hotel.command.impl.RegistrationForm;
import by.htp3.hotel.command.impl.logination;

public final class CommandHelper {
	private static final CommandHelper instance = new CommandHelper();
	
	private Map<CommandName, Command> commands = new HashMap<>();
	
	private CommandHelper(){
		commands.put(CommandName.LOGINATION, new logination());
		commands.put(CommandName.CHANGE_LANGUAGE, new ChangeLanguage());
		commands.put(CommandName.SHOW_FREE_ROOMS, new GetFreeRooms());
		commands.put(CommandName.ADD_NEW_ROOM, new AddNewRoom());
		commands.put(CommandName.ADD_NEW_ROOM_FORM, new AddNewRoomForm());
		commands.put(CommandName.DELET_ROOM, new DeletRoom());
		commands.put(CommandName.REGISTRATION, new Registration());
		commands.put(CommandName.REGISTRATION_FORM, new RegistrationForm());
	}
	public Command getCommand(String name){
		name = name.replace('-', '_');
		CommandName commandName = CommandName.valueOf(name.toUpperCase());
		
		Command command = commands.get(commandName);
		
		return command;
		
	}
	
	public static CommandHelper getInstance(){
		return instance;
	}
}
