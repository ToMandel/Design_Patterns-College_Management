package Model;

class SalarySim {
	private double salary;

	public SalarySim(double salary) {
		this.salary = salary;
	}

	public Memento updateSalary(double amount) {
		this.salary += amount;

		return new Memento(this.salary - amount);
	}

	public void restore(Memento memento) {
		salary = memento.getSalary();
	}

	public double getSalary() {
		return salary;
	}

}
