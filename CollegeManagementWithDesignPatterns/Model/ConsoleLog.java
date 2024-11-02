package Model;

public class ConsoleLog implements Log {
	@Override
	public String info(String msg) {
		return msg;
	}
}
