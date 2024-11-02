package Model;

public interface AddAndRemCommand {

	String apply() throws Exception;

	String undo() throws Exception;
}
