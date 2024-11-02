package Model;

public class OrganizationCommand implements AddAndRemCommand {

	public enum commandType {
		ADD, REMOVE
	}

	private commandType commandType;
	private Model model;
	private int id;
	private String isDone;

	public OrganizationCommand(commandType commandType, Model model, int id) {
		this.commandType = commandType;
		this.model = model;
		this.id = id;
	}

	@Override
	public String apply() throws Exception {
		switch (commandType) {
		case ADD:
			isDone = model.addLecToOrg(id);
			break;

		case REMOVE:
			isDone = model.removeLecFromOrg(id);
			break;
		}
		return isDone;
	}

	@Override
	public String undo() throws Exception {
		switch (commandType) {
		case ADD:
			isDone = model.removeLecFromOrg(id);
			isDone = "Undo was successfull";
			break;

		case REMOVE:
			isDone = model.addLecToOrg(id);
			isDone = "Undo was successfull";
			break;
		}
		return isDone;
	}

}
