package velcomParcer.controller;

import java.util.HashMap;
import java.util.Map;

import velcomParcer.controller.command.CommandName;
import velcomParcer.controller.command.impl.CreateCSV;
import velcomParcer.controller.command.impl.CreateXML;
import velcomParcer.controller.command.impl.ParserTenders;
import velcomParcer.controller.command.impl.SaveToDB;
import velcomParcer.controller.command.impl.WrongRequest;



public class CommandProvider {
	private final Map<CommandName, Command> repository = new HashMap<>();

	CommandProvider() {
		repository.put(CommandName.PARSERTENDER, new ParserTenders());
		repository.put(CommandName.WRONGREQUEST, new WrongRequest());
		repository.put(CommandName.CREATEXML, new CreateXML());
		repository.put(CommandName.CREATECSV, new CreateCSV());
		repository.put(CommandName.SAVETODB, new SaveToDB());
		

	}

	public Command getCommand(String commandName) {
		
		Command command = null;
		try {
			commandName = commandName.toUpperCase();
			command = repository.get(CommandName.valueOf(commandName));
		} catch (IllegalArgumentException | NullPointerException e) {
			command = repository.get(CommandName.WRONGREQUEST);
		}
		return command;
	}
	
}
