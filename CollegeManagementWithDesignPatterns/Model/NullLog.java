package Model;

final public class NullLog implements Log {

	@Override
	public String info(String msg) {
		return "Warning: Please add lecturers to organization first";
	}
}
