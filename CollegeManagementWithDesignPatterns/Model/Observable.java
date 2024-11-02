package Model;

import java.util.ArrayList;
import java.util.List;

class Observable<T> {
	private List<Observer<T>> observers = new ArrayList<>();

	public void subscribe(Observer<T> observer) {
		observers.add(observer);
	}

	protected String propertyChanged(T source, String propertyName, Object newValue) {
		for (Observer<T> o : observers)
			return o.handle(new PropertyChangedEventArgs<T>(source, propertyName, newValue));
		return "";
	}
}
